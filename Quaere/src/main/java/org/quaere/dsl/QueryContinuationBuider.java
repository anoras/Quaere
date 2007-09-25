package org.quaere.dsl;


public interface QueryContinuationBuider<R> {
    QueryContinuationOrQueryBodyBuilder<R> into(String identifier);
}
