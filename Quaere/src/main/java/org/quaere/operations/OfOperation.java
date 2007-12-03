package org.quaere.operations;

import org.quaere.Queryable;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 01:45:21 (c) 2007 jexp.de
 */
public interface OfOperation extends Qualification {
    <T> Double of(T[] source);

    <R, T> R of(Class<R> toClass, T[] source);

    <T> Double of(Iterable<T> source);

    <R, T> R of(Class<R> toClass, Iterable<T> source);

    <T> Double of(Queryable<T> source);

    <R, T> R of(Class<R> toClass, Queryable<T> source);
}
