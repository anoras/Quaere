package org.quaere.expressions;

/**
 * Creates a join clause used to corrolate the elements
 * of two sources based on matching keys.
 */
public class JoinClause extends QueryBodyClause {
    public final Identifier identifier;
    public final Expression inIndentifier;
    public final Expression onExpression;
    public final Expression keyEqualityExpression;
    public final Identifier intoIdentifier;

    public JoinClause(Identifier identifier, Expression inIndentifier, Expression onExpression, Expression keyEqualityExpression, Identifier intoIdentifier) {
        this.identifier = identifier;
        this.inIndentifier = inIndentifier;
        this.onExpression = onExpression;
        this.keyEqualityExpression = keyEqualityExpression;
        this.intoIdentifier = intoIdentifier;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
