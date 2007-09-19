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

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
