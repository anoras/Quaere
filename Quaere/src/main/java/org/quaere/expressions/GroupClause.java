package org.quaere.expressions;

import java.util.Comparator;

/**
 * Represents group clause used to group the elements of
 * a sequence according to a specified key selector @see Expression.
 * <p/>
 * A @see org.quaere.QueryEngine must return a sequence of @see org.quaere.Group objects
 * that contain zero or more items that match the key value for the group.
 * For example, you can group a sequence of strings according to the first letter in
 * each string. In this case, the first letter is the key and has a type char,
 * and is stored in the @see org.quaere.Group#key field of each @see org.quaere.Group object.
 *
 * @see org.quaere.expressions.ExpressionTreeNode
 */
public class GroupClause extends SelectOrGroupClause {
    /**
     * Gets the identifier for the sequence to group.
     */
    public final Identifier identifier;
    /**
     * Gets the @see Expression used to select keys to group by.
     */
    public final Expression keySelectionExpression;
    /**
     * Gets the @see Comparator to use when comparing keys.
     * If null, the default @see Comparator for the key's @see Class should be used,
     * otherwise this @see Comparator should be used.
     */
    public final Comparator keyComparator;
    /**
     * Creates a new @see GroupClause for the specified identifier with a
     * specific key selection expression.
     *
     * @param identifier             The @see Identifier of the source to group.
     * @param keySelectionExpression The key selection @see Expression to use for key selection.
     */
    public GroupClause(Identifier identifier, Expression keySelectionExpression) {
        this.identifier = identifier;
        this.keySelectionExpression = keySelectionExpression;
        this.keyComparator = null;
    }

    /**
     * Creates a new @see GroupClause for the specified identifier with a
     * specific key selection expression and @see Comparator to use for key comparision.
     *
     * @param identifier             The @see Identifier of the source to group.
     * @param keySelectionExpression The key selection @see Expression to use for key selection.
     * @param keyComparator          They @see Comparator to use when comparign group keys.
     */
    public GroupClause(Identifier identifier, Expression keySelectionExpression, Comparator keyComparator) {
        this.identifier = identifier;
        this.keySelectionExpression = keySelectionExpression;
        this.keyComparator = keyComparator;
    }
    /**
     * Gets a textual representation of the @see GroupClause.
     *
     * @return A textual representation of the @see GroupClause.
     */
    public String toString() {
        return String.format("group(%s).by(%s).", identifier.toString(), keySelectionExpression.toString());
    }
    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
