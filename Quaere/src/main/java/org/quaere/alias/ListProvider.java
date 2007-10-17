package org.quaere.alias;

import java.util.Arrays;
import java.util.Comparator;
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
    
    public static final Object A = alias(), B = alias(), C = alias();
    public static final Object D = alias(), E = alias(), F = alias();
    public static final Object G = alias(), H = alias(), I = alias();
    public static final Object J = alias(), K = alias(), L = alias();
    public static final Object M = alias(), N = alias(), O = alias();
    public static final Object P = alias(), Q = alias(), R = alias();
    public static final Object S = alias(), T = alias(), U = alias();
    public static final Object V = alias(), W = alias(), X = alias();
    public static final Object Y = alias(), Z = alias();
    
    private static Object alias() {
        Object instance = new Object();
        FieldMapping mapping = new FieldMapping(Object.class, null, instance);
        addMapping(mapping, instance);
        return instance;
    }
    
    private static <T> T alias(Class<T> clazz, T instance, T[] array) {
        List<T> list = Arrays.asList(array);
        FieldMapping<T> mapping = new FieldMapping<T>(clazz, list, instance);
        addMapping(mapping, instance);
        return instance;
    }
    
    public static Integer alias(int[] array) {
        Integer[] a2 = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            a2[i] = array[i];
        }
        return alias(Integer.class, new Integer(0), a2);
    }
    
    /**
     * Create a new object alias for a given class.
     * TODO: the class needs to have a public constructor without parameters
     * TODO: each field in the class must be public
     */
    public static <T> T alias(Class<T> clazz, List<T> list) {
        T instance = Utils.createNew(clazz);
        FieldMapping<T> mapping = new FieldMapping<T>(clazz, list, instance);
        addMapping(mapping, instance);
        return instance;
    }

    public static <T> T alias(Class<T> clazz) {
        T instance = Utils.createNew(clazz);
        FieldMapping<T> mapping = new FieldMapping<T>(clazz, null, instance);
        addMapping(mapping, instance);
        return instance;
    }
    
    public static String alias(String[] array) {
        return alias(String.class, new String(), array);
    }
    
    public static <T> T template(Class<T> clazz) {
        return alias(clazz, null);
    }
    
    public static Query<Integer> from(int[] array, Object alias) {
        return from(Utils.toIntegerArray(array), alias);
    }
    
    public static Query<Double> from(double[] array, Object alias) {
        return from(Utils.toDoubleArray(array), alias);
    }
    
    public static <T> Query<T> from(T[] array, Object alias) {
        return from(Arrays.asList(array), alias);
    }
    
    public static <T> Query<T> from(List<T> list, Object alias) {
        FieldMapping<T> mapping = new FieldMapping<T>(null, list, alias);
        Selector selector = new Selector(alias, mapping, list);
        return new Query<T>(selector);
    }
    
    public static <T> Query<T> from(T alias) {
        FieldMapping mapping = getMapping(alias);
        List<T> list = mapping.getList();
        Selector selector = new Selector(alias, mapping, list);
        return new Query<T>(selector);
    }

//    public static QueryJoin from(Object ... aliases) {
//        ArrayList<Selector> selectors = new ArrayList<Selector>();
//        for (Object alias : aliases) {
//            FieldMapping mapping = null;
//            synchronized (ALIAS_MAP) {
//                mapping = ALIAS_MAP.get(alias);
//            }
//            if (mapping == null) {
//                throw new Error("Not an alias: " + alias);
//            }
//            List list = mapping.getList();
//            Selector s = new Selector(alias, mapping, list);
//            selectors.add(s);
//        }
//        return new QueryJoin(selectors);
//    }
    
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
    
    public static Object upper(Object o) {
        return new UpperLower(o, true);
    }

    public static Object lower(Object o) {
        return new UpperLower(o, false);
    }
    
    public static Object charAt(Object o, Object index) {
        return new CharAt(o, index);
    }

    public static <T> ConditionCompare<T> test(Object a, CompareType type, Object b) {
        return new ConditionCompare<T>(a, b, type);
    }
    
    public static <T> ConditionCompare<T> equal(Object a, Object b) {
        return test(a, CompareType.EQUAL, b);
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
    
    static void addMapping(FieldMapping mapping, Object instance) {
        synchronized (ALIAS_MAP) {
            ALIAS_MAP.put(instance, mapping);
        }
    }
    
    public static <T> Order<T> desc(Object o) {
        return new Order<T>(o, false, false, null);
    }

    public static <T> Order<T> desc(Object o, Comparator<T> comp) {
        return new Order<T>(o, false, false, comp);
    }

    public static <T> Order<T> asc(Object o) {
        return new Order<T>(o, true, true, null);
    }

    public static <T> Order<T> asc(Object o, Comparator<T> comp) {
        return new Order<T>(o, true, true, comp);
    }

}
