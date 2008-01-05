package org.quaere.quaereForJPA;

import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.quaereForJPA.model.Customer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitTransactionType;

public class ScenariosTest {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    protected final Log log = LogFactory.getLog(ScenariosTest.class);

    @Before
    public void setup() {
        Session session = sessionFactory.openSession();
        Customer[] customers = Customer.getAllCustomers();
        for (Customer c : customers) {
            session.save(c);
        }
        session.flush();
        session.close();

        BasicConfigurator.configure();
    }
    @Test
    public void canQueryHibernate() {
        EntityManagerFactory entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
        QueryableEntityManager entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());

        Iterable<Customer> waCustomers =
                from("c").in(entityManager.entity(Customer.class)).
                        where(eq("c.getRegion()", "WA")).
                        select("c");

        for (Customer c : waCustomers) {
            log.info(c);
            Assert.assertEquals("WA", c.getRegion());
        }
    }
    @Test
    @Ignore("This test goes into an endless loop after a patch for Comparable was applied.")
    public void canUseSkipOperatorToSkipRows() {
        EntityManagerFactory entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
        QueryableEntityManager entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());

        Iterable<Customer> allCustomersExcept10First =
                skip(10).<Customer>in(
                        from("c").in(entityManager.entity(Customer.class)).
                                select("c")
                );

        int counter = 0;
        for (Customer c : allCustomersExcept10First) {
            log.info(c);
            counter++;
        }
        Assert.assertEquals(Customer.getAllCustomers().length - 10, counter);
    }
    @Test
    @Ignore("This test goes into an endless loop after a patch for Comparable was applied.")
    public void canUseTakeToLimitSelection() {
        EntityManagerFactory entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
        QueryableEntityManager entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());

        Iterable<Customer> allCustomersExcept10First =
                take(2).<Customer>from(
                        from("c").in(entityManager.entity(Customer.class)).
                                select("c")
                );

        int counter = 0;
        for (Customer c : allCustomersExcept10First) {
            log.info(c);
            counter++;
        }
        Assert.assertEquals(2, counter);
    }
    @Test
    @Ignore
    public void canPageSelectionByCombiningTakeAndSkip() {
//        EntityManagerFactory entityManagerFactory;
//        QueryableEntityManager entityManager;
//        entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
//        entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());
//
//        Customer tenthCustomer =
//                first(
//                        skip(10).<Customer>in(
//                                from("c").in(entityManager.entity(Customer.class)).
//                                        select("c")
//                        )
//                );
//
//        entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
//        entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());
//
//        Iterable<Customer> customers10To20 =
//                take(10).from(
//                         skip(10).in( (QueryContinuationBuider<Customer>)
//                                from("c").in(entityManager.entity(Customer.class)).
//                                        select("c")
//                        )
//                );
//
//        int counter = 0;
//        Customer firstInResult = null;
//        for (Object c : customers10To20) {
//            log.info(c);
//            if (counter == 0) firstInResult = (Customer) c;
//            counter++;
//        }
//        Assert.assertEquals(10, counter);
//        Assert.assertEquals(tenthCustomer, firstInResult);
    }

    @Test
    public void duplicateQueryElementsRegessionTest() {
        EntityManagerFactory entityManagerFactory = new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
        QueryableEntityManager entityManager = new QueryableEntityManager(entityManagerFactory.createEntityManager());

        Iterable<Customer> waCustomers =
                from("c").in(entityManager.entity(Customer.class)).
                        where(eq("c.getRegion()", "WA")).
                        select("c");

        waCustomers.iterator();
        waCustomers.iterator();
    }
}
