package org.quaere.alias;

import java.util.ArrayList;
import java.util.List;

public class Query<T> extends QueryBase {
    
    private Selector<T> selector;
    
    Query(Selector<T> selector) {
        this.selector = selector;
    }

    public Query<T> where(Condition condition) {
        addCondition(condition);
        return this;
    }

    public List<T> select() {
        initSelect();
        ArrayList<T> result = Utils.createArrayList();
        List<T> list = selector.getList();
        for (T item : list) {
            if (condition == null || condition.test(item, this)) {
                result.add(item);
            }
            index++;
        }
        return result;
    }
    
    public <U> List<U> select(U template, Assign ... assign) {
        initSelect();
        ArrayList<U> result = Utils.createArrayList();
        List<T> list = selector.getList();
        FieldMapping<U> mapping = ListProvider.getMapping(template);
        Class<U> clazz = mapping.getObjectClass();
        for (T item : list) {
            if (condition == null || condition.test(item, this)) {
                U obj = createFromTemplate(clazz, template, item);
                for (Assign a : assign) {
                    a.set(this, mapping, obj, item);
                }
                result.add(obj);
            }
            index++;
        }
        return result;
    }
    
    public <U> List<U> select(Object object) {
        initSelect();
        ArrayList<U> result = Utils.createArrayList();
        List<T> list = selector.getList();
        for (T item : list) {
            if (condition == null || condition.test(item, this)) {
                Object o = getValue(object, item);
                U converted = (U) selector.getMapping().convert(o, object.getClass());
                result.add(converted);
            }
            index++;
        }
        return result;
    }

    Object getValue(Object o, Object t) {
        if (o instanceof Function) {
            Function f = (Function) o;
            return f.getValue(this, t);
        }
        return selector.getMapping().getValue(o, (T) t);
    }

}
