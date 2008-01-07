package org.quaere.dsl;

import org.quaere.Queryable;
import org.quaere.QueryEngine;
import org.quaere.objects.ObjectQueryEngine;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Expression;
import org.quaere.expressions.Statement;
import org.quaere.expressions.MethodCall;

import java.util.Arrays;
import java.util.ArrayList;

public class ElementOperatorBuilderImpl implements
        ElementOperatorBuilder,
        ElementOperatorArgumentDefinitionBuilder,
        ElementOperatorWhereClauseBuilder,
        ElementOperatorWhereClauseOrIndexerArgumentBuilder {
    private final String operator;
    private Queryable source;
    private Identifier anonymousIdentifier;
    private Identifier indexerIdentifier;
    public ElementOperatorBuilderImpl(String operator) {
        this.operator = operator;
    }
    public <T> ElementOperatorArgumentDefinitionBuilder in(Queryable<T> source) {
        this.source = source;
        return this;
    }
    public ElementOperatorWhereClauseOrIndexerArgumentBuilder as(String anonymousIdentifier) {
        this.anonymousIdentifier = new Identifier(anonymousIdentifier);
        return this;
    }

    public ElementOperatorWhereClauseBuilder withIndexer(String indexerIdentifier) {
        this.indexerIdentifier = new Identifier(indexerIdentifier);
        return this;
    }
    public Object where(String predicate) {
        return where(LiteralExpression.parse(predicate));
    }
    public Object where(Expression predicate) {
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
        if (queryEngine instanceof ObjectQueryEngine) {
            ObjectQueryEngine asObjectQueryEngine = (ObjectQueryEngine) queryEngine;
            asObjectQueryEngine.addSource(sourceIdentifier, source);
        }
        return queryEngine.evaluate(query);
    }
}
