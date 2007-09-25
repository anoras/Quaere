package org.quaere.dsl;

import org.quaere.dsl.QueryBodyBuilder;
import org.quaere.dsl.QueryContinuationOrQueryBodyBuilder;
import org.quaere.Variant;
import org.quaere.Queryable;


public interface PartitioningOperatorBuilder {
    <T> Iterable<T> from(T[] source);
    <T> Iterable<T> from(Iterable<T> source);
    <T> Iterable<T> from(Queryable<T> source);
    <T> Iterable<T> from(QueryBodyBuilder<?> query);
    <T> Iterable<T> in(T[] source);
    <T> Iterable<T> in(Iterable<T> source);
    <T> Iterable<T> in(Queryable<T> source);
    <T> Iterable<T> in(QueryBodyBuilder<?> query);
}
