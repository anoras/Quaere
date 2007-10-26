package org.quaere.dsl;

import org.quaere.Queryable;

public interface AggregationOperatorWhereClauseBuilder<R> {
    <T> R in(T[] source);
    <T> R in(Iterable<T> source);
    <T> R in(Queryable<T> source);
}
