package org.quaere.dsl;

import org.quaere.Queryable;


public interface QuantificationOperatorBuilder {
    <T> QuantificationOperatorArgumentDefinitionBuilder in(Queryable<T> source);
}
