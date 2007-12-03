package org.quaere.operations;

import org.quaere.expressions.Identifier;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 10:57:47 (c) 2007 jexp.de
 */
public enum DefaultOperation {
    COUNT,
    MIN,
    MAX,
    AVERAGE,
    DISTINCT,
    EXCEPT, INTERSECT, SUM, UNION;

    Identifier asIdentifier() {
        return new Identifier(operationName());
    }
    public String operationName() {
        return name().toLowerCase();
    }

    public String toString() {
        return operationName();
    }
}
