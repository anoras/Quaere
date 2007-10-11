package org.quaere.alias;

public abstract class Condition<T> implements Function<T> {
    abstract boolean test(QueryBase query);
    
    public Object getValue(QueryBase query) {
        return Boolean.valueOf(test(query));
    }    
    
}
