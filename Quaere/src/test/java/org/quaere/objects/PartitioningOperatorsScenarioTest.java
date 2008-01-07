package org.quaere.objects;

import junit.framework.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Customer;

import java.util.Arrays;

public class PartitioningOperatorsScenarioTest {
    @Test
    public void canUseTakeToGetOnlyTheFirstThreeElementsOfAnArray_linq20() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> firstThreeNumbers = take(3).from(numbers);

        Assert.assertEquals(Arrays.asList(5, 4, 1), firstThreeNumbers);
    }
    @SuppressWarnings({"RedundantTypeArguments"})
    @Test
    public void canUseTakeToGetTheFirstThreeOrdersFromCustomersInWashington_linq21() {
        Customer[] customers = Customer.getAllCustomers();
        // NOTE: Explicit type argument is required before the take.from clause to support Eclipse.
        Iterable<Variant> firstThreeWAOrders = take(3).<Variant>from(
                from("c").in(customers).
                        from("o").in("c.getOrders()").
                        where(eq("c.getRegion()", "WA")).
                        select(
                        create(
                                property("c.getCustomerID()"),
                                property("o.getOrderID()"),
                                property("o.getOrderDate()")
                        )
                )
        );
        int count = 0;
        for (Variant order : firstThreeWAOrders) {
            count++;
        }
        Assert.assertEquals(3, count);
    }
    @Test
    public void canUseSkipToGetAllButTheFirstFourElementsOfAnArray_linq22() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> allButFirst4Numbers = skip(4).in(numbers);

        int index = 4;
        for (Integer n : allButFirst4Numbers) {
            Assert.assertEquals(numbers[index++], n);
        }
        Assert.assertEquals(numbers.length, index);
    }
    @SuppressWarnings({"RedundantTypeArguments"})
    @Test
    public void canUseSkipToGetAllButTheFirstTwoOrdersFromCustomersInWashington_linq23() {
        Customer[] customers = Customer.getAllCustomers();
        // NOTE: Explicit type argument is required before the take.from clause to support Eclipse.
        Iterable<Variant> allButFirst2Orders =
                skip(2).<Variant>in(
                        from("c").in(customers).
                                from("o").in("c.getOrders()").
                                where(eq("c.getRegion()", "WA")).
                                select(
                                create(
                                        property("c.getCustomerID()"),
                                        property("o.getOrderID()"),
                                        property("o.getOrderDate()")
                                )
                        )
                );
    }
    @Test
    public void canUseTakeWhileToReturnAllElementsFromTheBeginningOfAnArrayUntilNIsNotLessThanSix_linq24() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> firstNumbersLessThan6 = take("n").from(numbers).when(lt("n", 6));
        //take(numbers).as("n").when(lt("n", 6));
        Assert.assertEquals(Arrays.asList(5, 4, 1, 3), firstNumbersLessThan6);
    }
    @Test
    public void canUseTakeWhileToReturnAllElementsFromTheBeginningOfAnArrayWhileNIsLessThanItsPositionInTheArray_linq25() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> firstSmallNumbers = take("n").withIndexer("index").from(numbers).when(ge("n", "index"));
        //take(numbers).as("n").withIndexer("index").when(ge("n", "index"));
        Assert.assertEquals(Arrays.asList(5, 4), firstSmallNumbers);
    }
    @Test
    public void canUseSkipWhileToGetAllNumbersInArrayStartingFromTheFirstElementDivisibleByThree_linq26() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> numbersDivisibleBy3 = skip("n").in(numbers).when(ne("n % 3", 0));

        Assert.assertEquals(Arrays.asList(3, 9, 8, 6, 7, 2, 0), numbersDivisibleBy3);
    }

    @Test
    public void canUseSkipWhileToGetAllElementsInAnArrayStartingFromTheFirstElementLessThanItsPosition_linq27() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> laterNumbers = skip("n").withIndexer("index").in(numbers).when(ge("n", "index"));

        Assert.assertEquals(Arrays.asList(1, 3, 9, 8, 6, 7, 2, 0), laterNumbers);
    }
}
