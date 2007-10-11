package org.quaere.alias;

public class Order {
    private final Object item;
    private final boolean ascending;
    private boolean nullsFirst;
    
    Order(Object item, boolean ascending, boolean nullsFirst) {
        this.item = item;
        this.ascending = ascending;
        this.nullsFirst = nullsFirst;
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
    
    int compare(Object a, Object b) {
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
        a = Utils.convert(a, clazz);
        b = Utils.convert(b, clazz);
        int comp;
        if (a instanceof Comparable) {
            Comparable ca = (Comparable) a;
            comp = ca.compareTo(b);
        } else {
            comp = a.toString().compareTo(b.toString());
        }
        return ascending ? comp : -comp;
    }
}
