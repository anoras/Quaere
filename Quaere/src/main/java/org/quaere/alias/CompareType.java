package org.quaere.alias;

public enum CompareType {
    EQUAL("=", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) == 0;
        }
    },
    BIGGER(">", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) > 0;
        }
    },
    BIGGER_EQUAL(">=", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) >= 0;
        }        
    },
    SMALLER("<", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) < 0;
        }
    },
    SMALLER_EQUAL("<=", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) <= 0;
        }
    },
    NOT_EQUAL("<>", true) {
        public boolean test(Object a, Object b) {
            return compare(a, b) != 0;
        }
    },
    IS_NOT_NULL("IS NOT NULL", false) {
        public boolean test(Object a, Object b) {
            return a != null;
        }
    },
    IS_NULL("IS NULL", false) {
        public boolean test(Object a, Object b) {
            return a == null;
        }
    };
//    LIKE("LIKE", true);

    CompareType(String text, boolean hasValue) {
        this.text = text;
        this.hasValue = hasValue;
    }
    private String text;
    private boolean hasValue;
    public String toString() {
        return text;
    }
    public boolean hasValue() {
        return hasValue;
    }
    public abstract boolean test(Object a, Object b);
    
    protected int compare(Object a, Object b) {
        Class clazz = Utils.getHigherClass(a, b);
        a = Utils.convert(a, clazz);
        b = Utils.convert(b, clazz);
        return ((Comparable) a).compareTo((Comparable) b);
    }
    
}
