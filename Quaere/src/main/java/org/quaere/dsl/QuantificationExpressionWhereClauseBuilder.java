package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface QuantificationExpressionWhereClauseBuilder {
    Expression where(String predicate);
    Expression where(Expression predicate);
}
