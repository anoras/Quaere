package org.quaere.alias;

public class ConditionOr<T> extends Condition<T> {

    private Condition<T> left, right;
    
    ConditionOr(Condition<T> left, Condition<T> right) {
        this.left = left;
        this.right = right;
    }

    boolean test(T t, QueryBase query) {
        return left.test(t, query) || right.test(t, query);
    }
}
