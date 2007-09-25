package org.quaere.expressions;

public class BinaryExpression extends Expression {
    public final OperatorType operator;
    public final Expression leftExpression;
    public final Expression rightExpression;

    public BinaryExpression(OperatorType operator, Expression leftExpression, Expression rightExpression) {
        this.operator = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", leftExpression, operatorText(), rightExpression);
    }

    protected String operatorText() {
        return "default";
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

    public enum OperatorType {
        AND,
        OR,
        NOT_EQUAL,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN,
        GREATER_THAN,
        EQUAL,
        MINUS,
        PLUS,
        MODULO,
        DIVIDE,
        MULTIPLY,
        POW,
        UNKNOWN
    }
}
