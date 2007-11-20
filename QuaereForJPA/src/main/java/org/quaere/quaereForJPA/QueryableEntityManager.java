package org.quaere.quaereForJPA;

import java.util.List;
import java.util.Map;

import org.quaere.QueryEngine;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryableEntityManager {
    public final javax.persistence.EntityManager entityManager;
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
    public <T> T query(String jpql, Map<Integer,Object> parameterMap) {
    	Query query = entityManager.createQuery(jpql);
    	for (Integer parameterIndex : parameterMap.keySet()) {
    		query.setParameter(parameterIndex, parameterMap.get(parameterIndex));
    	}
        return (T) query.getResultList();
    }
}
