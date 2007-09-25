package org.quaere.expressions;

public final class LessThanOrEqualOperator extends BinaryExpression {
    public LessThanOrEqualOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.LESS_THAN_OR_EQUAL, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "<=";
    }
}
