package org.quaere;

public class Convert {
    public static <T extends Object> Integer toInteger(T value) {
        Class clazz = value.getClass();
        if (clazz.equals(Integer.class)) {
            return (Integer) value;
        } else if (clazz.equals(Double.class)) {
            String asString = value.toString();
            return Integer.valueOf(asString.substring(0,asString.indexOf('.')));
        }
        throw new UnsupportedOperationException(String.format("Cannot convert %s to double", clazz.getName()));
    }
    public static <T extends Object> double toDouble(T value) {
        Class clazz = value.getClass();
        if (clazz.equals(Double.class)) {
            return (Double) value;
        } else if (clazz.equals(Integer.class)) {
            return Double.valueOf(value.toString());
        }
        throw new UnsupportedOperationException(String.format("Cannot convert %s to double", clazz.getName()));
    }
    public static <T extends Object> Object coerce(T value, Class toClass) {
        if (toClass.equals(value.getClass())) return value;
        if (toClass.equals(Integer.class))
        {
            return toInteger(value);
        }
        else if (toClass.equals(Double.class))
        {
            return toDouble(value);
        }
        throw new RuntimeException(String.format("Convert.coerce is not implemented for %s to %s",value.getClass().getName(),toClass.getName()));
    }
}
