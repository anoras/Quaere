package org.quaere.dsl;

import org.quaere.Queryable;

public interface AggregateOperatorInClauseBuilder<R> {
    <T> R in(Class<R> rClass, T[] source);
    <T> R in(Class<R> rClass, Iterable<T> source);
    <T> R in(Class<R> rClass, Queryable<T> source);
}
