package org.quaere.expressions;

public final class PowOperator extends BinaryExpression {
    public PowOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.POW, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "^";
    }
}
