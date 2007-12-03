package org.quaere.expressions;

import java.util.Arrays;
import java.util.List;

public class NewExpression extends Expression {
    private final Class<?> clazz;
    private final List<Property> properties;

    public NewExpression(Class<?> clazz, List<Property> properties) {
        this.clazz = clazz;
        this.properties = properties;
    }
    public NewExpression(Class<?> clazz, Property... properties) {
        this(clazz, Arrays.asList(properties));
    }


    public Class getClazz() {
        return clazz;
    }

    public List<Property> getProperties() {
        return properties;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("new ");
        sb.append(clazz != null ? clazz.getName() : "Variant");
        sb.append(" {");
        for (Property p : properties) {
            sb.append("  ");
            sb.append(p.toString());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();

    }
// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
