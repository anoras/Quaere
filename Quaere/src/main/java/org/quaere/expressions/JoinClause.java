package org.quaere.expressions;

public class JoinClause extends QueryBodyClause {
    private final String className;
    private final Identifier identifier;
    private final Expression inIndentifier;
    private final Expression onExpression;
    private final Expression equalsExpression;
    private final Identifier intoIdentifier;


    public JoinClause(String className, Identifier identifier, Expression inIndentifier, Expression onExpression, Expression equalsExpression, Identifier intoIdentifier) {
        this.className = className;
        this.identifier = identifier;
        this.inIndentifier = inIndentifier;
        this.onExpression = onExpression;
        this.equalsExpression = equalsExpression;
        this.intoIdentifier = intoIdentifier;
    }

    public String getClassName() {
        return className;
    }

    public Expression getEqualsExpression() {
        return equalsExpression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Expression getInIndentifier() {
        return inIndentifier;
    }

    public Identifier getIntoIdentifier() {
        return intoIdentifier;
    }

    public Expression getOnExpression() {
        return onExpression;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
