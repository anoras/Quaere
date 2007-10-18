package org.quaere.alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Query<T> extends QueryBase {
    
    private Selector<T> selector;
    
    Query(Selector<T> selector) {
        this.selector = selector;
    }
    
    public <U> QueryJoin join(U[] array, Object alias) {
        return join(Arrays.asList(array), alias);
    }
    
    public <U> QueryJoin join(List<U> list, Object alias) {
        ArrayList<Selector> selectors = new ArrayList<Selector>();
        selectors.add(selector);
        FieldMapping mapping = ListProvider.getMapping(alias);
        Selector selector = new Selector(alias, mapping, list);
        selectors.add(selector);
        return new QueryJoin(selectors);
    }

    public QueryJoin join(int[] array, Object alias) {
        return join(Utils.toIntegerArray(array), alias);
    }
    
    public Query<T> where(Condition condition) {
        addCondition(condition);
        return this;
    }
    
    public Query<T> orderBy(Object... orderList) {
        addOrder(orderList);
        return this;
    }    

    public List<T> select() {
        return select(false);
    }
    
    public List<T> selectDistinct() {
        return select(true);
    }
    
    private List<T> select(boolean distinct) {
        initSelect();
        final ArrayList<Row <T>> result = Utils.createArrayList();
        final Query<T> me = this;
        selector.iterate(new ListVisitor<T>() {
            public void visit() {
                if (condition == null || condition.test(me)) {
                    T item = selector.getCurrentItem();
                    addRow(result, item);
                }
                index++;
            }
        });
        return order(result, distinct);
    }

    public <U> List<U> select(final U template, final Assign ... assign) {
        return select(false, template, assign);
    }

    public <U> List<U> selectDistinct(final U template, final Assign ... assign) {
        return select(true, template, assign);
    }

    private <U> List<U> select(boolean distinct, final U template, final Assign ... assign) {
        initSelect();
        final ArrayList<Row <U>> result = Utils.createArrayList();
        final Query<T> me = this;
        final FieldMapping<U> mapping = ListProvider.getMapping(template);
        final Class<U> clazz = mapping.getObjectClass();
        selector.iterate(new ListVisitor<T>() {
            public void visit() {
                if (condition == null || condition.test(me)) {
                    U obj = createFromTemplate(clazz, template);
                    for (Assign a : assign) {
                        a.set(me, mapping, obj);
                    }
                    addRow(result, obj);
                }
                index++;
            }
        });
        return order(result, distinct);
    }
    
    public <U> List<U> select(final Object object) {
        return select(false, object);
    }
    
    public <U> List<U> selectDistinct(final Object object) {
        return select(true, object);
    }
    
    private <U> List<U> select(boolean distinct, final Object object) {
        initSelect();
        final ArrayList<Row <U>> result = Utils.createArrayList();
        final Query<T> me = this;
        selector.iterate(new ListVisitor<T>() {
            public void visit() {
                if (condition == null || condition.test(me)) {
                    Object o = getValue(object);
                    U converted = (U) selector.getMapping().convert(o, object.getClass());
                    addRow(result, converted);
                }
                index++;
            }
        });
        return order(result, distinct);
    }

    Object getValue(Object o) {
        if (o instanceof Function) {
            Function f = (Function) o;
            return f.getValue(this);
        }
        T t = selector.getCurrentItem();
        return selector.getMapping().getValue(o, t);
    }

}
