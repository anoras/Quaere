package org.quaere;


public interface Predicate<T> {
    boolean execute(T obj);
}
