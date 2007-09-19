package org.quaere.expressions;

public class Parameter extends Expression {
    private final String name;

    public Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
