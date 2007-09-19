package org.quaere.expressions;

import java.util.Comparator;

public class OrderByCriteria {
    private final Expression expression;
    private final Direction direction;
    private final Comparator comparator;
    public OrderByCriteria(Expression expression, Direction direction) {
        this.expression = expression;
        this.direction = direction;
        this.comparator =null;
    }

    public OrderByCriteria(Expression expression, Direction direction, Comparator comparator) {
        this.expression = expression;
        this.direction = direction;
        this.comparator = comparator;
    }
    public Direction getDirection() {
        return direction;
    }

    public Expression getExpression() {
        return expression;
    }

    public Comparator getComparator() {
        return comparator;
    }
    public enum Direction {
        ASCENDING,
        DESCENDING
    }
}
