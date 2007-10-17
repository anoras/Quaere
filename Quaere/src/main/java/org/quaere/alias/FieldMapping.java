package org.quaere.alias;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.List;


public class FieldMapping<T> {
    private final Class clazz;
    private final List<T> list;
    private final IdentityHashMap<Object, Field> fieldMap = new IdentityHashMap<Object, Field>();

    /*
     * This is a special field. It could be any, but it must be one that is not used by the application
     */
    private static final Field FIELD_ITSELF;
    
    static {
        try {
            FIELD_ITSELF = System.class.getField("out");
        } catch (Exception e) {
            throw new Error("Field System.out not found: " + e);
        }
    }
    
    public FieldMapping(Class clazz, List<T> list, Object instance) {
        this.clazz = clazz;
        this.list = list;
        boolean basic = Utils.isBasicClass(instance.getClass());
        if (basic) {
            fieldMap.put(instance, FIELD_ITSELF);
        } else {
            Field[] fields = instance.getClass().getFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                Class type = f.getType();
                try {
                    Object obj = Utils.createNew(type);
                    fieldMap.put(obj, f);
                    f.set(instance, obj);
                } catch (IllegalAccessException e) {
                    throw new Error("IllegalAccessException setting " + instance.getClass().getName()+"."+ f.getName());
                }
            }
            if (Utils.MAP_NON_PUBLIC_FIELDS) {
                // TODO only new fields
                fields = instance.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    if ((f.getModifiers() & Modifier.PUBLIC) != 0) {
                        continue;
                    }
                    f.setAccessible(true);
                    Class type = f.getType();
                    try {
                        Object obj = Utils.createNew(type);
                        fieldMap.put(obj, f);
                        f.set(instance, obj);
                    } catch (IllegalAccessException e) {
                        throw new Error("IllegalAccessException setting " + instance.getClass().getName()+"."+ f.getName());
                    }
                }
            }
        }
    }
    
    public List<T> getList() {
        return list;
    }

    Object getValue(Object o, T t) {
        Field field = fieldMap.get(o);
        if (field == null) {
            return o;
        } else if (FieldMapping.FIELD_ITSELF == field) {
            return t;
        } else {
            try {
                return field.get(t);
            } catch (Exception e) {
                throw new Error("Error accessing field " + field.getName());
            }
        }
    }

    public T convert(Object object) {
        if (clazz == null) {
            return (T) object;
        } else {
            return (T) convert(object, clazz);
        }
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

    Field getField(Object field) {
        return fieldMap.get(field);
    }

}
