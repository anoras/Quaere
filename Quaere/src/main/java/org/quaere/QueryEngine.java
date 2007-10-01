package org.quaere;

import org.quaere.expressions.Expression;
import org.quaere.expressions.Identifier;


public interface QueryEngine {
    void addSource(Identifier identifer, Queryable<?> source);
    <T> T evaluate(Expression query);
}
