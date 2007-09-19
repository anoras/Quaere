package org.quaere.expressions;

public final class DivideOperator extends BinaryExpression {
    public DivideOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.DIVIDE, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "/";
    }
}
