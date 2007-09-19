package org.quaere.dsl;

import java.util.Comparator;


public interface GroupClauseBuilder {
    GroupClauseBuilderContinuation by(String expression);
    GroupClauseBuilderContinuation by(String expression, Comparator comparator);
}
