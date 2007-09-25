package org.quaere.dsl;

import org.quaere.expressions.Expression;

import java.util.Comparator;

public interface GroupClauseBuilder<R> {
    QueryContinuationBuider<R> by(String expression);
    QueryContinuationBuider<R> by(String expression, Comparator comparator);
    QueryContinuationBuider<R> by(Expression expression);
    QueryContinuationBuider<R> by(Expression expression, Comparator comparator);
}