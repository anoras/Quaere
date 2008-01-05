package org.quaere.quaereForJPA;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.quaereForJPA.model.Customer;
import org.quaere.quaereForJPA.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class QuaereForJPAQueryEngineTest {
    final Mockery context = new Mockery();
    final Query emptyQuery = context.mock(Query.class);
    @Before
    public void setup() {
        context.checking(new Expectations() {
            {
                one(emptyQuery).getResultList();
                will(returnValue(new ArrayList()));
            }
        });
    }
    @Test
    public void fromEntitySelectIsTranslatedCorrectly() {
        final EntityManager entityManager = context.mock(EntityManager.class);
        context.checking(new Expectations() {
            {
                one(entityManager).createQuery("SELECT c FROM Customer AS c");
                will(returnValue(emptyQuery));
            }
        });

        QueryableEntityManager mgr = new QueryableEntityManager(entityManager);
        Iterable<Customer> allCustomers = from("c").in(mgr.entity(Customer.class)).select("c");
        allCustomers.iterator(); // <--- Trigger query execution..

    }
    @Test
    public void fromNavigationSelectIsTranslatedCorrectly() {
        final EntityManager entityManager = context.mock(EntityManager.class);
        context.checking(new Expectations() {
            {
                one(entityManager).createQuery("SELECT o FROM Customer AS c,c.orders AS o");
                will(returnValue(emptyQuery));
            }
        });

        QueryableEntityManager mgr = new QueryableEntityManager(entityManager);
        Iterable<Customer> allOrders =
                from("c").in(mgr.entity(Customer.class)).
                        from("o").in("c.getOrders()").
                        select("o");
        allOrders.iterator(); // <--- Trigger query execution..
    }
    @Test
    public void multipleFromSelectIsTranslatedCorrectly() {
        final EntityManager entityManager = context.mock(EntityManager.class);
        context.checking(new Expectations() {
            {
                one(entityManager).createQuery("SELECT o FROM Customer AS c,Order AS o");
                will(returnValue(emptyQuery));
            }
        });

        QueryableEntityManager mgr = new QueryableEntityManager(entityManager);
        Iterable<Customer> allOrders =
                from("c").in(mgr.entity(Customer.class)).
                        from("o").in(mgr.entity(Order.class)).
                        select("o");
        allOrders.iterator(); // <--- Trigger query execution..
    }
    @Test
    public void whereIsTranslatedCorrectly() {
        final EntityManager entityManager = context.mock(EntityManager.class);
        context.checking(new Expectations() {
            {
                one(entityManager).createQuery("SELECT c FROM Customer AS c WHERE c.country = ?1 AND c.region <> ?2");
                will(returnValue(emptyQuery));
                one(emptyQuery).setParameter(1, "USA");
                one(emptyQuery).setParameter(2, "WA");
            }
        });

        QueryableEntityManager mgr = new QueryableEntityManager(entityManager);
        Iterable<Customer> americanCustomersOutsideWashington =
                from("c").in(mgr.entity(Customer.class)).
                        where(eq("c.getCountry()", "USA").and(ne("c.getRegion()", "WA"))).
                        select("c");
        americanCustomersOutsideWashington.iterator(); // <--- Trigger query execution..

    }
}
