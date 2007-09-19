package org.quaere.expressions;

public final class ModuloOperator extends BinaryExpression {
    public ModuloOperator(Expression leftExpression, Expression rightExpression) {
        super(OperatorType.MODULO, leftExpression, rightExpression);
    }

    protected String operatorText() {
        return "%";
    }
}
