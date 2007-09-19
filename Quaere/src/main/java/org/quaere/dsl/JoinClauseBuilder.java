package org.quaere.dsl;

import java.util.List;


public interface JoinClauseBuilder {
    <T> JoinClauseOnConditionBuilder in(T...source);
    <T> JoinClauseOnConditionBuilder in(List<T> source);
}
