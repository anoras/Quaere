package org.quaere;

public class Primitives {
    static int BYTE_LOWER_BOUND = 0;
    static int BYTE_UPPER_BOUND = 255;
    static int CHARACTER_LOWER_BOUND = 0;
    static int CHARACTER_UPPER_BOUND = 255;
    static int SHORT_LOWER_BOUND = -1000;
    static int SHORT_UPPER_BOUND = 1000;
    static int INTEGER_LOWER_BOUND = -1000;
    static int INTEGER_UPPER_BOUND = 1000;
    static int LONG_LOWER_BOUND = -1000;
    static int LONG_UPPER_BOUND = 1000;

    //-------------------------------------
    // Member variables
    //-------------------------------------

    static Byte[] mBytes = createBytes();
    static Character[] mCharacters = createCharacters();
    static Short[] mShorts = createShorts();
    static Integer[] mIntegers = createIntegers();
    static Long[] mLongs = createLongs();

    //-------------------------------------
    // Getting primitive values
    //-------------------------------------
    public static Boolean getBoolean(boolean pValue) {
        return
                pValue ?
                        Boolean.TRUE :
                        Boolean.FALSE;
    }

    //-------------------------------------
    public static Byte getByte(byte pValue) {
        if (pValue >= BYTE_LOWER_BOUND &&
                pValue <= BYTE_UPPER_BOUND) {
            return mBytes[((int) pValue) - BYTE_LOWER_BOUND];
        } else {
            return pValue;
        }
    }

    //-------------------------------------
    public static Character getCharacter(char pValue) {
        if (pValue >= CHARACTER_LOWER_BOUND &&
                pValue <= CHARACTER_UPPER_BOUND) {
            return mCharacters[((int) pValue) - CHARACTER_LOWER_BOUND];
        } else {
            return pValue;
        }
    }

    //-------------------------------------
    public static Short getShort(short pValue) {
        if (pValue >= SHORT_LOWER_BOUND &&
                pValue <= SHORT_UPPER_BOUND) {
            return mShorts[((int) pValue) - SHORT_LOWER_BOUND];
        } else {
            return pValue;
        }
    }

    //-------------------------------------
    public static Integer getInteger(int pValue) {
        if (pValue >= INTEGER_LOWER_BOUND &&
                pValue <= INTEGER_UPPER_BOUND) {
            return mIntegers[((int) pValue) - INTEGER_LOWER_BOUND];
        } else {
            return pValue;
        }
    }

    //-------------------------------------
    public static Long getLong(long pValue) {
        if (pValue >= LONG_LOWER_BOUND &&
                pValue <= LONG_UPPER_BOUND) {
            return mLongs[((int) pValue) - LONG_LOWER_BOUND];
        } else {
            return pValue;
        }
    }

    //-------------------------------------
    public static Float getFloat(float pValue) {
        return pValue;
    }

    //-------------------------------------
    public static Double getDouble(double pValue) {
        return pValue;
    }


    public static Class getPrimitiveObjectClass(Class pClass) {
        if (pClass == Boolean.TYPE) {
            return Boolean.class;
        } else if (pClass == Byte.TYPE) {
            return Byte.class;
        } else if (pClass == Short.TYPE) {
            return Short.class;
        } else if (pClass == Character.TYPE) {
            return Character.class;
        } else if (pClass == Integer.TYPE) {
            return Integer.class;
        } else if (pClass == Long.TYPE) {
            return Long.class;
        } else if (pClass == Float.TYPE) {
            return Float.class;
        } else if (pClass == Double.TYPE) {
            return Double.class;
        } else {
            return pClass;
        }
    }

    //-------------------------------------
    // Initializing the cached values
    //-------------------------------------
    static Byte[] createBytes() {
        int len = BYTE_UPPER_BOUND - BYTE_LOWER_BOUND + 1;
        Byte[] ret = new Byte[len];
        byte val = (byte) BYTE_LOWER_BOUND;
        for (int i = 0; i < len; i++, val++) {
            ret[i] = new Byte(val);
        }
        return ret;
    }

    //-------------------------------------
    static Character[] createCharacters() {
        int len = CHARACTER_UPPER_BOUND - CHARACTER_LOWER_BOUND + 1;
        Character[] ret = new Character[len];
        char val = (char) CHARACTER_LOWER_BOUND;
        for (int i = 0; i < len; i++, val++) {
            ret[i] = new Character(val);
        }
        return ret;
    }

    //-------------------------------------
    static Short[] createShorts() {
        int len = SHORT_UPPER_BOUND - SHORT_LOWER_BOUND + 1;
        Short[] ret = new Short[len];
        short val = (short) SHORT_LOWER_BOUND;
        for (int i = 0; i < len; i++, val++) {
            ret[i] = new Short(val);
        }
        return ret;
    }

    //-------------------------------------
    static Integer[] createIntegers() {
        int len = INTEGER_UPPER_BOUND - INTEGER_LOWER_BOUND + 1;
        Integer[] ret = new Integer[len];
        int val = (int) INTEGER_LOWER_BOUND;
        for (int i = 0; i < len; i++, val++) {
            ret[i] = new Integer(val);
        }
        return ret;
    }

    //-------------------------------------
    static Long[] createLongs() {
        int len = LONG_UPPER_BOUND - LONG_LOWER_BOUND + 1;
        Long[] ret = new Long[len];
        long val = (long) LONG_LOWER_BOUND;
        for (int i = 0; i < len; i++, val++) {
            ret[i] = new Long(val);
        }
        return ret;
    }

}
