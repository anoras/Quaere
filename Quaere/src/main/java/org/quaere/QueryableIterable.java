package org.quaere;

import java.util.Comparator;
import java.util.Iterator;

public class QueryableIterable<T> implements Queryable<T> {
    final Iterable<T> elements;

    public QueryableIterable(Iterable<T> elements) {
        this.elements = elements;
    }

// --------------------- Interface Iterable ---------------------


    public Iterator<T> iterator() {
        return elements.iterator();
    }

// --------------------- Interface Queryable ---------------------


    public Queryable<T> orderBy(Comparator comparison) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Queryable<T> select(Action<T> action) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Queryable<T> where(Predicate<T> predicate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
