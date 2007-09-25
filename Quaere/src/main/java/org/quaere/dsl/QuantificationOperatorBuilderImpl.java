package org.quaere.dsl;

import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.quaere4objects.Quaere4ObjectsQueryEngine;
import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.MethodCall;
import org.quaere.expressions.Statement;

import java.util.ArrayList;
import java.util.Arrays;

public class QuantificationOperatorBuilderImpl implements
        QuantificationOperatorBuilder,
        QuantificationOperatorArgumentDefinitionBuilder,
        QuantificationOperatorWhereClauseOrIndexerArgumentBuilder,
        QuantificationOperatorWhereClauseBuilder {
    private final String operator;
    private Queryable source;
    private Identifier anonymousIdentifier;
    private Identifier indexerIdentifier;
    public QuantificationOperatorBuilderImpl(String operator) {
        this.operator = operator;
    }
    public <T> QuantificationOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
        this.source = source;
        return this;
    }
    public QuantificationOperatorWhereClauseOrIndexerArgumentBuilder as(String anonymousIdentifier) {
        this.anonymousIdentifier = new Identifier(anonymousIdentifier);
        return this;
    }
    public QuantificationOperatorWhereClauseBuilder withIndexer(String indexerIdentifier) {
        this.indexerIdentifier = new Identifier(indexerIdentifier);
        return this;
    }
    public boolean where(String predicate) {
        return where(LiteralExpression.parse(predicate));
    }
    public boolean where(Expression predicate) {
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
        return (Boolean) queryEngine.evaluate(query);
    }
}
