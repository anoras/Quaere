package org.quaere.expressions;

/**
 * Represents a constant name.
 */
public class Constant extends Expression {
    /**
     * Gets the constant's name.
     */
    public final Object value;
    /**
     * Gets the @see Class of the constant's name.
     */
    public final Class clazz;
    /**
     * Creates a new @see Constant name and infers the constant's @see Class.
     *
     * @param value The name of the constant.
     * @throws IllegalArgumentException The name is null.
     */
    public Constant(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Canot infer the type of 'null'. Use the Constant(Object,Class) constructor to create a null valued constant.");
        }
        this.value = value;
        this.clazz = value.getClass();
    }
    /**
     * Creates a new @see Constant name with a specified see @Class
     *
     * @param value The name of the constant.
     * @param clazz The @see Class of the constant's name.
     */
    public Constant(Object value, Class clazz) {
        this.value = value;
        this.clazz = clazz;
    }
    /**
     * Gets a textual representation of the @see Constant.
     *
     * @return
     */
    @Override
    public String toString() {
        if (String.class.equals(clazz)) {
            return '"' + String.valueOf(value) + '"';
        }
        else if (Character.class.equals(clazz)) {
            return '\'' + String.valueOf(value) + '\'';
        }
        else {
            return String.valueOf(value);
        }
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
