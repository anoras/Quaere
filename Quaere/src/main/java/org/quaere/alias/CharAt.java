package org.quaere.alias;

public class CharAt<T> implements Function<T> {

    private Object object;
    private Object index;
    
    CharAt(Object object, Object index) {
        this.object = object;
        this.index = index;
    }
    
    public Object getValue(QueryBase query) {
        Object o = query.getValue(object);
        int i = ((Number) query.getValue(index)).intValue();
        return o == null ? null : o.toString().charAt(i);
    }

}
