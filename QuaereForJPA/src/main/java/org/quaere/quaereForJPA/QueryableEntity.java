package org.quaere.quaereForJPA;

import org.quaere.Queryable;
import org.quaere.QueryEngine;
import org.quaere.quaereForJPA.QuaereForJPAQueryEngine;
import org.quaere.expressions.Identifier;

import javax.persistence.Entity;
import java.util.Iterator;
import java.util.ArrayList;

public class QueryableEntity<T> implements Queryable<T> {
    private final QueryableEntityManager entityManager;
    private final Class<T> entityClass;

    public QueryableEntity(QueryableEntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }
    public QueryEngine createQueryEngine() {
        return entityManager.createQueryEngine(this);
    }
    public Identifier getSourceIdentifier(Identifier identifier) {
        return identifier;
    }
    public Iterator<T> iterator() {
        return new ArrayList<T>().iterator();
    }
    public String getEntityName() {
        Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        if (entityAnnotation != null) {
            return entityAnnotation.name();
        } else {
            return entityClass.getName().substring(entityClass.getName().lastIndexOf(".") + 1);
        }
    }
}
