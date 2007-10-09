package org.quaere.alias;

public class Index<T> implements Function<T> {

    public Object getValue(QueryBase query, T t) {
        return query.getIndex();
    }
    
}
