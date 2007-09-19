package org.quaere.expressions;

public class QueryContinuation implements ExpressionTreeNode {
    private final Identifier identifier;
    private final QueryBody queryBody;

    public QueryContinuation(Identifier identifier, QueryBody queryBody) {
        this.identifier = identifier;
        this.queryBody = queryBody;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public QueryBody getQueryBody() {
        return queryBody;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
