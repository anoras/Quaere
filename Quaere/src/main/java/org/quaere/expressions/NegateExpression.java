package org.quaere.expressions;

public class NegateExpression extends UnaryExpression {
    public NegateExpression(Expression expression) {
        super(OperatorType.NEGATE, expression);
    }
}
