package org.quaere.expressions;

public final class GreaterThanOrEqualOperator extends BinaryExpression {
    public GreaterThanOrEqualOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.GREATER_THAN_OR_EQUAL, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return ">=";
    }
}
