package org.quaere.alias;

public class Length<T> implements Function<T> {

    private Object object;
    
    Length(Object object) {
        this.object = object;
    }
    
    public Object getValue(QueryBase query, T t) {
        Object o = query.getValue(object, t);
        return o == null ? null : o.toString().length();
    }

}
