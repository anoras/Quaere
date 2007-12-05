package org.quaere.quaereForJPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.expressions.BinaryExpression;
import org.quaere.expressions.Constant;
import org.quaere.expressions.DeclareClause;
import org.quaere.expressions.Expression;
import org.quaere.expressions.ExpressionTreeVisitor;
import org.quaere.expressions.FromClause;
import org.quaere.expressions.GroupClause;
import org.quaere.expressions.Identifier;
import org.quaere.expressions.Indexer;
import org.quaere.expressions.JoinClause;
import org.quaere.expressions.MethodCall;
import org.quaere.expressions.NewExpression;
import org.quaere.expressions.OrderByClause;
import org.quaere.expressions.Parameter;
import org.quaere.expressions.QueryBody;
import org.quaere.expressions.QueryBodyClause;
import org.quaere.expressions.QueryContinuation;
import org.quaere.expressions.QueryExpression;
import org.quaere.expressions.SelectClause;
import org.quaere.expressions.Statement;
import org.quaere.expressions.TernaryExpression;
import org.quaere.expressions.UnaryExpression;
import org.quaere.expressions.WhereClause;

public class QuaereForJPAQueryEngine implements ExpressionTreeVisitor, QueryEngine {
    private final EntityManager entityManager;
    private List<String> sourceNames = new ArrayList<String>();
    private Map<String, QueryableEntity> sources = new HashMap<String, QueryableEntity>();
    private StringBuilder selectFragment = new StringBuilder();
    private StringBuilder fromFragment = new StringBuilder();
    private StringBuilder whereFragment = new StringBuilder();
    private String currentFragment;

    public QuaereForJPAQueryEngine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void visit(FromClause expression) {
        sourceNames.add(expression.identifier.name);
        expression.sourceExpression.accept(this);
        if (sources.containsKey(currentFragment)) {
            currentFragment = sources.get(currentFragment).getEntityName();
        }
        fromFragment.append(currentFragment);
        expression.identifier.accept(this);
        fromFragment.append(" AS ");
        fromFragment.append(currentFragment);
        fromFragment.append(',');
        currentFragment = null;
    }
    public void visit(GroupClause expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(JoinClause expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(OrderByClause expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(DeclareClause expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(WhereClause expression) {
        expression.getExpression().accept(this);
        whereFragment.append(currentFragment);
    }
    public void visit(SelectClause expression) {
        expression.getExpression().accept(this);
        selectFragment.append(currentFragment);
        selectFragment.append(',');
        currentFragment = null;
    }
    public void visit(QueryBody expression) {
        for (QueryBodyClause clause : expression.getClauses()) {
            clause.accept(this);
        }
        if (expression.hasSelectOrGroupClause()) {
            expression.getSelectOrGroupClause().accept(this);
        }
        if (expression.hasContinuation()) {
            expression.getContinuation().accept(this);
        }
    }
    public void visit(QueryContinuation expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(QueryExpression expression) {
        expression.getFrom().accept(this);
        expression.getQueryBody().accept(this);
    }
    public void visit(BinaryExpression expression) {
        expression.leftExpression.accept(this);
        String leftFragment = currentFragment;
        expression.rightExpression.accept(this);
        String rightFragment = currentFragment;
        String operatorFragment = "";
        switch (expression.operator) {
            case AND:
                operatorFragment = "AND";
                break;
            case OR:
                operatorFragment = "OR";
                break;
            case EQUAL:
                operatorFragment = "=";
                break;
            case NOT_EQUAL:
                operatorFragment = "<>";
                break;
            case GREATER_THAN:
                operatorFragment = ">";
                break;
            case GREATER_THAN_OR_EQUAL:
                operatorFragment = ">=";
                break;
            case LESS_THAN:
                operatorFragment = "<";
                break;
            case LESS_THAN_OR_EQUAL:
                operatorFragment = "<=";
                break;
            default:
                throw new UnsupportedOperationException("Operator not supported yet!");
        }
        currentFragment = leftFragment + " " + operatorFragment + " " + rightFragment;
    }
    public void visit(TernaryExpression expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(UnaryExpression expression) {
        throw new RuntimeException("The method is not implemented");
    }

    int parameterIndex = 1;
    private Map<Integer, Object> parameterMap = new HashMap<Integer, Object>();

    public void visit(Constant expression) {
        currentFragment = "?" + parameterIndex;
        parameterMap.put(parameterIndex, expression.value);
        parameterIndex++;
    }

    public void visit(Identifier expression) {
        if (sourceNames.contains(expression.name)) {
            currentFragment = expression.name;
        }
        else {
            Constant asConstant = new Constant(expression.name, String.class);
            this.visit(asConstant);
        }
    }
    public void visit(MethodCall expression) {
        String methodName = expression.getIdentifier().name;
        if (methodName.startsWith("get")) {
            currentFragment = methodName.substring("get".length());
            currentFragment = currentFragment.substring(0, 1).toLowerCase() + currentFragment.substring(1);
        }
        else if (methodName.startsWith("is")) {
            currentFragment = methodName.substring("is".length());
            currentFragment = currentFragment.substring(0, 1).toLowerCase() + currentFragment.substring(1);
        }
        else {
            throw new RuntimeException("Cannot translate method " + methodName + " to property");
        }
        currentFragment = "." + currentFragment;
    }
    public void visit(Indexer expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(Statement expression) {
        StringBuilder fragmentBuilder = new StringBuilder();
        for (Expression e : expression.getExpressions()) {
            e.accept(this);
            fragmentBuilder.append(currentFragment);
        }
        currentFragment = fragmentBuilder.toString();
    }
    public void visit(Parameter expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void visit(NewExpression expression) {
        throw new RuntimeException("The method is not implemented");
    }
    public void addSource(Identifier identifer, Queryable<?> source) {
        if (!(source instanceof QueryableEntity)) {
            throw new IllegalArgumentException("Only QueryableEnity can be used as a source");
        }
        this.sources.put(identifer.name, (QueryableEntity) source);
    }
    public <T> T evaluate(Expression query) {
        query.accept(this);
        return (T) query(getJPQL(), parameterMap);
    }

    @SuppressWarnings("unchecked")
    private <T> T query(String jpql, Map<Integer, Object> parameterMap) {
        Query query = entityManager.createQuery(jpql);
        for (Integer parameterIndex : parameterMap.keySet()) {
            query.setParameter(parameterIndex, parameterMap.get(parameterIndex));
        }
        return (T) query.getResultList();
    }

    private String getJPQL() {
        return String.format("%s %s %s",
                getFragment("SELECT", selectFragment),
                getFragment("FROM", fromFragment),
                getFragment("WHERE", whereFragment)).trim();
    }
    private String getFragment(String clause, StringBuilder fragmentBuilder) {
        if (fragmentBuilder.length() == 0) {
            return "";
        }
        else {
            fragmentBuilder.insert(0, clause + " ");
            String fragment = fragmentBuilder.toString();
            if (fragment.endsWith(",")) {
                fragment = fragment.substring(0, fragment.length() - 1);
            }
            return fragment;
        }
    }

}
