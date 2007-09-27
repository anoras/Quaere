package org.quaere.dsl;

import org.quaere.expressions.QueryExpression;


public interface QueryContinuationBuider<R> {
    QueryContinuationOrQueryBodyBuilder<R> into(String identifier);
}
