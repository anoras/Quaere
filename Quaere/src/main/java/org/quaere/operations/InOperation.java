package org.quaere.operations;

import org.quaere.Queryable;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 01:44:55 (c) 2007 jexp.de
 */
public interface InOperation extends Qualification {
    <T> Double in(T[] source);

    <R, T> R in(Class<R> toClass, T[] source);

    <T> Double in(Iterable<T> source);

    <R, T> R in(Class<R> toClass, Iterable<T> source);

    <T> Double in(Queryable<T> source);

    <R, T> R in(Class<R> toClass, Queryable<T> source);
}
