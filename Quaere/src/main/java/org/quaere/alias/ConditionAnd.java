package org.quaere.alias;

public class ConditionAnd<T> extends Condition<T> {

    private Condition<T> left, right;
    
    ConditionAnd(Condition<T> left, Condition<T> right) {
        this.left = left;
        this.right = right;
    }

    boolean test(T t, QueryBase query) {
        return left.test(t, query) && right.test(t, query);
    }
    
}
