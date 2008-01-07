package org.quaere.dsl;

import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.expressions.*;
import org.quaere.objects.Quaere4ObjectsQueryEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PartitioningOperatorBuilderImpl implements PartitioningOperatorBuilder {
    private final String operator;
    private final int count;
    public PartitioningOperatorBuilderImpl(PartitionOperator operator, int count) {
        this(operator.operationName(), count);
    }
    public PartitioningOperatorBuilderImpl(String operator, int count) {
        this.operator = operator;
        this.count = count;
    }

    public <T> Iterable<T> from(T[] source) {
        return from(Arrays.asList(source));
    }
    public <T> Iterable<T> from(Iterable<T> source) {
        return from(new QueryableIterable<T>(source));
    }
    public <T> Iterable<T> from(Queryable<T> source) {
        return evaluateFor(source);
    }
    @SuppressWarnings({"WhileLoopReplaceableByForEach", "unchecked"})
    public <T> Iterable<T> from(QueryBodyBuilder<?> query) {
        return evaluateQuery(query);
    }
    public <T> Iterable<T> in(T[] source) {
        return in(Arrays.asList(source));
    }
    public <T> Iterable<T> in(Iterable<T> source) {
        return in(new QueryableIterable<T>(source));
    }
    public <T> Iterable<T> in(Queryable<T> source) {
        return evaluateFor(source);
    }
    public <T> Iterable<T> in(QueryBodyBuilder<?> query) {
        return evaluateQuery(query);
    }
    private <T> Iterable<T> evaluateQuery(QueryBodyBuilder<?> query) {
        List<T> elms = new ArrayList<T>();
        Iterator iter = query.iterator();
        while (iter.hasNext()) {
            elms.add((T) iter.next());
        }
        return from(elms);
    }
    private <T> Iterable<T> evaluateFor(Queryable<T> source) {
        QueryEngine queryEngine = source.createQueryEngine();
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        sourceIdentifier,
                        new MethodCall(
                                new Identifier(operator),
                                Arrays.<Expression>asList(
                                        new Constant(count, Integer.class)
                                )
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return queryEngine.evaluate(query);
    }

}
