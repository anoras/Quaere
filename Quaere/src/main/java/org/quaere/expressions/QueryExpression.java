package org.quaere.expressions;

public class QueryExpression extends Expression {
    private final FromClause from;
    private final QueryBody queryBody;

    public QueryExpression(FromClause from, QueryBody queryBody) {
        this.from = from;
        this.queryBody = queryBody;
    }

    public FromClause getFrom() {
        return from;
    }

    public QueryBody getQueryBody() {
        return queryBody;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
    public String toString() {
        return String.format("%s %s",from!=null?from.toString():"null",queryBody!=null?queryBody.toString():"null");
    }
}
