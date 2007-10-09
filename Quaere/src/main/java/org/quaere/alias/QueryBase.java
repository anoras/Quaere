package org.quaere.alias;

public abstract class QueryBase implements RowVisitor {

    protected int index;
    protected Condition condition;
    
    protected void addCondition(Condition condition) {
        if (this.condition != null) {
            this.condition = new ConditionAnd(this.condition, condition);
        } else {
            this.condition = condition;
        }
    }
    
    protected <T> void initSelect() {
        index = 0;
    }
    
    public int getIndex() {
        return index;
    }
    
    protected <U, T> U createFromTemplate(Class<U> clazz, U template, T item) {
        U obj = Utils.createNew(clazz);
        return obj;
    }

    abstract Object getValue(Object o, Object t);

}
