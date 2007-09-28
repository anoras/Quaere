package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface ElementOperatorWhereClauseBuilder {
    <R> R where(String predicate);
    <R> R where(Expression predicate);
}
