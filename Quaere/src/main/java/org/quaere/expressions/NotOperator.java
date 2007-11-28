package org.quaere.expressions;

public final class NotOperator extends UnaryExpression {
    public NotOperator(Expression expression) {
        super(OperatorType.NOT, expression);
    }
}
