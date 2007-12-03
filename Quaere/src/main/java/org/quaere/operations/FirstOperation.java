package org.quaere.operations;

import org.quaere.dsl.ElementOperatorArgumentDefinitionBuilder;
import org.quaere.Queryable;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 02:34:26 (c) 2007 jexp.de
 */
public interface FirstOperation {
    <T> ElementOperatorArgumentDefinitionBuilder in(T[] source);

    <T> ElementOperatorArgumentDefinitionBuilder in(Iterable<T> source);

    <T> ElementOperatorArgumentDefinitionBuilder in(Queryable<T> source);
}
