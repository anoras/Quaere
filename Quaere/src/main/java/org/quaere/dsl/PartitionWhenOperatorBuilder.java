package org.quaere.dsl;

import org.quaere.Queryable;


public interface PartitionWhenOperatorBuilder {
    <T> PartitionWhenOperatorWhenClauseBuilder from(T[] source);
    <T> PartitionWhenOperatorWhenClauseBuilder from(Iterable<T> source);
    <T> PartitionWhenOperatorWhenClauseBuilder from(Queryable<T> source);
    PartitionWhenOperatorWhenClauseBuilder from(QueryBodyBuilder<?> query);
    PartitionWhenOperatorBuilder withIndexer(String indexerIdentifer);
    <T> PartitionWhenOperatorWhenClauseBuilder in(T[] source);
    <T> PartitionWhenOperatorWhenClauseBuilder in(Iterable<T> source);
    <T> PartitionWhenOperatorWhenClauseBuilder in(Queryable<T> source);
    PartitionWhenOperatorWhenClauseBuilder in(QueryBodyBuilder<?> query);
}
