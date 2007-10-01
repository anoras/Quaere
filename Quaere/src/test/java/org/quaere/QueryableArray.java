package org.quaere;

import org.quaere.expressions.Identifier;
import org.quaere.expressions.Expression;
import org.quaere.dsl.parser.QuaereParser;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class QueryableArray<T> implements Queryable<T> {
    private final List<T> innerList;


    public QueryableArray(T[] array) {
        this.innerList = Arrays.asList(array);
    }
    public QueryEngine createQueryEngine() {
        return new QueryableArrayQueryEngine(this);
    }
    public Identifier getSourceIdentifier(Identifier identifier) {
        return new Identifier("__quare_quaere_quaere_" + identifier.getText());
    }
    public Iterator<T> iterator() {
        return innerList.iterator();
    }
    public class QueryableArrayQueryEngine implements QueryEngine {
        private QueryableArray<T> source;
        public QueryableArrayQueryEngine(QueryableArray<T> queryableArray) {
            this.source = queryableArray;
        }
        public void addSource(Identifier identifer, Queryable<?> source) {

        }
        public <T> T evaluate(Expression query) {
            return (T) this.source.innerList;
        }
    }
}
