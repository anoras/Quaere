package org.quaere.dsl;

import org.quaere.expressions.Expression;
import org.quaere.expressions.Statement;
import org.quaere.expressions.Identifier;

import java.util.Arrays;


public interface Selectable {
    <T> Iterable<T> select(String expression);
    <T> Iterable<T> select(Expression expression);
}
