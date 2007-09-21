package org.quaere;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Convert {
    public static byte toByte(Object obj) {
        return (Byte) coerce(obj, Byte.class);
    }
    public static short toShort(Object obj) {
        return (Short) coerce(obj, Short.class);
    }
    public static int toInteger(Object obj) {
        return (Integer) coerce(obj, Integer.class);
    }
    public static long toLong(Object obj) {
        return (Long) coerce(obj, Long.class);
    }
    public static float toFloat(Object obj) {
        return (Float) coerce(obj, Float.class);
    }
    public static double toDouble(Object obj) {
        return (Double) coerce(obj, Double.class);
    }
    public static boolean toBoolean(Object obj) {
        if (obj == null || "".equals(obj)) {
            return Boolean.FALSE;
        } else if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else if (obj instanceof String) {
            return Boolean.valueOf((String) obj);
        } else {
            throw createCoercionException(obj, Boolean.class);
        }
    }
    @SuppressWarnings({"unchecked"})
    public static Object coerce(Object obj, Class<? extends Object> toClass) {
        if (String.class.equals(toClass)) {
            return toString(obj);
        } else if (isNumber(toClass)) {
            return toNumber(obj, (Class<? extends Number>) toClass);
        } else if (Character.class.equals(toClass)) {
            return toCharacter(obj);
        } else if (Boolean.class.equals(toClass)) {
            return toBoolean(obj);
        } else {
            throw createCoercionException(obj, toClass);
        }
    }
    private static boolean isNumber(Class clazz) {
        return clazz.equals(Byte.class) || clazz.equals(Short.class) ||
                clazz.equals(Integer.class) || clazz.equals(Long.class) ||
                clazz.equals(Float.class) || clazz.equals(Double.class) ||
                clazz.equals(BigInteger.class) || clazz.equals(BigDecimal.class);
    }
    private static RuntimeException createCoercionException(Object obj, Class toClass) {
        return new RuntimeException(String.format("Cannot coerce %s to %s", obj.getClass().getName(), toClass.getName()));
    }

    private static Character toCharacter(Object obj) {
        if (obj == null || "".equals(obj) || obj instanceof Boolean) {
            return (char) 0;
        } else if (obj instanceof Character) return (Character) obj;
        if (obj instanceof Number) {
            return (char) ((Number) obj).shortValue();
        } else if (obj instanceof String) {
            return ((String) obj).charAt(0);
        } else {
            throw createCoercionException(obj, Character.class);
        }
    }
    private static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return obj.toString();
        }
    }
    private static Number toNumber(Object obj, Class<? extends Number> toClass) {
        if (obj == null || "".equals(obj)) {
            return toNumber(0, toClass);
        } else if (obj instanceof Character) {
            char val = (Character) obj;
            return toNumber(new Short((short) val), toClass);
        } else if (obj instanceof Boolean) {
            return (Boolean) obj ? toNumber(1, toClass) : toNumber(0, toClass);
        } else if (toClass.equals(obj.getClass())) {
            return (Number) obj;
        } else if (obj instanceof Number) {
            return toNumber((Number) obj, toClass);
        } else if (obj instanceof String) {
            return toNumber((String) obj, toClass);
        } else {
            throw createCoercionException(obj, toClass);
        }
    }
    public static Number toNumber(String val, Class<? extends Number> toClass) {
        if (Byte.class.equals(toClass)) {
            return Byte.valueOf(val);
        } else if (Short.class.equals(toClass)) {
            return Short.valueOf(val);
        } else if (Integer.class.equals(toClass)) {
            return Integer.valueOf(val);
        } else if (Long.class.equals(toClass)) {
            return Long.valueOf(val);
        } else if (Float.class.equals(toClass)) {
            return Float.valueOf(val);
        } else if (Double.class.equals(toClass)) {
            return Double.valueOf(val);
        } else if (BigInteger.class.equals(toClass)) {
            return new BigInteger(val);
        } else if (BigDecimal.class.equals(toClass)) {
            return new BigDecimal(val);
        } else {
            throw createCoercionException(val, toClass);
        }
    }
    public static Number toNumber(Number val, Class<? extends Number> toClass) {
        if (Byte.class.equals(toClass)) {
            return Primitives.getByte(val.byteValue());
        } else if (Short.class.equals(toClass)) {
            return Primitives.getShort(val.shortValue());
        } else if (Integer.class.equals(toClass)) {
            return Primitives.getInteger(val.intValue());
        } else if (Long.class.equals(toClass)) {
            return Primitives.getLong(val.longValue());
        } else if (Float.class.equals(toClass)) {
            return Primitives.getFloat(val.floatValue());
        } else if (Double.class.equals(toClass)) {
            return Primitives.getDouble(val.doubleValue());
        } else if (BigInteger.class.equals(toClass)) {
            if (val instanceof BigDecimal) {
                return ((BigDecimal) val).toBigInteger();
            } else {
                return BigInteger.valueOf(val.longValue());
            }
        } else if (BigDecimal.class.equals(toClass)) {
            if (val instanceof BigInteger) {
                return new BigDecimal((BigInteger) val);
            } else {
                return new BigDecimal(val.doubleValue());
            }
        } else {
            throw createCoercionException(val, toClass);
        }
    }
    public static Number toNumber(long val, Class<? extends Number> toClass) {
        if (Byte.class.equals(toClass)) {
            return Primitives.getByte((byte) val);
        } else if (Short.class.equals(toClass)) {
            return Primitives.getShort((short) val);
        } else if (Integer.class.equals(toClass)) {
            return Primitives.getInteger((int) val);
        } else if (Long.class.equals(toClass)) {
            return Primitives.getLong((val));
        } else if (Float.class.equals(toClass)) {
            return Primitives.getFloat((float) val);
        } else if (Double.class.equals(toClass)) {
            return Primitives.getDouble((double) val);
        } else if (BigInteger.class.equals(toClass)) {
            return BigInteger.valueOf(Primitives.getLong(val));
        } else if (BigDecimal.class.equals(toClass)) {
            return BigDecimal.valueOf(Primitives.getLong(val));
        } else {
            throw createCoercionException(val, toClass);
        }

    }
}
