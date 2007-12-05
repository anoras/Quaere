package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Statement;
import org.quaere.expressions.JoinClause;
import org.quaere.Queryable;
import org.quaere.QueryableIterable;

import java.util.Arrays;

public class JoinClauseBuilderImpl<R> implements JoinClauseBuilder<R>, JoinOnClauseBuilder<R>, JoinOnEqualsClauseBuilder<R> {
    private final QueryExpressionBuilderImpl<R> parentBuilder;
    private final Identifier currentIdentifier;
    private Expression onExpression;
    public JoinClauseBuilderImpl(QueryExpressionBuilderImpl<R> parentBuilder, Identifier identifier) {
        this.parentBuilder = parentBuilder;
        currentIdentifier = identifier;
    }
    public <T> JoinOnClauseBuilder<R> in(T[] source) {
        return in(Arrays.asList(source));
    }
    public <T> JoinOnClauseBuilder<R> in(Iterable<T> source) {
        return in(new QueryableIterable<T>(source));
    }
    public <T> JoinOnClauseBuilder<R> in(Queryable<T> source) {
        parentBuilder.sources.put(currentIdentifier, source);
        return this;
    }
    public JoinOnClauseBuilder<R> in(String expression) {
        throw new RuntimeException("JoinClauseBuilderImpl.in is not implemented");
    }
    public JoinOnEqualsClauseBuilder<R> on(String onExpression) {
        return on(LiteralExpression.parse(onExpression));
    }
    public JoinOnEqualsClauseBuilder<R> on(Expression onExpression) {
        this.onExpression = onExpression;
        return this;
    }
    public QueryContinuationOrQueryBodyBuilder<R> equals(String equalsExpression) {
        return equals(LiteralExpression.parse(equalsExpression));
    }
    public QueryContinuationOrQueryBodyBuilder<R> equals(Expression equalsExpression) {
        JoinClause joinClause = new JoinClause(
                currentIdentifier,
                new Statement(
                        Arrays.<Expression>asList(
                                parentBuilder.sources.get(currentIdentifier).getSourceIdentifier(currentIdentifier)
                        )
                ),
                onExpression,
                equalsExpression,
                null
        );
        parentBuilder.queryBodyClauses.add(joinClause);
        return parentBuilder;
    }
}
