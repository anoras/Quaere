package org.quaere.alias;

public class ConditionOr<T> extends Condition<T> {

    private Condition<T> left, right;
    
    ConditionOr(Condition<T> left, Condition<T> right) {
        this.left = left;
        this.right = right;
    }

    boolean test(QueryBase query) {
        return left.test(query) || right.test(query);
    }
}
