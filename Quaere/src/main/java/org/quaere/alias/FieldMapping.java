package org.quaere.alias;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.List;


public class FieldMapping<T> {
    private final Class<T> clazz;
    private final List<T> list;
    private final IdentityHashMap<Object, String> fieldMap = new IdentityHashMap<Object, String>();
    private static final String FIELDNAME_ITSELF = "";
    
    public FieldMapping(Class<T> clazz, List<T> list, T instance, boolean basic) {
        this.clazz = clazz;
        this.list = list;
        if (basic) {
            fieldMap.put(instance, FIELDNAME_ITSELF);
        } else {
            Field[] fields = instance.getClass().getFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                Class type = f.getType();
                try {
                    Object obj = Utils.createNew(type);
                    fieldMap.put(obj, f.getName());
                    f.set(instance, obj);
                } catch (IllegalAccessException e) {
                    throw new Error("IllegalAccessException setting " + instance.getClass().getName()+"."+ f.getName());
                }
            }
        }
    }
    
    public List<T> getList() {
        return list;
    }

    Object getValue(Object o, T t) {
        String fieldName = fieldMap.get(o);
        if (fieldName == null) {
            return o;
        } else if (FieldMapping.FIELDNAME_ITSELF.equals(fieldName)) {
            return t;
        } else {
            try {
                Field f = t.getClass().getField(fieldName);
                return f.get(t);
            } catch (Exception e) {
                throw new Error("Error accessing field " + fieldName);
            }
        }
    }

    public T convert(Object object) {
        return convert(object, clazz);
    }

    @SuppressWarnings("unchecked")
    public <U> U convert(Object object, Class<U> clazz) {
        if (clazz == Operation.class) {
            return (U) convert(object, this.clazz);
        } else if (clazz == Get.class) {
            return (U) object;
        }
        if (clazz.isAssignableFrom(object.getClass())) {
            return (U) object;
        }
        if (clazz == Integer.class) {
            if (object instanceof Number) {
                return (U) Integer.valueOf(((Number) object).intValue());
            }
        } else if (clazz == String.class) {
            return (U) object.toString();
        }
        throw new Error("Can not convert " + object.getClass() + " to " + clazz);
    }

    Class<T> getObjectClass() {
        return clazz;
    }

    String getFieldName(Object field) {
        return fieldMap.get(field);
    }

}
