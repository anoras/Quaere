package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface QuantificationOperatorWhereClauseBuilder {
    boolean where(String predicate);
    boolean where(Expression predicate);
}
