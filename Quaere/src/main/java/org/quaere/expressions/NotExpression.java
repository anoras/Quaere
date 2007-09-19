package org.quaere.expressions;

public class NotExpression extends UnaryExpression {
    public NotExpression(Expression expression) {
        super(OperatorType.NOT, expression);
    }
}
