package org.quaere.expressions;

public class LessThanOperator extends BinaryExpression {
    public LessThanOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.LESS_THAN, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "<";
    }
}
