package org.quaere;

import org.quaere.expressions.Expression;


public interface QueryEngine {
    <T> T evaluate(Expression query);
}
