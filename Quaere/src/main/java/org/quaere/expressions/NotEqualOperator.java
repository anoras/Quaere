package org.quaere.expressions;

public final class NotEqualOperator extends BinaryExpression {
    public NotEqualOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.NOT_EQUAL, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "!=";
    }
}
