package org.quaere.expressions;

public class Constant extends Expression {
    private final Object value;
    private final Class clazz;

    public Constant(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Canot infer the type of 'null'. Use the Constant(Object,Class) constructor to create a null valued constant.");
        }
        this.value = value;
        this.clazz = value.getClass();
    }
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
        if (String.class.equals(clazz)) {
            return '"' + String.valueOf(value) + '"';
        } else if (Character.class.equals(clazz)) {
            return '\'' + String.valueOf(value) + '\'';
        } else {
            return String.valueOf(value);
        }
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
