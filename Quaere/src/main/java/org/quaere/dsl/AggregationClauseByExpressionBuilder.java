package org.quaere.dsl;

import org.quaere.Queryable;
import org.quaere.expressions.Expression;

public interface AggregationClauseByExpressionBuilder<R> {
    <T> R in(Class<R> rClass, T[] source);
    <T> R in(Class<R> rClass, Iterable<T> source);
    <T> R in(Class<R> rClass, Queryable<T> source);
    Expression in(String expression);
    Expression in(Expression expression);
}
