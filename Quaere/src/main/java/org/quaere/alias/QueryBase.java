package org.quaere.alias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class QueryBase implements RowVisitor {

    protected int index;
    protected Condition condition;
    protected List<Order> order;
    
    protected void addCondition(Condition condition) {
        if (this.condition != null) {
            this.condition = new ConditionAnd(this.condition, condition);
        } else {
            this.condition = condition;
        }
    }
    
    public void addOrder(Object... orderList) {
        if (this.order == null) {
            order = new ArrayList<Order>();
        }
        for (Object x : orderList) {
            if (!(x instanceof Order)) {
                x = new Order(x, true, true);
            }
            order.add((Order) x);
        }
    }
    
    <T> List<T> order(List<Row<T>> list) {
        if (order != null) {
            Collections.sort(list, new Comparator<Row <T>>() {
                public int compare(Row<T> a, Row<T> b) {
                    for (int i = 0; i < order.size(); i++) {
                        int comp = order.get(i).compare(a.getOrderList()[i], b.getOrderList()[i]);
                        if (comp != 0) {
                            return comp;
                        }
                    }
                    return 0;
                }
            });
        }
        List<T> result = new ArrayList<T>();
        for (Row<T> r : list) {
            result.add(r.getData());
        }
        return result;
    }
    
    <T> void addRow(List<Row<T>> list, T item) {
        Object[] o;
        if (order == null) {
            o = null;
        } else {
            int len = order.size();
            o = new Object[len];
            for (int i = 0; i < len; i++) {
                o[i] = getValue(order.get(i).getItem());
            }
        }
        Row<T> row = new Row<T>(item, o);
        list.add(row);
    }
    
    protected <T> void initSelect() {
        index = 0;
    }
    
    public int getIndex() {
        return index;
    }
    
    protected <U, T> U createFromTemplate(Class<U> clazz, U template) {
        U obj = Utils.createNew(clazz);
        return obj;
    }

    abstract Object getValue(Object o);

}
