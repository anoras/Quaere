package org.quaere.expressions;

import java.util.List;

public class NewExpression extends Expression {
    private final String className;
    private final List<Property> properties;

    public NewExpression(String className, List<Property> properties) {
        this.className = className;
        this.properties = properties;
    }

    public String getClassName() {
        return className;
    }

    public List<Property> getProperties() {
        return properties;
    }
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("new ");
        sb.append(className != null ? className : "Variant");
        sb.append(" {");
        for (Property p: properties) {
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
