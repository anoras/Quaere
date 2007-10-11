package org.quaere.alias;

import java.lang.reflect.Field;

public class Assign {

    private Object field;
    private Object value;
    
    public Assign(Object field, Object value) {
        this.field = field;
        this.value = value;
    }
    
    <T, U> void set(QueryBase query, FieldMapping<U> mapping, U target) {
        Object v = query.getValue(value);
        Field f = mapping.getField(field);
        try {
            f.set(target, v);
        } catch (Exception e) {
            throw new Error("Can not assign " + v + " to " + target.getClass() + "." + field, e);
        }
    }

}
