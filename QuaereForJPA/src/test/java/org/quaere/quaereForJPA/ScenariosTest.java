package org.quaere.quaereForJPA;

import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.Test;
import org.junit.Before;
import static org.quaere.DSL.eq;
import static org.quaere.DSL.from;
import org.quaere.model.Customer;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Appender;

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
}
