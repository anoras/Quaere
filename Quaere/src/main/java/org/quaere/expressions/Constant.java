package org.quaere.expressions;

public class Constant extends Expression {
    private final Object value;
    private final Class clazz;

    public Constant(Object value, Class clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
