package org.quaere.alias;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class Utils {
    static <T> ArrayList<T> createArrayList() {
        return new ArrayList<T>();
    }
    
    static <T> ArrayList<T> createArrayList(int size) {
        return new ArrayList<T>(size);
    }
    
    static <T, U> WeakHashMap<T, U> createWeakHashMap() {
        return new WeakHashMap<T, U>();
    }
    
    @SuppressWarnings("unchecked")
    static <T> T createNew(Class<T> clazz) {
        if (clazz == Integer.class) {
            return (T) new Integer(0);
        } else if (clazz == Double.class) {
            return (T) new Double(0);
        } else if (clazz == Boolean.class) {
            return (T) new Boolean(false);
        }
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException e) {
            throw new Error("IllegalAccessException creating " + clazz.getName());
        } catch (InstantiationException e) {
            throw new Error("InstantiationException creating " + clazz.getName());
        }
    }

    public static Class getHigherClass(Object a, Object b) {
        int oa = getOrder(a), ob = getOrder(b);
        if (oa > ob) {
            return a.getClass();
        } else {
            return b.getClass();
        }
    }

    private static int getOrder(Object a) {
        if (a == null) {
            return 0;
        }
        Class clazz = a.getClass();
        if (clazz == Boolean.class) {
            return 1;
        } else if (clazz == Byte.class) {
            return 2;
        } else if (clazz == Character.class) {
            return 3;
        } else if (clazz == Short.class) {
            return 4;
        } else if (clazz == Integer.class) {
            return 5;
        } else if (clazz == Long.class) {
            return 6;
        } else if (clazz == BigInteger.class) {
            return 7;
        } else if (clazz == Float.class) {
            return 8;
        } else if (clazz == Double.class) {
            return 9;
        } else if (clazz == BigDecimal.class) {
            return 10;
        } else if (clazz == String.class) {
            return 11;
        } else {
            return 12;
        }
    }

    public static Object convert(Object a, Class clazz) {
        if (a == null || a.getClass() == clazz) {
            return a;
        }
        if (clazz == Boolean.class) {
            if (a instanceof Number) {
                return Boolean.valueOf(((Number) a).intValue() != 0);
            } else {
                return Boolean.valueOf(a.toString());
            }
        } else if (clazz == Byte.class) {
            if (a instanceof Number) {
                return Byte.valueOf(((Number) a).byteValue());
            } else {
                return Byte.valueOf(a.toString());
            }
        } else if (clazz == Character.class) {
            if (a instanceof Number) {
                return Character.valueOf((char) ((Number) a).intValue());
            } else {
                return Character.valueOf((char) Integer.parseInt(a.toString()));
            }
        } else if (clazz == Short.class) {
            if (a instanceof Number) {
                return Short.valueOf(((Number) a).shortValue());
            } else {
                return Short.valueOf(a.toString());
            }
        } else if (clazz == Integer.class) {
            if (a instanceof Number) {
                return Integer.valueOf(((Number) a).intValue());
            } else {
                return Integer.valueOf(a.toString());
            }
        } else if (clazz == Long.class) {
            if (a instanceof Number) {
                return Long.valueOf(((Number) a).longValue());
            } else {
                return Long.valueOf(a.toString());
            }
        } else if (clazz == BigInteger.class) {
            if (a instanceof Number) {
                return BigInteger.valueOf(((Number) a).longValue());
            } else {
                return new BigInteger(a.toString());
            }
        } else if (clazz == Float.class) {
            if (a instanceof Number) {
                return Float.valueOf(((Number) a).floatValue());
            } else {
                return Float.valueOf(a.toString());
            }
        } else if (clazz == Double.class) {
            if (a instanceof Number) {
                return Double.valueOf(((Number) a).doubleValue());
            } else {
                return Double.valueOf(a.toString());
            }
        } else if (clazz == BigDecimal.class) {
            if (a instanceof Number) {
                return BigDecimal.valueOf(((Number) a).doubleValue());
            } else {
                return new BigDecimal(a.toString());
            }
        } else if (clazz == String.class) {
            return a.toString();
        } else {
            throw new Error("Unsupported convertion from " + a.getClass() + " to " + clazz);
        }
    }
}
