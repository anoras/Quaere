package org.quaere.alias;

import java.util.Comparator;

public class Order<T> {
    private final Object item;
    private final boolean ascending;
    private final Comparator<T> comparator;
    private boolean nullsFirst;
    
    Order(Object item, boolean ascending, boolean nullsFirst, Comparator<T> comparator) {
        this.item = item;
        this.ascending = ascending;
        this.nullsFirst = nullsFirst;
        this.comparator = comparator;
    }
    
    public Order nullsFirst() {
        this.nullsFirst = true;
        return this;
    }

    public Order nullsLast() {
        this.nullsFirst = false;
        return this;
    }
    
    Object getItem() {
        return item;
    }
    
    int compare(T a, T b) {
        boolean aNull = a == null, bNull = b == null;
        if (aNull || bNull) {
            if (aNull == bNull) {
                return 0;
            }
            if (nullsFirst) {
                return aNull ? -1 : 1;
            } else {
                return aNull ? 1 : -1;
            }
        }
        Class clazz = Utils.getHigherClass(a, b);
        a = (T) Utils.convert(a, clazz);
        b = (T) Utils.convert(b, clazz);
        int comp;
        if (comparator != null) {
            comp = comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            Comparable ca = (Comparable) a;
            comp = ca.compareTo(b);
        } else {
            comp = a.toString().compareTo(b.toString());
        }
        return ascending ? comp : -comp;
    }
}
