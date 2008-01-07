package org.quaere.dsl;

import org.quaere.Queryable;
import org.quaere.QueryableIterable;
import org.quaere.QueryEngine;
import org.quaere.Convert;
import org.quaere.objects.Quaere4ObjectsQueryEngine;
import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Statement;
import org.quaere.expressions.MethodCall;

import java.util.Arrays;
import java.util.ArrayList;

public class AggregateOperatorBuilderImpl<R> implements
        AggregateOperatorBuilder<R>,
        AggregateOperatorInClauseBuilder<R> {
    private final Identifier accumulationIdentifier;
    private final Identifier anonymousIdentifier;
    private Expression lambdaExpression;
    public AggregateOperatorBuilderImpl(Identifier accumulationIdentifier, Identifier anonymousIdentifier) {
        this.accumulationIdentifier = accumulationIdentifier;
        this.anonymousIdentifier = anonymousIdentifier;
    }
    public AggregateOperatorInClauseBuilder<R> with(String expression) {
        return with(LiteralExpression.parse(expression));
    }
    public AggregateOperatorInClauseBuilder<R> with(Expression expression) {
        this.lambdaExpression = expression;
        return this;
    }
    public <T> R in(Class<R> rClass, T[] source) {
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
                                new Identifier("aggregate"),
                                new ArrayList<Expression>(0),
                                anonymousIdentifier,
                                accumulationIdentifier,
                                lambdaExpression
                        )
                )
        );
        if (queryEngine instanceof Quaere4ObjectsQueryEngine) {
            Quaere4ObjectsQueryEngine asQuaere4ObjectsQueryEngine = (Quaere4ObjectsQueryEngine) queryEngine;
            asQuaere4ObjectsQueryEngine.addSource(sourceIdentifier, source);
        }
        return (R) Convert.toType(queryEngine.evaluate(query), rClass);
    }
}
