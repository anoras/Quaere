package org.quaere.expressions;

public final class AndOperator extends BinaryExpression {
    public AndOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.AND, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "&&";
    }
}
