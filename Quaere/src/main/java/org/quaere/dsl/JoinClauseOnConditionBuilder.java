package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.expressions.EqualOperator;


public interface JoinClauseOnConditionBuilder {
    JoinClauseBuilderContinuation on(EqualOperator expression);
}
