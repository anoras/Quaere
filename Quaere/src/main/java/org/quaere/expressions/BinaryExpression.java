package org.quaere.expressions;

/**
 * Represents an sourceExpression that has a binary operator.
 */
public class BinaryExpression extends Expression {
    /**
     * Gets the @see OperatorType of the @see BinaryExpression.
     */
    public final OperatorType operator;
    /**
     * Gets the @see Expression on the left hand side of the @see BinaryExpression#operator.
     */
    public final Expression leftExpression;
    /**
     * Gets the @see Expression on the right hand side of the @see BinaryExpression#operator.
     */
    public final Expression rightExpression;
    public BinaryExpression(OperatorType operator, Expression leftExpression, Expression rightExpression) {
        this.operator = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    /**
     * Creates a @see BinaryExpression that represents a conditional <em>AND</em> operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#AND and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression and(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.AND, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents a conditional <em>OR</em> operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#OR and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression or(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.OR, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an inequality operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#NOT_EQUAL and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression notEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.NOT_EQUAL, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents a less than or equal numeric operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#LESS_THAN_OR_EQUAL and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression lessThanOrEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.LESS_THAN_OR_EQUAL, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents a greater than or equal numeric operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#GREATER_THAN_OR_EQUAL and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression greaterThanOrEqual(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.GREATER_THAN_OR_EQUAL, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents a less than operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#LESS_THAN and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression lessThan(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.LESS_THAN, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents a greater than or equal numeric operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#GREATER_THAN and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression greaterThan(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.GREATER_THAN, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an equality operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#EQUAL and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression equal(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.EQUAL, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic subtraction operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#SUBTRACTION and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression subtract(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.SUBTRACTION, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic addition operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#ADDITION and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression add(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.ADDITION, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic remainder operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#MODULO and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression modulo(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.MODULO, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic division operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#DIVISION and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression divide(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.DIVISION, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic multiplication operation.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#MULTIPLICATION and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression multiply(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.MULTIPLICATION, left, right);
    }
    /**
     * Creates a @see BinaryExpression that represents an arithmetic operation of raising a number to a power.
     *
     * @param left  The left hand side sourceExpression.
     * @param right The right hand side epxression.
     * @return A @see BinaryExpression that has @see BinaryExpression#operator equal
     *         to @see BinaryExpression.OperatorType#POW and the @see BinaryExpression#leftExpression and
     * @see BinaryExpression#rightExpression set to the specified values.
     */
    public static BinaryExpression pow(Expression left, Expression right) {
        return new BinaryExpression(OperatorType.POW, left, right);
    }
    /**
     * Gets a textual representation of the @see BinaryExpression.
     *
     * @return A textual reprsentation of the @BinaryExpression.
     */
    @Override
    public String toString() {
        return String.format("(%s %s %s)", leftExpression, operator.toString(), rightExpression);
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
        SUBTRACTION,
        ADDITION,
        MODULO,
        DIVISION,
        MULTIPLICATION,
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
                case SUBTRACTION:
                    return "-";
                case ADDITION:
                    return "+";
                case MODULO:
                    return "%";
                case DIVISION:
                    return "/";
                case MULTIPLICATION:
                    return "*";
                case POW:
                    return "^";
                default:
                    return "??";
            }

        }
    }
}
