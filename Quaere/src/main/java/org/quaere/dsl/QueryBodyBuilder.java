package org.quaere.dsl;

import org.quaere.expressions.Expression;

import java.util.Comparator;


public interface QueryBodyBuilder<R> extends Iterable<R> {
    FromClauseBuilder<R> from(String identifer);
    FromClauseBuilder<R> from(QueryExpressionBuilder subquery);
    LetClauseBuilder<R> let(String identifier);
    QueryBodyBuilder<R> where(String predicate);
    QueryBodyBuilder<R> where(Expression predicate);
    JoinClauseBuilder<R> join(String identifer);
    QueryBodyBuilder<R> orderBy(String expression);
    QueryBodyBuilder<R> orderBy(String expression, Comparator comparator);
    QueryBodyBuilder<R> orderBy(Expression expression);
    QueryBodyBuilder<R> orderBy(Expression expression, Comparator comparator);
    QueryBodyBuilder<R> orderByDescending(String expression);
    QueryBodyBuilder<R> orderByDescending(String expression, Comparator comparator);
    QueryBodyBuilder<R> orderByDescending(Expression expression);
    QueryBodyBuilder<R> orderByDescending(Expression expression, Comparator comparator);
    <R> QueryContinuationOrQueryBodyBuilder<R> select(String expression);
    <R> QueryContinuationOrQueryBodyBuilder<R> select(Expression expression);
    GroupClauseBuilder<R> group(String identifier);
}
