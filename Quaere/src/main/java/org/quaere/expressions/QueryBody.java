package org.quaere.expressions;

import java.util.List;

public class QueryBody implements ExpressionTreeNode {
    private final List<QueryBodyClause> clauses;
    private final SelectOrGroupClause selectOrGroupClause;
    private final QueryContinuation continuation;

    public QueryBody(List<QueryBodyClause> clauses, SelectOrGroupClause selectOrGroupClause, QueryContinuation continuation) {
        this.clauses = clauses;
        this.selectOrGroupClause = selectOrGroupClause;
        this.continuation = continuation;
    }

    public List<QueryBodyClause> getClauses() {
        return clauses;
    }

    public QueryContinuation getContinuation() {
        return continuation;
    }

    public SelectOrGroupClause getSelectOrGroupClause() {
        return selectOrGroupClause;
    }
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (QueryBodyClause clause: clauses) {
            sb.append(clause.toString());
            sb.append(".\n");
        }
        return sb.toString();
    }
    // --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }

    public boolean hasContinuation() {
        return continuation != null;
    }

    public boolean hasSelectOrGroupClause() {
        return selectOrGroupClause != null;
    }
    
}
