package org.quaere.dsl;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 11:08:07 (c) 2007 jexp.de
 */
public enum PartitionOperator {
    take, takeWhile, skip, skipWhile;

    public String operationName() {
        return name();
    }

}
