package org.quaere;

import org.quaere.expressions.Identifier;
import org.quaere.objects.ObjectQueryEngine;

import java.util.Iterator;

public class QueryableIterable<T> implements Queryable<T> {
    final Iterable<T> elements;

    public QueryableIterable(Iterable<T> elements) {
        this.elements = elements;
    }

    public Iterator<T> iterator() {
        return elements.iterator();
    }


    public QueryEngine createQueryEngine() {
        return new ObjectQueryEngine();
    }
    public Identifier getSourceIdentifier(Identifier identifier) {
        return new Identifier("__src_" + identifier.name);
    }
}
