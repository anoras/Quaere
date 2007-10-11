package org.quaere.alias;

public class UpperLower<T> implements Function<T> {
    private Object object;
    private boolean upper;
    
    UpperLower(Object object, boolean upper) {
        this.object = object;
        this.upper = upper;
    }

    public Object getValue(QueryBase query) {
        Object o = query.getValue(object);
        if (o == null) {
            return null;
        }
        if (upper) {
            return o.toString().toUpperCase();
        } else {
            return o.toString().toLowerCase();
        }
    }
    
    
}
