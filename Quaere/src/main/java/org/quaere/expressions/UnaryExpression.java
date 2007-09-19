package org.quaere.expressions;

public class UnaryExpression extends Expression {
    public final OperatorType operator;
    public final Expression expression;

    public UnaryExpression(OperatorType opertator, Expression expression) {
        this.operator = opertator;
        this.expression = expression;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OperatorType {
        NOT,
        NEGATE
    }
}
