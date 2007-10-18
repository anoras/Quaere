package org.quaere.alias;

import java.util.ArrayList;
import java.util.List;

public class QueryJoin extends QueryBase {

    private ArrayList<Selector> selectors;

    QueryJoin(ArrayList<Selector> list) {
        this.selectors = list;
    }
    
    public QueryJoin where(Condition condition) {
        addCondition(condition);
        return this;
    }

    public <U> List<U> select(final U template, final Assign ... assign) {
        return select(false, template, assign);
    }

    public <U> List<U> selectDistinct(final U template, final Assign ... assign) {
        return select(true, template, assign);
    }

    private <U> List<U> select(boolean distinct, final U template, final Assign ... assign) {
        initSelect();        
        final ArrayList<Row<U>> result = Utils.createArrayList();
        final FieldMapping<U> mapping = ListProvider.getMapping(template);
        final Class<U> clazz = mapping.getObjectClass();
        final QueryJoin me = this;
        for (int i = 0; i < selectors.size() - 1; i++) {
            Selector s = selectors.get(i);
            s.setJoin(selectors.get(i + 1));
        }
        Selector main = selectors.get(0);
        main.iterate(new ListVisitor<U>() {
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
    
    Object getValue(Object o) {
        if (o instanceof Function) {
            Function f = (Function) o;
            return f.getValue(this);
        }
        // TODO use a hash map for performance
        for (Selector s : selectors) {
            Object t = s.getCurrentItem();
            Object result = s.getMapping().getValue(o, t);
            if (result != o) {
                return result;
            }
        }
        return o;
    }

}
