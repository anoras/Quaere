package org.quaere.expressions;


/**
 * Represents a variable declaration and assignment.
 */
public class DeclareClause extends QueryBodyClause {
    /**
     * Gets the @see Identifier for the variable.
     */
    public final Identifier variableIdentifier;
    /**
     * Gets the @see Expression that should be evaluated and assigned to the @see DeclareClause#variableIdentifier.
     */
    public final Expression assignedExpression;

    /**
     * Creates a new @see DeclareClause for a given variable @see Identifier and name @see Expression.
     *
     * @param left  The @see Identifier for the variable.
     * @param right The @Expression to assign to the variable.
     */
    public DeclareClause(Identifier left, Expression right) {
        this.variableIdentifier = left;
        this.assignedExpression = right;
    }

    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
