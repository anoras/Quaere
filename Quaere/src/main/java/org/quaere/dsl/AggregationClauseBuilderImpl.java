package org.quaere.dsl;

import org.quaere.Convert;
import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.MethodCall;
import org.quaere.expressions.Statement;
import org.quaere.quaere4objects.Quaere4ObjectsQueryEngine;

import java.util.ArrayList;
import java.util.Arrays;

public class AggregationClauseBuilderImpl<R> implements AggregationClauseBuilder<R>, AggregationClauseByExpressionBuilder<R> {
    private Identifier anonymousIdentifier;
    private Expression qualifier;
    private final String operator;
    public AggregationClauseBuilderImpl(String operator, Identifier anonymousIdentifier) {
        this.operator = operator;
        this.anonymousIdentifier = anonymousIdentifier;
    }
    public AggregationClauseByExpressionBuilder<R> by(String expression) {
        return by(LiteralExpression.parse(expression));
    }
    public AggregationClauseByExpressionBuilder<R> by(Expression expression) {
        qualifier = expression;
        return this;
    }

    public <T> R in(Class<R> rClass,T[] source) {
        return in(rClass, Arrays.asList(source));
    }
    public <T> R in(Class<R> rClass, Iterable<T> source) {
        return in(rClass, new QueryableIterable<T>(source));
    }
    public <T> R in(Class<R> rClass, Queryable<T> source) {
        QueryEngine queryEngine = source.createQueryEngine();
        Identifier sourceIdentifier = Identifier.createUniqueIdentfier();
        Statement query = new Statement(
                Arrays.<Expression>asList(
                        sourceIdentifier,
                        new MethodCall(
                                new Identifier(operator),
                                new ArrayList<Expression>(0),
                                anonymousIdentifier,
                                null,
                                qualifier
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return (R) Convert.coerce(queryEngine.evaluate(query),rClass);
    }
    public Expression in(String expression) {
        return in(LiteralExpression.parse(expression));
    }
    public Expression in(Expression expression) {
        Statement aggregationExpression = new Statement(
                Arrays.<Expression>asList(
                        expression,
                        new MethodCall(
                                new Identifier(operator),
                                new ArrayList<Expression>(0),
                                anonymousIdentifier,
                                null,
                                qualifier
                        )
                )
        );
        return aggregationExpression;
    }
}
