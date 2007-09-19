package org.quaere.expressions;

import java.util.List;
import java.util.Comparator;

public class OrderByClause extends QueryBodyClause {
    private final List<OrderByCriteria> criterias;

    public OrderByClause(List<OrderByCriteria> criterias) {
        this.criterias = criterias;
    }

    public List<OrderByCriteria> getCriterias() {
        return criterias;
    }

// --------------------- Interface ExpressionTreeNode ---------------------


    public void accept(ExpressionTreeVisitor visitor) {
        visitor.visit(this);
    }
}
