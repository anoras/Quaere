package org.quaere.quaereForJPA;

import org.junit.Test;
import org.jmock.Mockery;
import org.quaere.quaereForJPA.model.NamedEntity;
import org.quaere.quaereForJPA.model.UnnamedEntity;
import org.quaere.expressions.Identifier;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

public class QueryableEntityTest {
    final Mockery context = new Mockery();
    final EntityManager dummyManager = context.mock(EntityManager.class);
    @Test
    public void entityNameIsAliasForNamedEntities() {
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<NamedEntity> queryableEntity = manager.entity(NamedEntity.class);
        Assert.assertEquals("Named", queryableEntity.getEntityName());
    }
    @Test
    public void entityNameIsClassNameForUnnamedEntities() {
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<UnnamedEntity> queryableEntity = manager.entity(UnnamedEntity.class);
        Assert.assertEquals("UnnamedEntity", queryableEntity.getEntityName());
    }
    @Test
    public void identifierIsNotChanged() {
        Identifier identifer = new Identifier("id");
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<UnnamedEntity> queryableEntity = manager.entity(UnnamedEntity.class);
        Assert.assertSame(identifer, queryableEntity.getSourceIdentifier(identifer));

    }
}
