package org.quaere.expressions;

public final class GreaterThanOperator extends BinaryExpression {
    public GreaterThanOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.GREATER_THAN, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return ">";
    }
}
