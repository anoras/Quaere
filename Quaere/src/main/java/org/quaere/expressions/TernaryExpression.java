package org.quaere.expressions;

public class TernaryExpression extends Expression {
    private final Expression leftExpression;
    private final Expression middleExpression;
    private final Expression rightExpression;

    public TernaryExpression(Expression leftExpression, Expression middleExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.middleExpression = middleExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getMiddleExpression() {
        return middleExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public String toString() {
        return leftExpression.toString() + '(' + middleExpression.toString() + ", " + rightExpression.toString() + ')';
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
