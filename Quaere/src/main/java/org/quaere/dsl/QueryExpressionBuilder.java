package org.quaere.dsl;


public interface QueryExpressionBuilder<R> {
    FromClauseBuilder<R> from(String identifier);
}
