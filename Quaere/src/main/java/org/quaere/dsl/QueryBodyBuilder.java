package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface QueryBodyBuilder extends Selectable {
    FromClauseBuilder from(String identifier);
    JoinClauseBuilder join(String identifer);
    WhereClauseBuilder where(Expression predicate);
    WhereClauseBuilder where(String expression);
}
