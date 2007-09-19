package org.quaere.expressions;

public final class NotOperator extends UnaryExpression {
    protected NotOperator(Expression expression) {
        super(OperatorType.NOT, expression);
    }
}
