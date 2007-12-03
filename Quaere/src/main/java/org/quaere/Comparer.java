package org.quaere;

import java.util.Comparator;

public class Comparer {
    public static int compare(Object a, Object b) {
        if (a == b) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        if (!(a instanceof Comparable)) {
            throw new IllegalArgumentException("Argument must implement Comparable");
        }
        Comparable comparableA = (Comparable) a;
        return comparableA.compareTo(Convert.toType(b, a.getClass()));
    }
    public static int compare(Object a, Object b, Comparator comparator) {
        if (comparator == null) {
            return compare(a, b);
        } else {
            return comparator.compare(a, b);
        }
    }
}
