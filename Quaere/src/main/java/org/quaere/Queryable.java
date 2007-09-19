package org.quaere;

import java.util.Comparator;


public interface Queryable<T> extends Iterable<T> {
    Queryable<T> orderBy(Comparator comparison);
    Queryable<T> select(Action<T> action);

    Queryable<T> where(Predicate<T> predicate);
}
