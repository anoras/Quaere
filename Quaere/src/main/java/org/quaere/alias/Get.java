package org.quaere.alias;

public class Get<T> implements Function<T> {
    
    private T[] list;
    private Object index;
    
    Get(T[] list, Object index) {
        this.list = list;
        this.index = index;
    }

    public Object getValue(QueryBase query) {
        Object o = query.getValue(index);
        return list[((Number) o).intValue()];
    }

}
