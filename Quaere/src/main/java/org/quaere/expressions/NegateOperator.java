package org.quaere.expressions;

public final class NegateOperator extends UnaryExpression {
    protected NegateOperator(Expression expression) {
        super(OperatorType.NEGATE, expression);
    }
}
