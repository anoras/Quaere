package org.quaere;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Convert {
    private static final String EMTPY_STRING = "";

    public static byte toByte(Object obj) {
        return (Byte) toType(obj, Byte.class);
    }

    public static short toShort(Object obj) {
        return (Short) toType(obj, Short.class);
    }

    public static int toInteger(Object obj) {
        return (Integer) toType(obj, Integer.class);
    }

    public static long toLong(Object obj) {
        return (Long) toType(obj, Long.class);
    }

    public static float toFloat(Object obj) {
        return (Float) toType(obj, Float.class);
    }

    public static double toDouble(Object obj) {
        return (Double) toType(obj, Double.class);
    }

    public static boolean toBoolean(Object obj) {
        if (isNullOrEmptyString(obj)) {
            return false;
        }
        else if (isBoolean(obj)) {
            return (Boolean) obj;
        }
        else if (isNumber(obj)) {
            return ((Number) obj).intValue() != 0;
        }
        else if (isCharSequence(obj)) {
            return Boolean.valueOf(toString(obj));
        }
        else {
            throw createConversionException(obj, Boolean.class);
        }
    }

    public static boolean isNumber(final Object obj) {
        return obj instanceof Number;
    }

    public static Object toType(Object obj, Class<?> toClass) {
        if (isInstance(obj, toClass)) {
            return obj;
        }
        if (isStringType(toClass)) {
            return toString(obj);
        }
        if (isNumberType(toClass)) {
            return toNumber(obj, (Class<? extends Number>) toClass);
        }
        if (isCharacterType(toClass)) {
            return toCharacter(obj);
        }
        if (isBooleanType(toClass)) {
            return toBoolean(obj);
        }
        throw createConversionException(obj, toClass);
    }

    public static boolean isInstance(final Object obj, final Class<?> toClass) {
        return toClass.isInstance(obj);
    }

    public static boolean isStringType(final Class<?> toClass) {
        return String.class.equals(toClass);
    }

    public static boolean isBooleanType(final Class<?> toClass) {
        return Boolean.class.equals(toClass);
    }

    public static boolean isCharacterType(final Class<?> toClass) {
        return Character.class.equals(toClass);
    }

    public static boolean isNumberType(Class clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    private static RuntimeException createConversionException(Object obj, Class toClass) {
        return new RuntimeException(String.format("Cannot convert %s to %s", obj.getClass().getName(), toClass.getName()));
    }

    public static Character toCharacter(Object obj) {
        if (isNullOrEmptyString(obj) || isBoolean(obj)) { // TODO why ignore boolean name?
            return (char) 0;
        }
        else if (isCharacter(obj)) {
            return (Character) obj;
        }
        if (isNumber(obj)) {
            return (char) ((Number) obj).shortValue();
        }
        else if (isCharSequence(obj)) {
            return ((String) obj).charAt(0);
        }
        else {
            throw createConversionException(obj, Character.class);
        }
    }

    public static boolean isCharacter(final Object obj) {
        return obj instanceof Character;
    }

    public static boolean isCharSequence(final Object obj) {
        return obj instanceof CharSequence;
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return EMTPY_STRING;
        }
        return obj.toString();
    }

    private static Number toNumber(Object obj, Class<? extends Number> toClass) {
        if (isNullOrEmptyString(obj)) {
            return toNumber(0, toClass);
        }
        else if (isCharacter(obj)) {
            char val = (Character) obj;
            return toNumber(new Short((short) val), toClass);
        }
        else if (isBoolean(obj)) {
            return (Boolean) obj ? toNumber(1, toClass) : toNumber(0, toClass);
        }
        else if (isInstance(obj, toClass)) {
            return (Number) obj;
        }
        else if (isNumber(obj)) {
            return toNumber((Number) obj, toClass);
        }
        else if (isCharSequence(obj)) {
            return toNumber((String) obj, toClass);
        }
        else {
            throw createConversionException(obj, toClass);
        }
    }

    public static boolean isBoolean(final Object obj) {
        return obj instanceof Boolean;
    }

    public static boolean isNullOrEmptyString(final Object obj) {
        return obj == null || EMTPY_STRING.equals(obj);
    }

    public static Number toNumber(String val, Class<? extends Number> toClass) {
        if (Byte.class.equals(toClass)) {
            return Byte.valueOf(val);
        }
        else if (Short.class.equals(toClass)) {
            return Short.valueOf(val);
        }
        else if (Integer.class.equals(toClass)) {
            return Integer.valueOf(val);
        }
        else if (Long.class.equals(toClass)) {
            return Long.valueOf(val);
        }
        else if (Float.class.equals(toClass)) {
            return Float.valueOf(val);
        }
        else if (Double.class.equals(toClass)) {
            return Double.valueOf(val);
        }
        else if (BigInteger.class.equals(toClass)) {
            return new BigInteger(val);
        }
        else if (BigDecimal.class.equals(toClass)) {
            return new BigDecimal(val);
        }
        else {
            throw createConversionException(val, toClass);
        }
    }

    public static Number toNumber(Number val, Class<? extends Number> toClass) {
        if (isInstance(val, toClass)) {
            return val;
        }
        if (Byte.class.equals(toClass)) {
            return Primitives.getByte(val.byteValue());
        }
        else if (Short.class.equals(toClass)) {
            return Primitives.getShort(val.shortValue());
        }
        else if (Integer.class.equals(toClass)) {
            return Primitives.getInteger(val.intValue());
        }
        else if (Long.class.equals(toClass)) {
            return Primitives.getLong(val.longValue());
        }
        else if (Float.class.equals(toClass)) {
            return Primitives.getFloat(val.floatValue());
        }
        else if (Double.class.equals(toClass)) {
            return Primitives.getDouble(val.doubleValue());
        }
        else if (BigInteger.class.equals(toClass)) {
            if (val instanceof BigDecimal) {
                return ((BigDecimal) val).toBigInteger();
            }
            else {
                return BigInteger.valueOf(val.longValue());
            }
        }
        else if (BigDecimal.class.equals(toClass)) {
            if (val instanceof BigInteger) {
                return new BigDecimal((BigInteger) val);
            }
            else {
                return new BigDecimal(val.doubleValue());
            }
        }
        else {
            throw createConversionException(val, toClass);
        }
    }

    public static Number toNumber(long val, Class<? extends Number> toClass) {
        if (Byte.class.equals(toClass)) {
            return Primitives.getByte((byte) val);
        }
        else if (Short.class.equals(toClass)) {
            return Primitives.getShort((short) val);
        }
        else if (Integer.class.equals(toClass)) {
            return Primitives.getInteger((int) val);
        }
        else if (Long.class.equals(toClass)) {
            return Primitives.getLong((val));
        }
        else if (Float.class.equals(toClass)) {
            return Primitives.getFloat((float) val);
        }
        else if (Double.class.equals(toClass)) {
            return Primitives.getDouble((double) val);
        }
        else if (BigInteger.class.equals(toClass)) {
            return BigInteger.valueOf(Primitives.getLong(val));
        }
        else if (BigDecimal.class.equals(toClass)) {
            return BigDecimal.valueOf(Primitives.getLong(val));
        }
        else {
            throw createConversionException(val, toClass);
        }

    }
}
