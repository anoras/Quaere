package org.quaere.alias;

public class Operation<T> implements Function<T> {

    private Object left, right;
    private OperationType type;
    
    public Operation(Object a, Object b, OperationType type) {
        this.left = a;
        this.right = b;
        this.type = type;
    }

    public Object getValue(QueryBase query) {
        return type.calculate(query.getValue(left), query.getValue(right));
    }

}
