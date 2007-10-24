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
    public String toString() {
        return String.format("into(%s).\n%s",identifier.toString(),queryBody.toString());
    }
    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
