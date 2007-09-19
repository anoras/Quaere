package org.quaere.quaere4objects;

import junit.framework.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.model.Customer;

import java.util.Arrays;

public class PartitioningOperatorsTestCase {
    @Test
    public void canUseTakeToGetOnlyTheFirstThreeElementsOfAnArray_linq20() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> firstThreeNumbers = take(3, numbers);

        Assert.assertEquals(Arrays.asList(5, 4, 1), firstThreeNumbers);
    }
    @Test
    public void canUseTakeToGetTheFirstThreeOrdersFromCustomersInWashington_linq21() {
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Object> firstThreeWAOrders =
                take(3,
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
        for (Object o : firstThreeWAOrders) {
            count++;
        }
        Assert.assertEquals(3, count);
    }
    @Test
    public void canUseSkipToGetAllButTheFirstFourElementsOfAnArray_linq22() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> allButFirst4Numbers = skip(4, numbers);

        int index = 4;
        for (Integer n : allButFirst4Numbers) {
            Assert.assertEquals(numbers[index++], n);
        }
        Assert.assertEquals(numbers.length, index);
    }
    @Test
    public void canUseSkipToGetAllButTheFirstTwoOrdersFromCustomersInWashington_linq23() {
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Object> allButFirst2Orders =
                skip(2,
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
        Iterable<Integer> firstNumbersLessThan6 = takeWhile("n => n < 6", numbers);
        Assert.assertEquals(Arrays.asList(5, 4, 1, 3), firstNumbersLessThan6);
    }
    @Test
    public void canUseTakeWhileToReturnAllElementsFromTheBeginningOfAnArrayWhileNIsLessThanItsPositionInTheArray_linq25() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> firstSmallNumbers = takeWhile("(n, index) => n >= index", numbers);
        Assert.assertEquals(Arrays.asList(5, 4), firstSmallNumbers);
    }
    @Test
     public void canUseSkipWhileToGetAllNumbersInArrayStartingFromTheFirstElementDivisibleByThree_linq26() {
         Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
         Iterable<Integer> numbersDivisibleBy3=skipWhile("n => n % 3 != 0",numbers);

        Assert.assertEquals(Arrays.asList(3, 9, 8, 6, 7, 2, 0), numbersDivisibleBy3);
     }
    @Test
    public void canUseSkipWhileToGetAllElementsInAnArrayStartingFromTheFirstElementLessThanItsPosition_linq27() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> laterNumbers = skipWhile("(n, index) => n >= index", numbers);

        Assert.assertEquals(Arrays.asList(1, 3, 9, 8, 6, 7, 2, 0), laterNumbers);
    }}
