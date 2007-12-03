package org.quaere.operations;

import org.quaere.dsl.AggregationOperatorBuilder;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 01:48:35 (c) 2007 jexp.de
 */
public interface Qualification {
    <R> AggregationOperatorBuilder<R> qualify(String anonymousIdentifier);
}
