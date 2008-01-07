package org.quaere.jpa;

import javax.persistence.EntityManager;

public class QueryableEntityManager {
    public final EntityManager entityManager;
    public QueryableEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public <T> QueryableEntity<T> entity(Class<T> clazz) {
        return new QueryableEntity<T>(entityManager, clazz);
    }
}
