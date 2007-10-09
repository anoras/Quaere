package org.quaere.alias;

import java.lang.reflect.Field;

public class Assign {

    private Object field;
    private Object value;
    
    public Assign(Object field, Object value) {
        this.field = field;
        this.value = value;
    }
    
    <T, U> void set(Query<T> query, FieldMapping<U> mapping, U target, T item) {
        Object v = query.getValue(value, item);
        String fieldName = mapping.getFieldName(field);
        try {
            Field f = target.getClass().getField(fieldName);
            f.set(target, v);
        } catch (Exception e) {
            throw new Error("Can not assign " + v + " to " + target.getClass() + "." + fieldName, e);
        }
    }

}
