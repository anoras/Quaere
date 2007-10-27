package org.quaere.dsl;

import org.quaere.expressions.Expression;

public interface AggregateOperatorBuilder<R> {
    AggregateOperatorInClauseBuilder<R> with(String expression);
    AggregateOperatorInClauseBuilder<R> with(Expression expression);
}
