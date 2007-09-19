package org.quaere.quaere4objects;

import junit.framework.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.model.Product;
import org.quaere.model.Customer;

import java.util.Arrays;

public class RestrictionOperatorsTestCase {
    @Test
    public void canUseWhereRestrictionToFindAllElementsInArrayLess_linq1() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> lowNums =
                from("n").in(numbers).
                where(lt("n", 5)).
                select("n");

        Assert.assertEquals(Arrays.asList(4, 1, 3, 2, 0), asList(lowNums));
    }
    @Test
    public void canUseWhereRestrictionToFindAllOutOfStockProducts_linq2() {
        Product[] products = Product.getAllProducts();
        Iterable<Product> soldOutProducts =
                from("p").in(products).
                where(eq("p.getUnitsInStock()", 0)).
                select("p");

        for (Product p : soldOutProducts) {
            Assert.assertEquals(0, p.getUnitsInStock());
        }
    }
    @Test
    public void canUseWhereRestrictionToFindAllInStockProductsThatCostMoreThan3_linq3() {
        Product[] products = Product.getAllProducts();
        Iterable<Product> expensiveProducts =
                from("p").in(products).
                where(and(gt("p.getUnitsInStock()", 0), gt("p.getUnitPrice()", 3.0))).
                select("p");

        for (Product p : expensiveProducts) {
            Assert.assertTrue(p.getUnitsInStock() > 0);
            Assert.assertTrue(p.getUnitPrice() > 3.0);
        }
    }
    @Test
    public void canUseWhereRestrictionsToFindAllCustomersInWashingtonAndDrillDownIntoTheirOrders_linq4() {
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Customer> waCustomers =
                from("c").in(customers).
                where(eq("c.getRegion()", "WA")).
                select("c");

        for (Customer c : waCustomers) {
            Assert.assertEquals("WA", c.getRegion());
        }
    }



}
