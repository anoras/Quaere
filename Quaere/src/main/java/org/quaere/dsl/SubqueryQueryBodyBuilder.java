package org.quaere.dsl;

import org.quaere.expressions.Expression;


public interface SubqueryQueryBodyBuilder extends SubquerySelectable {
    SubqueryJoinClauseBuilder join(String identifer);
    SubqueryWhereClauseBuilder where(Expression predicate);
}
