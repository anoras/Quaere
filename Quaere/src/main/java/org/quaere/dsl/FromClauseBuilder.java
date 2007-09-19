package org.quaere.dsl;

import java.util.List;


public interface FromClauseBuilder {
    OrderableAndGroupableQueryBodyBuilder in(String expression);
    <T> OrderableAndGroupableQueryBodyBuilder in(T...source);
    <T> OrderableAndGroupableQueryBodyBuilder in(List<T> source);
}
