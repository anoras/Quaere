package org.quaere.expressions;

public final class EqualOperator extends BinaryExpression {
    public EqualOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.EQUAL, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "==";
    }
}
