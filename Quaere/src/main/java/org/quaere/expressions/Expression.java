package org.quaere.expressions;

/**
 * Provides the super class from which the classes that
 * represent sourceExpression tree nodes are derived.
 */
public abstract class Expression implements ExpressionTreeNode {
    public abstract void accept(ExpressionTreeVisitor visitor);
    /**
     * Combines this @see Expression with another @Esee xpression by applying a logical <em>AND</em>.
     *
     * @param rightHandSide The @see Expression that will be at the right hand side of the <em>AND</em> operator.
     * @return Returns a @see BinaryExpression with the @see BinaryExpression#operator equal to @see BinaryExpression.OperatorType.AND
     */
    public BinaryExpression and(Expression rightHandSide) {
        return BinaryExpression.and(this, rightHandSide);
    }
    /**
     * Combines this @see Expression with another @Esee xpression by applying a logical <em>OR</em>.
     *
     * @param rightHandSide The @see Expression that will be at the right hand side of the <em>OR</em> operator.
     * @return Returns a @see BinaryExpression with the @see BinaryExpression#operator equal to @see BinaryExpression.OperatorType.OR.
     */
    public BinaryExpression or(Expression rightHandSide) {
        return BinaryExpression.or(this, rightHandSide);
    }
}
