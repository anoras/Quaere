package org.quaere.expressions;

public final class MinusOperator extends BinaryExpression {
    public MinusOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.MINUS, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "-";
    }
}
