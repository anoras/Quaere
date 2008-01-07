package org.quaere.jpa;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.quaere.QueryEngine;
import org.quaere.Queryable;
import org.quaere.expressions.Identifier;

public class QueryableEntity<T> implements Queryable<T> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public QueryableEntity(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }
    public QueryEngine createQueryEngine() {
        return new JpaQueryEngine(entityManager);
    }
    public Identifier getSourceIdentifier(Identifier identifier) {
        return identifier;
    }
    public Iterator<T> iterator() {
        return new ArrayList<T>().iterator();
    }
    public String getEntityName() {
        Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        if (entityAnnotation != null && isNotEmpty(entityAnnotation.name())) {
            return entityAnnotation.name();
        } else {
            return entityClass.getSimpleName();
        }
    }
    private boolean isNotEmpty(String s) {
        return s == null ? false : !"".equals(s);
    }
}
