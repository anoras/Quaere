package org.quaere.expressions;

public final class GreaterThanOrEqualOperator extends BinaryExpression {
    protected GreaterThanOrEqualOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.GREATER_THAN_OR_EQUAL, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return ">=";
    }
}
