package org.quaere.expressions;

public class FromClause extends QueryBodyClause {
    private final Class clazz;
    private final Identifier identifier;
    private final Expression expression;


    public FromClause(Identifier identifier, Expression expression) {
        this.clazz = null;
        this.identifier = identifier;
        this.expression = expression;
    }

    public FromClause(Class clazz, Identifier identifier, Expression expression) {
        this.clazz = clazz;
        this.identifier = identifier;
        this.expression = expression;
    }
    public FromClause(String className, Identifier identifier, Expression expression) {
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("Cannot find class '%s'", className), e);
        }
        this.identifier = identifier;
        this.expression = expression;
    }
    public Expression getExpression() {
        return expression;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
