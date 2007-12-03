package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface PartitionWhenOperatorWhenClauseBuilder {
    <T> Iterable<T> when(Expression predicate);
}
