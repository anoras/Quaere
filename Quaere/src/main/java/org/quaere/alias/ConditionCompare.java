package org.quaere.alias;

public class ConditionCompare<T> extends Condition<T> {
    private Object left;
    private Object right;
    private CompareType type;
    
    public ConditionCompare(Object left, Object  right, CompareType type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }
    
    boolean test(QueryBase query) {
        Object a = query.getValue(left);
        Object b = query.getValue(right);
        return type.test(a, b);
    }
    
    public Condition and(Object a, CompareType type, Object b) {
        return new ConditionAnd<T>(this, new ConditionCompare<T>(a, b, type));
    }
}
