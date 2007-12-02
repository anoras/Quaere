package org.quaere.dsl;

import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.QueryEngine;
import org.quaere.quaere4objects.Quaere4ObjectsQueryEngine;
import org.quaere.expressions.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PartitionWhenOperatorBuilderImpl implements PartitionWhenOperatorBuilder, PartitionWhenOperatorWhenClauseBuilder {
    private String operator;
    private Queryable source;
    private Identifier anonymousIdentifier;
    private Identifier indexerIdentifier;

    public PartitionWhenOperatorBuilderImpl(PartitionOperator operator, String anonymousIdentifier) {
        this(operator.operationName(),anonymousIdentifier);
    }
    public PartitionWhenOperatorBuilderImpl(String operator, String anonymousIdentifier) {
        this.operator = operator;
        this.anonymousIdentifier = new Identifier(anonymousIdentifier);
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder from(T[] source) {
        return from(Arrays.asList(source));
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder from(Iterable<T> source) {
        return from(new QueryableIterable<T>(source));
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder from(Queryable<T> source) {
        this.source = source;
        return this;
    }

    public PartitionWhenOperatorWhenClauseBuilder from(QueryBodyBuilder<?> query) {
        return evaluateQuery(query);
    }
    public PartitionWhenOperatorBuilder withIndexer(String indexerIdentifer) {
        this.indexerIdentifier = new Identifier(indexerIdentifer);
        return this;
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder in(T[] source) {
        return in(Arrays.asList(source));
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder in(Iterable<T> source) {
        return in(new QueryableIterable<T>(source));
    }
    public <T> PartitionWhenOperatorWhenClauseBuilder in(Queryable<T> source) {
        this.source = source;
        return this;
    }
    public PartitionWhenOperatorWhenClauseBuilder in(QueryBodyBuilder<?> query) {
        return evaluateQuery(query);
    }
    public <T> Iterable<T> when(Expression predicate) {
        QueryEngine queryEngine = source.createQueryEngine();
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        sourceIdentifier,
                        new MethodCall(
                                new Identifier(operator),
                                new ArrayList<Expression>(0),
                                anonymousIdentifier,
                                indexerIdentifier,
                                predicate
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return queryEngine.evaluate(query);
    }
    private <T> PartitionWhenOperatorWhenClauseBuilder evaluateQuery(QueryBodyBuilder<?> query) {
        List<T> elms = new ArrayList<T>();
        Iterator iter = query.iterator();
        while (iter.hasNext()) {
            elms.add((T) iter.next());
        }
        return from(elms);
    }
}
