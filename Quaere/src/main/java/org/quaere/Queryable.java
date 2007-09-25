package org.quaere;

import org.quaere.expressions.Identifier;

import java.util.Comparator;


public interface Queryable<T> extends Iterable<T> {
    QueryEngine createQueryEngine();
    Identifier getSourceIdentifier(Identifier identifier);
}
