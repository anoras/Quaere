package org.quaere.quaereForJPA;

import org.quaere.QueryEngine;

import javax.persistence.EntityManager;

public class QueryableEntityManager {
    public final EntityManager entityManager;
    private QuaereForJPAQueryEngine engine;
    public QueryableEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public <T> QueryableEntity<T> entity(Class<T> clazz) {
        return new QueryableEntity<T>(this, clazz);
    }
    public String getJPQL() {
        return engine.getJPQL();
    }
    public <T> QueryEngine createQueryEngine(QueryableEntity<T> queryableEntity) {
        if (engine == null) {
            engine = new QuaereForJPAQueryEngine(this);
        }
        return engine;
    }
    public <T> T query(String jpql) {
        return (T) entityManager.createQuery(jpql).getResultList();
    }
}
