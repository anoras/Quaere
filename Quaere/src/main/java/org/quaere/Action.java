package org.quaere;


public interface Action<T> {
    void execute(T obj);
}
