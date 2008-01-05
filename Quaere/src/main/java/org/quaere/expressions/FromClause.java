package org.quaere.expressions;

/**
 * Represents a from clause within an sourceExpression tree.
 * <p/>
 * A query expression must begin with a from clause. Additionally, a query expression can
 * contain sub-queries, which also begin with a from clause. The from clause specifies the
 * source on which the query or subquery will be run and an @see Identifier that represents
 * each element in the query source.
 * <p/>
 * The @see FromClause#sourceExpression produce a @see Queryable, @see Iterable, array or
 * another query.
 *
 * @see org.quaere.expressions.ExpressionTreeNode
 */
public class FromClause extends QueryBodyClause {
    /**
     * Gets the @see Class for the elements in the source @see FromClause#sourceExpression
     * if the @see Class has been specified, otherwise <em>null</em>.
     */
    public final Class elementClass;
    /**
     * Gets the @see Identifier assigned to the @see FromClause#sourceExpression.
     */
    public final Identifier identifier;
    /**
     * Gets the @see Expression which will produce the query source when evaluated.
     */
    public final Expression sourceExpression;

    /**
     * Creates a new @see FromClause with a given @see Identifier and source @see Expression.
     *
     * @param identifier       The @see Identifier to assign to the source.
     * @param sourceExpression The @see Expression which will produce the query source when evaluated.
     */
    public FromClause(Identifier identifier, Expression sourceExpression) {
        this.elementClass = null;
        this.identifier = identifier;
        this.sourceExpression = sourceExpression;
    }

    /**
     * Creates a new @see FromClause with a given @see Identifier and source @see Expression.
     *
     * @param elementClass     The @see Class of the elements whitn the query source.
     * @param identifier       The @see Identifier to assign to the source.
     * @param sourceExpression The @see Expression which will produce the query source when evaluated.
     */
    public FromClause(Class elementClass, Identifier identifier, Expression sourceExpression) {
        this.elementClass = elementClass;
        this.identifier = identifier;
        this.sourceExpression = sourceExpression;
    }
    /**
     * Creates a new @see FromClause with a given @see Identifier and source @see Expression.
     *
     * @param elementClassName The name of the class of the elements within the query source.
     * @param identifier       The @see Identifier to assign to the source.
     * @param sourceExpression The @see Expression which will produce the query source when evaluated.
     * @throws IllegalArgumentException The specified class name cannot be found.
     */
    public FromClause(String elementClassName, Identifier identifier, Expression sourceExpression) {
        try {
            this.elementClass = Class.forName(elementClassName);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("Cannot find class '%s'", elementClassName), e);
        }
        this.identifier = identifier;
        this.sourceExpression = sourceExpression;
    }

    // --------------------- Interface ExpressionTreeNode ---------------------

    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
