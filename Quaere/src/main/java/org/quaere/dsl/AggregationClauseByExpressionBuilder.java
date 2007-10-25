package org.quaere.dsl;

import org.quaere.Queryable;
import org.quaere.expressions.Expression;

public interface AggregationClauseByExpressionBuilder<R> {
    <T> R in(T[] source);
    <T> R in(Iterable<T> source);
    <T> R in(Queryable<T> source);
    Expression in(String expression);
    Expression in(Expression expression);
}
