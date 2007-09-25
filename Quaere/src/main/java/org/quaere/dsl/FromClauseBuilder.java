package org.quaere.dsl;

import org.quaere.Queryable;


public interface FromClauseBuilder<R> {
    <T> QueryBodyBuilder<R> in(T[] source);
    <T> QueryBodyBuilder<R> in(Iterable<T> source);
    <T> QueryBodyBuilder<R> in(Queryable<T> source);
    QueryBodyBuilder<R> in(String expression);
}
