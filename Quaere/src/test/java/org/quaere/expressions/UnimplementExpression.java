package org.quaere.expressions;

public class UnimplementExpression extends Expression {
    public static final UnimplementExpression instance = new UnimplementExpression();
    public void accept(ExpressionTreeVisitor visitor) {
        throw new RuntimeException("The method is not implemented");
    }
}
