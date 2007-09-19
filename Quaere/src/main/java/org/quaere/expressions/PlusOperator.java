package org.quaere.expressions;

public final class PlusOperator extends BinaryExpression {
    public PlusOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.PLUS, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "+";
    }
}
