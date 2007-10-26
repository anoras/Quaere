package org.quaere.dsl;

import org.quaere.expressions.Expression;

public interface AggregationClauseBuilder<R> {
    AggregationClauseByExpressionBuilder<R> by(String expression);
    AggregationClauseByExpressionBuilder<R> by(Expression expression);
}
