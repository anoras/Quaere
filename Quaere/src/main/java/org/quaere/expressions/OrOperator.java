package org.quaere.expressions;

public final class OrOperator extends BinaryExpression {
    public OrOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.OR, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "||";
    }
}
