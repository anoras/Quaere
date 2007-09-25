package org.quaere.dsl;

import org.quaere.expressions.Expression;

public interface JoinOnEqualsClauseBuilder<R> {
    QueryContinuationOrQueryBodyBuilder<R> equals(String expression);
    QueryContinuationOrQueryBodyBuilder<R> equals(Expression expression);
}
