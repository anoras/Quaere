package org.quaere.alias;

public class Get<U> implements Function<U> {
    
    private U[] list;
    private Object index;
    
    Get(U[] list, Object index) {
        this.list = list;
        this.index = index;
    }

    public Object getValue(QueryBase query, U t) {
        Object o = query.getValue(index, t);
        return list[((Number) o).intValue()];
    }

}
