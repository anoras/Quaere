package org.quaere.quaere4objects;

import org.quaere.dsl.*;
import org.quaere.expressions.*;

import java.util.*;

public class Quaere4ObjectsSubqueryBuilder implements SubqueryFromClauseBuilder, SubqueryJoinClauseBuilder, SubqueryWhereClauseBuilder, SubqueryOrderableAndGroupableQueryBodyBuilder, SubqueryOrderByBuilderContinuation, SubqueryGroupClauseBuilder, SubqueryGroupClauseBuilderContinuation {
    private List<Identifier> sourceOrder = new ArrayList<Identifier>();
    private Map<Identifier, List> sources = new HashMap<Identifier, List>();
    private FromClause fromClause;
    private List<QueryBodyClause> queryBodyClauses = new ArrayList<QueryBodyClause>();
    private QueryExpression expression;
    private Identifier lastIdentifier;
    private List<OrderByCriteria> currentOrderByCriterias = new ArrayList<OrderByCriteria>();
    private String currentGroupIdentifier;
    private SelectOrGroupClause currentSelectOrGroupClause;
    private Quaere4ObjectsSubqueryBuilder parentQueryBuilder = null;
    private QueryContinuation queryContinuation = null;
    private Identifier continuationIdentifier=null;
    public Quaere4ObjectsSubqueryBuilder() {
    }
    public Quaere4ObjectsSubqueryBuilder(Quaere4ObjectsSubqueryBuilder parentQueryBuilder) {
        this.parentQueryBuilder=parentQueryBuilder;

    }

    private void terminateOrderingClauses() {
        if (currentOrderByCriterias.size() > 0) {
            queryBodyClauses.add(new OrderByClause(currentOrderByCriterias));
            currentOrderByCriterias = new ArrayList<OrderByCriteria>();
        }
    }

    public SubqueryFromClauseBuilder from(String identifier) {
        lastIdentifier = new Identifier(identifier);
        return this;
    }
    public SubqueryOrderableAndGroupableQueryBodyBuilder in(String expression) {
        return in(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    private SubqueryOrderableAndGroupableQueryBodyBuilder in(Expression expression) {
        if (fromClause == null) {
            // This is the intial from clause
            fromClause = new FromClause(null, lastIdentifier, expression);
        } else {
            queryBodyClauses.add(new FromClause(null, lastIdentifier, expression));
        }
        return this;
    }

    public Expression select(String expression) {
        return select(LiteralExpressionParser.parseLiteralExpression(expression));

    }
    public Expression select(Expression expression) {
        QueryBody queryBody = new QueryBody(queryBodyClauses, currentSelectOrGroupClause, queryContinuation);
        QueryExpression queryExpression = new QueryExpression(fromClause, queryBody);
        if (parentQueryBuilder!=null)
        {
            currentSelectOrGroupClause = new SelectClause(expression);
            return queryExpression;

        }
        else
        {
            int i=0;
            throw new RuntimeException("Quaere4ObjectsSubqueryBuilder.select is not implemented");
        }
    }
    public SubqueryOrderByBuilderContinuation orderBy(String identifier) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public SubqueryOrderByBuilderContinuation orderBy(String expression, Comparator comparator) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public SubqueryOrderByBuilderContinuation orderByDescending(String expression) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public SubqueryOrderByBuilderContinuation orderByDescending(String expression, Comparator comparator) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public SubqueryGroupClauseBuilder group(String identifier) {
        terminateOrderingClauses();
        currentGroupIdentifier = identifier;
        return this;    }
    public SubqueryJoinClauseBuilder join(String identifer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public SubqueryWhereClauseBuilder where(Expression predicate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
     public SubqueryGroupClauseBuilderContinuation by(String expression) {
        return by(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    public SubqueryGroupClauseBuilderContinuation by(Expression expression) {
        currentSelectOrGroupClause = new GroupClause(
                new Identifier(currentGroupIdentifier),
                expression
        );
        currentGroupIdentifier = null;
        return this;
    }
    public SubqueryOrderableQueryBodyBuilder into(String identifier) {
        this.continuationIdentifier=new Identifier(identifier);
        Quaere4ObjectsSubqueryBuilder continuationBuilder = new Quaere4ObjectsSubqueryBuilder(this);
        return continuationBuilder;
    }
}
