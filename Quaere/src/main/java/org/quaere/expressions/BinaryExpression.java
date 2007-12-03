package org.quaere.expressions;

/**
 * Represents an expression that has a binary operator.
 */
public class BinaryExpression extends Expression {
    public final OperatorType operator;
    public final Expression leftExpression;
    public final Expression rightExpression;

    public BinaryExpression(OperatorType operator, Expression leftExpression, Expression rightExpression) {
        this.operator = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    public static BinaryExpression and(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.AND, left, right);
    }
    public static BinaryExpression or(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.OR, left, right);
    }
    public static BinaryExpression notEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.NOT_EQUAL, left, right);
    }
    public static BinaryExpression lessThanOrEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.LESS_THAN_OR_EQUAL, left, right);
    }
    public static BinaryExpression greaterThanOrEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.GREATER_THAN_OR_EQUAL, left, right);
    }
    public static BinaryExpression lessThan(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.LESS_THAN, left, right);
    }
    public static BinaryExpression greaterThan(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.GREATER_THAN, left, right);
    }
    public static BinaryExpression equal(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.EQUAL, left, right);
    }
    public static BinaryExpression minus(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.MINUS, left, right);
    }
    public static BinaryExpression plus(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.PLUS, left, right);
    }
    public static BinaryExpression modulo(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.MODULO, left, right);
    }
    public static BinaryExpression divide(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.DIVIDE, left, right);
    }
    public static BinaryExpression multiply(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.MULTIPLY, left, right);
    }
    public static BinaryExpression pow(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.POW, left, right);
    }
    @Override
    public String toString() {
        return String.format("(%s %s %s)", leftExpression, operator.toString(), rightExpression);
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
        UNKNOWN;

        public String toString() {
            switch (this) {
                case AND:
                    return "&&";
                case OR:
                    return "||";
                case NOT_EQUAL:
                    return "!=";
                case LESS_THAN_OR_EQUAL:
                    return "<=";
                case GREATER_THAN_OR_EQUAL:
                    return ">=";
                case LESS_THAN:
                    return "<";
                case GREATER_THAN:
                    return ">";
                case EQUAL:
                    return "==";
                case MINUS:
                    return "-";
                case PLUS:
                    return "+";
                case MODULO:
                    return "%";
                case DIVIDE:
                    return "/";
                case MULTIPLY:
                    return "*";
                case POW:
                    return "^";
                default:
                    return "??";
            }

        }
    }
}
