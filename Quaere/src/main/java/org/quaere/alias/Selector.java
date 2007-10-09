package org.quaere.alias;

import java.util.List;

class Selector<T> {
    private FieldMapping<T> mapping;
    private T instance;
    private List<T> list;
    private Selector join;

    Selector(T instance, FieldMapping<T> mapping, List<T> list) {
        this.instance = instance;
        this.mapping = mapping;
        this.list = list;
    }

    T getInstance() {
        return instance;
    }

    List<T> getList() {
        return list;
    }

    FieldMapping<T> getMapping() {
        return mapping;
    }
}
