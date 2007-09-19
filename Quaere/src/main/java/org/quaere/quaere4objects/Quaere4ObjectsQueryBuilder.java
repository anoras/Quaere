package org.quaere.quaere4objects;

import org.quaere.dsl.*;
import org.quaere.expressions.*;

import java.util.*;

public class Quaere4ObjectsQueryBuilder implements Iterable, FromClauseBuilder, WhereClauseBuilder, OrderableAndGroupableQueryBodyBuilder, OrderByBuilderContinuation, GroupClauseBuilder, GroupClauseBuilderContinuation, JoinClauseBuilderContinuation {
    private List<Identifier> sourceOrder = new ArrayList<Identifier>();
    Map<Identifier, List> sources = new HashMap<Identifier, List>();
    private FromClause fromClause;
    List<QueryBodyClause> queryBodyClauses = new ArrayList<QueryBodyClause>();
    private QueryExpression expression;
    private Identifier lastIdentifier;
    private List<OrderByCriteria> currentOrderByCriterias = new ArrayList<OrderByCriteria>();
    private String currentGroupIdentifier;
    private SelectOrGroupClause currentSelectOrGroupClause;
    private Quaere4ObjectsQueryBuilder parentQueryBuilder = null;
    private QueryContinuation queryContinuation = null;
    private Identifier continuationIdentifier=null;


    public Quaere4ObjectsQueryBuilder() {
    }
    public Quaere4ObjectsQueryBuilder(Quaere4ObjectsQueryBuilder parentQueryBuilder) {
        this.parentQueryBuilder=parentQueryBuilder;
    }

    private void terminateOrderingClauses() {
        if (currentOrderByCriterias.size() > 0) {
            queryBodyClauses.add(new OrderByClause(currentOrderByCriterias));
            currentOrderByCriterias = new ArrayList<OrderByCriteria>();
        }
    }

    public FromClauseBuilder from(String identifier) {
        lastIdentifier = new Identifier(identifier);
        return this;
    }
    public OrderableAndGroupableQueryBodyBuilder in(String expression) {
        return in(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    public <T> OrderableAndGroupableQueryBodyBuilder in(T... source) {
        return in(Arrays.asList(source));
    }
    public <T> OrderableAndGroupableQueryBodyBuilder in(List<T> source) {
        sources.put(lastIdentifier, source);
        return in(
                new Statement(
                        Arrays.<Expression>asList(
                                new Identifier(Quaere4ObjectsQuery.getSourceName(lastIdentifier))
                        )
                )
        );
    }

    public OrderableAndGroupableQueryBodyBuilder in(Expression expression) {
        if (fromClause == null) {
            // This is the intial from clause
            fromClause = new FromClause(null, lastIdentifier, expression);
        } else {
            queryBodyClauses.add(new FromClause(null, lastIdentifier, expression));
        }
        return this;
    }
    public <T> Iterable<T> select(String expression) {
        return select(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    public <T> Iterable<T> select(Expression expression) {
        terminateOrderingClauses();
        currentSelectOrGroupClause = new SelectClause(expression);
        return this;
    }
    public GroupClauseBuilder group(String identifier) {
        terminateOrderingClauses();
        currentGroupIdentifier = identifier;
        return this;
    }
    public JoinClauseBuilder join(String identifer) {
        Quaere4ObjectsQueryJoinClauseBuilder joinClauseBuilder=new Quaere4ObjectsQueryJoinClauseBuilder(this,new Identifier(identifer));
        return joinClauseBuilder;

    }
    public WhereClauseBuilder where(Expression predicate) {
        queryBodyClauses.add(new WhereClause(predicate));
        return this;
    }
    public WhereClauseBuilder where(String expression) {
        return where(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    public OrderByBuilderContinuation orderBy(String identifier) {
        return orderBy(LiteralExpressionParser.parseLiteralExpression(identifier));
    }
    public OrderByBuilderContinuation orderBy(Expression expression) {
        currentOrderByCriterias.add(new OrderByCriteria(expression, OrderByCriteria.Direction.ASCENDING));
        return this;
    }
    public OrderByBuilderContinuation orderBy(String expression, Comparator comparator) {
        return orderBy(LiteralExpressionParser.parseLiteralExpression(expression), comparator);
    }
    public OrderByBuilderContinuation orderBy(Expression expression, Comparator comparator) {
        currentOrderByCriterias.add(new OrderByCriteria(expression, OrderByCriteria.Direction.ASCENDING, comparator));
        return this;
    }

    public OrderByBuilderContinuation orderByDescending(String expression) {
        return orderByDescending(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    private OrderByBuilderContinuation orderByDescending(Expression expression) {
        currentOrderByCriterias.add(new OrderByCriteria(expression, OrderByCriteria.Direction.DESCENDING));

        return this;
    }

    public OrderByBuilderContinuation orderByDescending(String expression, Comparator comparator) {
        return orderByDescending(LiteralExpressionParser.parseLiteralExpression(expression), comparator);
    }
    public OrderByBuilderContinuation orderByDescending(Expression expression, Comparator comparator) {
        currentOrderByCriterias.add(new OrderByCriteria(expression, OrderByCriteria.Direction.DESCENDING, comparator));
        return this;
    }

    public OrderByBuilderContinuation thenBy(String expression) {
        throw new RuntimeException("Quaere4ObjectsQueryBuilder.thenBy is not implemented");
    }
    public GroupClauseBuilderContinuation by(String expression) {
        return by(LiteralExpressionParser.parseLiteralExpression(expression));
    }
    public GroupClauseBuilderContinuation by(String expression, Comparator comparator) {
        return by(LiteralExpressionParser.parseLiteralExpression(expression),comparator);
    }
    public GroupClauseBuilderContinuation by(Expression expression) {
        currentSelectOrGroupClause = new GroupClause(
                new Identifier(currentGroupIdentifier),
                expression
        );
        currentGroupIdentifier = null;
        return this;
    }
    public GroupClauseBuilderContinuation by(Expression expression,Comparator comparator) {
        currentSelectOrGroupClause = new GroupClause(
                new Identifier(currentGroupIdentifier),
                expression,
                comparator
        );
        currentGroupIdentifier = null;
        return this;
    }
    public Iterator<?> iterator() {
        if (parentQueryBuilder != null) {
            QueryBody queryBody = new QueryBody(queryBodyClauses, currentSelectOrGroupClause, queryContinuation);
            return parentQueryBuilder.iterator(queryBody);
        } else {
            if (currentSelectOrGroupClause == null) {
                throw new RuntimeException("Incomplete query experssion.");
            }
            QueryBody queryBody = new QueryBody(queryBodyClauses, currentSelectOrGroupClause, queryContinuation);
            QueryExpression queryExpression = new QueryExpression(fromClause, queryBody);
            Quaere4ObjectsQuery q = new Quaere4ObjectsQuery(queryExpression);
            for (Map.Entry<Identifier, List> sourceEntry : sources.entrySet()) {
                q.addSource(Quaere4ObjectsQuery.getSourceName(sourceEntry.getKey()), sourceEntry.getValue());
            }
            Iterable result = q.evaluate();
            return result.iterator();
        }
    }
    private Iterator<?> iterator(QueryBody continuationQueryBody) {
        this.queryContinuation=new QueryContinuation(
                continuationIdentifier,
                continuationQueryBody
        );
        return iterator();
    }
    public OrderableQueryBodyBuilder into(String identifier) {
        if (currentSelectOrGroupClause instanceof GroupClause)
        {
            // Are we creating a source from a group by?
            this.continuationIdentifier=new Identifier(identifier);
            Quaere4ObjectsQueryBuilder continuationBuilder = new Quaere4ObjectsQueryBuilder(this);
            return continuationBuilder;
        } else {
            // ..or was is a join?
            JoinClause nonGroupedJoin= (JoinClause) queryBodyClauses.get(queryBodyClauses.size()-1);
            JoinClause groupedJoin=new JoinClause(
                nonGroupedJoin.getClassName(),
                nonGroupedJoin.getIdentifier(),
                nonGroupedJoin.getInIndentifier(),
                nonGroupedJoin.getOnExpression(),
                nonGroupedJoin.getEqualsExpression(),
                new Identifier(identifier)
            );
            queryBodyClauses.remove(nonGroupedJoin);
            queryBodyClauses.add(groupedJoin);
            return this;
        }

    }
}
