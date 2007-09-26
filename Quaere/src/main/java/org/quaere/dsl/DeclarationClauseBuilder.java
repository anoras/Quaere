package org.quaere.dsl;

import org.w3c.dom.html.HTMLInputElement;
import org.quaere.expressions.Expression;


public interface DeclarationClauseBuilder<R> {
    QueryBodyBuilder<R> as(String expression);
    QueryBodyBuilder<R> as(Expression expression);
}
