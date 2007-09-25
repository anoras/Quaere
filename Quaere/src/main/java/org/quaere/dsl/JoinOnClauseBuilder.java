package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface JoinOnClauseBuilder<R> {
    JoinOnEqualsClauseBuilder<R> on(String expression);
    JoinOnEqualsClauseBuilder<R> on(Expression expression);
}
