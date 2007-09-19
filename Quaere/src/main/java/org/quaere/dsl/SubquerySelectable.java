package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.expressions.QueryBody;


public interface SubquerySelectable {
    Expression select(String expression);
    Expression select(Expression expression);}
