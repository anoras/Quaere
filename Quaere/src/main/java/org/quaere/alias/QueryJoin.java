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

    public <U> List<U> select(U template, Assign ... assign) {
        ArrayList<U> result = Utils.createArrayList();
        FieldMapping<U> mapping = ListProvider.getMapping(template);
        Class<U> clazz = mapping.getObjectClass();
        index = 0;
//        List<T> list = selector.getList();
//        for (T item : list) {
//            if (condition == null || condition.test(item, this)) {
//                U obj = createFromTemplate(clazz, template, item);
//                for (Assign a : assign) {
//                    a.set(this, mapping, obj, item);
//                }
//                result.add(obj);
//            }
//            index++;
//        }
        return result;
    }
    
    Object getValue(Object o, Object t) {
        if (o instanceof Function) {
            Function f = (Function) o;
            return f.getValue(this, t);
        }
        // TODO use a hash map for performance
        for (Selector s : selectors) {
            if (s.getInstance() == t) {
                return s.getMapping().getValue(o, t);
            }
        }
        throw new Error("Unknown alias: " + t);
    }

}
