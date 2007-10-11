package org.quaere.alias;

import java.util.List;

class Selector<T> {
    private FieldMapping<T> mapping;
    private T instance;
    private List<T> list;
    private Selector<T> join;
    private T currentItem;

    Selector(T instance, FieldMapping<T> mapping, List<T> list) {
        this.instance = instance;
        this.mapping = mapping;
        this.list = list;
    }

    T getInstance() {
        return instance;
    }
    
    void setJoin(Selector<T> join) {
        this.join = join;
    }

    List<T> getList() {
        return list;
    }
    
    void iterate(ListVisitor<T> visitor) {
        for (T item : list) {
            currentItem = item;
            if (join == null) {
                visitor.visit();
            } else {
                join.iterate(visitor);
            }
        }
    }
    
    T getCurrentItem() {
        return currentItem;
    }

    FieldMapping<T> getMapping() {
        return mapping;
    }
}
