package org.quaere.expressions;

public final class MultiplyOperator extends BinaryExpression {
    public MultiplyOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.MULTIPLY, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "*";
    }
}
