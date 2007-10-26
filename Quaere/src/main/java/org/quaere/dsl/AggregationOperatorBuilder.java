package org.quaere.dsl;

import org.quaere.expressions.Expression;

public interface AggregationOperatorBuilder<R> {
    AggregationOperatorByExpressionBuilder<R> by(String expression);
    AggregationOperatorByExpressionBuilder<R> by(Expression expression);
    AggregationOperatorWhereClauseOrIndexerArgumentBuilder<R> withIndexer(String indexerIdentifer);
}
