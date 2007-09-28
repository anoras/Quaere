package org.quaere.dsl;

import org.quaere.Queryable;


public interface ElementOperatorBuilder {
    <T> ElementOperatorArgumentDefinitionBuilder in(Queryable<T> source);
}
