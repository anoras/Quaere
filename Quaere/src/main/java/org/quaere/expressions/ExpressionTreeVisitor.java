package org.quaere.expressions;

public interface ExpressionTreeVisitor {
    public void visit(FromClause expression);

    public void visit(GroupClause expression);

    public void visit(JoinClause expression);

    public void visit(OrderByClause expression);

    public void visit(LetClause expression);

    public void visit(WhereClause expression);

    public void visit(SelectClause expression);


    public void visit(QueryBody expression);

    public void visit(QueryContinuation expression);

    public void visit(QueryExpression expression);


    public void visit(BinaryExpression expression);

    public void visit(TernaryExpression expression);

    public void visit(UnaryExpression expression);

    public void visit(Constant expression);

    public void visit(Identifier expression);

    public void visit(MethodCall expression);

    public void visit(Indexer expression);

    public void visit(Statement expression);

    public void visit(Parameter expression);

    public void visit(NewExpression expression);
}
