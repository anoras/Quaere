package org.quaere.expressions;

public abstract class Expression implements ExpressionTreeNode {
    public abstract void accept(ExpressionTreeVisitor visitor);
    public AndOperator and(Expression rightHandSide) {
        return new AndOperator(this, rightHandSide);
    }
    public OrOperator or(Expression rightHandSide) {
        return new OrOperator(this, rightHandSide);
    }
}
