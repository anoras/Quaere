package org.quaere.alias;

public abstract class Condition<T> implements Function<T> {
    abstract boolean test(T t, QueryBase query);
    
    public Object getValue(QueryBase query, T t) {
        return Boolean.valueOf(test(t, query));
    }    
    
}
