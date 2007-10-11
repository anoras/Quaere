package org.quaere.alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provider implementation for object lists.
 * 
 * @author Thomas Mueller
 */
public class ListProvider {
    
    private static final WeakIdentityHashMap<Object, FieldMapping> ALIAS_MAP = new WeakIdentityHashMap<Object, FieldMapping>();
    
    private ListProvider() {
    }
    
    /**
     * Create a new object alias for a given class.
     * TODO: the class needs to have a public constructor without parameters
     * TODO: each field must be public in the class
     */
    public static <T> T alias(Class<T> clazz, List<T> list) {
        T instance = Utils.createNew(clazz);
        FieldMapping<T> mapping = new FieldMapping<T>(clazz, list, instance, false);
        synchronized (ALIAS_MAP) {
            ALIAS_MAP.put(instance, mapping);
        }
        return instance;
    }
    
    public static Order desc(Object o) {
        return new Order(o, false, false);
    }

    public static Order asc(Object o) {
        return new Order(o, true, true);
    }

    public static String alias(String[] array) {
        return alias(String.class, new String(), array);
    }
    
    public static <T> T template(Class<T> clazz) {
        return alias(clazz, null);
    }
    
    public static Assign set(Object object, Object value) {
        return new Assign(object, value);
    }
    
    public static Function length(Object o) {
        return new Length(o);
    }
    
    public static Function index() {
        return new Index();
    }
    
    public static Operation add(Object a, Object b) {
        return new Operation(a, b, OperationType.PLUS);
    }
    
    public static Operation mod(Object a, Object b) {
        return new Operation(a, b, OperationType.MODULO);
    }
    
    public static <T> Get get(T[] list, Object index) {
        return new Get<T>(list, index);
    }
    
    private static <T> T alias(Class<T> clazz, T instance, T[] array) {
        List<T> list = Arrays.asList(array);
        FieldMapping<T> mapping = new FieldMapping<T>(clazz, list, instance, true);
        synchronized (ALIAS_MAP) {
            ALIAS_MAP.put(instance, mapping);
        }
        return instance;
    }
    
    public static Object upper(Object o) {
        return new UpperLower(o, true);
    }

    public static Object lower(Object o) {
        return new UpperLower(o, false);
    }

    public static Integer alias(int[] array) {
        Integer[] a2 = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            a2[i] = array[i];
        }
        return alias(Integer.class, new Integer(0), a2);
    }
    
    public static <T> ConditionCompare<T> test(Object a, CompareType type, Object b) {
        return new ConditionCompare<T>(a, b, type);
    }
    
    public static <T> ConditionCompare<T> equal(Object a, Object b) {
        return test(a, CompareType.EQUAL, b);
    }
    
    public static <T> Query<T> from(T alias) {
        FieldMapping mapping = null;
        synchronized (ALIAS_MAP) {
            mapping = ALIAS_MAP.get(alias);
        }
        if (mapping == null) {
            throw new Error("Not an alias: " + alias);
        }
        List<T> list = mapping.getList();
        Selector selector = new Selector(alias, mapping, list);
        return new Query<T>(selector);
    }

    public static QueryJoin from(Object ... aliases) {
        ArrayList<Selector> selectors = new ArrayList<Selector>();
        for (Object alias : aliases) {
            FieldMapping mapping = null;
            synchronized (ALIAS_MAP) {
                mapping = ALIAS_MAP.get(alias);
            }
            if (mapping == null) {
                throw new Error("Not an alias: " + alias);
            }
            List list = mapping.getList();
            Selector s = new Selector(alias, mapping, list);
            selectors.add(s);
        }
        return new QueryJoin(selectors);
    }

    static <T> FieldMapping<T> getMapping(Object template) {
        FieldMapping mapping = null;
        synchronized (ALIAS_MAP) {
            mapping = ALIAS_MAP.get(template);
        }
        if (mapping == null) {
            throw new Error("Not an template: " + template);
        }
        return mapping;
    }
    
}
