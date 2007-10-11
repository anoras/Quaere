package org.quaere.alias;

public class ConditionAnd<T> extends Condition<T> {

    private Condition<T> left, right;
    
    ConditionAnd(Condition<T> left, Condition<T> right) {
        this.left = left;
        this.right = right;
    }

    boolean test(QueryBase query) {
        return left.test(query) && right.test(query);
    }
    
}
