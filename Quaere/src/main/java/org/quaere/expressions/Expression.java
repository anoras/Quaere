package org.quaere.expressions;

public abstract class Expression implements ExpressionTreeNode {
    public abstract void accept(ExpressionTreeVisitor visitor);
    public BinaryExpression and(Expression rightHandSide) {
        return BinaryExpression.and(this, rightHandSide);
    }
    public BinaryExpression or(Expression rightHandSide) {
        return BinaryExpression.or(this, rightHandSide);
    }
}
