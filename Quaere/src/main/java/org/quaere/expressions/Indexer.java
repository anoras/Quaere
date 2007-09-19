package org.quaere.expressions;

public class Indexer extends Expression {
    private final Expression innerExpression;
    private final Expression parameter;

    public Indexer(Expression innerExpression, Expression parameter) {
        this.innerExpression = innerExpression;
        this.parameter = parameter;
    }

    public Expression getInnerExpression() {
        return innerExpression;
    }

    public Expression getParameter() {
        return parameter;
    }

    public String toString() {
        return '[' + parameter.toString() + ']';
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
