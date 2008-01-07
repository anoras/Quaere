package org.quaere.objects;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.expressions.*;
import org.quaere.model.Customer;
import org.quaere.model.Order;
import org.quaere.model.Product;

import java.util.*;

public class ProjectionOperatorsScenarioTest {
    @Test
    public void canUseProjectionToProduceASequenceOfIntsOneHigherThanAnExistingArrayOfInts_linq6() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Integer> numsPlusOne =
                from("n").in(numbers).
                        select("n + 1");

        int index = 0;
        for (Integer n : numsPlusOne) {
            Assert.assertEquals(numbers[index++] + 1, (int) n);
        }
    }
    @Test
    public void canUseProjectionToReturnASequenceOfJustTheNamesOfAListOfProducts_linq7() {
        List<Product> products = Arrays.asList(Product.getAllProducts());
        Iterable<String> productNames =
                from("p").in(products).
                        select("p.getProductName()");

        Iterator<Product> productIterator = products.iterator();
        Iterator<String> productNameIterator = productNames.iterator();
        while (productIterator.hasNext() && productNameIterator.hasNext()) {
            Assert.assertEquals(productIterator.next().getProductName(), productNameIterator.next());
        }
        Assert.assertFalse(productIterator.hasNext());
        Assert.assertFalse(productNameIterator.hasNext());
    }
    @Test
    public void canUseProjectionToProduceASequenceOfStringsRepresentingTheTextVersionOfASequenceOfInts_linq8() {
        final Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        final String[] strings = {
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
        };
        Iterable<String> textNums =
                from("n").in(numbers).
                        select(
                                new Indexer(
                                        new Statement(Arrays.<Expression>asList(new Constant(strings, strings.getClass()))),
                                        new Identifier("n")
                                )
                        );
        List<String> expectedStrings = Arrays.asList(
                "five",
                "four",
                "one",
                "three",
                "nine",
                "eight",
                "six",
                "seven",
                "two",
                "zero"
        );
        Assert.assertEquals(expectedStrings, asList(textNums));
    }
    @Test
    public void canUseProjectionToCreateASequenceOfBothUpperAndLowercaseVersionsOfTheWordsInAnArray_linq9() {
        String[] words = {"aPPLE", "BlUeBeRrY", "cHeRry"};
        Iterable<Variant> upperLowerWords =
                from("w").in(words).
                        select(
                                create(
                                        property("upper", "w.toUpperCase()"),
                                        property("lower", "w.toLowerCase()")
                                )
                        );
        int index = 0;
        for (Variant ul : upperLowerWords) {
            Assert.assertEquals(words[index].toUpperCase(), ul.get("upper"));
            Assert.assertEquals(words[index].toLowerCase(), ul.get("lower"));
            index++;
        }
    }
    @Test
    public void canUseProjectionToProduceASequenceOfTextRepresentationOfDigitsAndWhetherTheLengthIsEvenOrOdd_linq10() {
        final Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        final String[] strings = {
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
        };
        Iterable<Variant> digitOddEvens =
                from("n").in(numbers).
                        select(
                                create(
                                        property(
                                                "digit",
                                                new Indexer(
                                                        new Statement(Arrays.<Expression>asList(new Constant(strings, strings.getClass()))),
                                                        new Identifier("n")
                                                )
                                        ),
                                        property("isEven", "n % 2 == 0")
                                )
                        );
        String[] expectedDigits = {
                "five",
                "four",
                "one",
                "three",
                "nine",
                "eight",
                "six",
                "seven",
                "two",
                "zero"
        };
        int index = 0;
        for (Variant num : digitOddEvens) {
            Assert.assertEquals(expectedDigits[index], num.get("digit"));
            Assert.assertEquals(numbers[index] % 2 == 0, num.get("isEven"));
            index++;
        }
    }
    @Test
    public void canUseProjectionToProduceASequenceContainingSomePropertiesOfProducts_linq11() {
        List<Product> products = Arrays.asList(Product.getAllProducts());
        Iterable<Variant> productInfos =
                from("p").in(products).
                        select(
                                create(
                                        property("p.getProductName()"),
                                        property("p.getCategory()"),
                                        property("price", "p.getUnitPrice()")
                                )
                        );

        Iterator<Product> productIterator = products.iterator();
        Iterator<Variant> productInfoIterator = productInfos.iterator();
        while (productIterator.hasNext() && productInfoIterator.hasNext()) {
            Product p = productIterator.next();
            Variant pi = productInfoIterator.next();
            Assert.assertEquals(p.getProductName(), pi.get("productName"));
            Assert.assertEquals(p.getCategory(), pi.get("category"));
            Assert.assertEquals(p.getUnitPrice(), pi.get("price"));
        }
        Assert.assertFalse(productIterator.hasNext());
        Assert.assertFalse(productInfoIterator.hasNext());
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseIndexedProjectionToDetermineIfTheValueMatchesItsPlaceInTheArray_linq12() {
        // TODO: Redesign: Too many magic strings here. 
//        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
//        Iterable<Variant> numsInPlace = select(numbers,"(num, index) => create {num = num, inPlace = (num == index)}");
//        int index = 0;
//        for (Variant numInPlace : numsInPlace) {
//            Integer n = (Integer) numInPlace.get("num");
//            Assert.assertEquals(numbers[index], n);
//            Assert.assertEquals(n == index, numInPlace.get("inPlace"));
//            index++;
//        }
    }
    @Test
    public void canCombineProjectionAndRestrictionToReturnTextFormOfEachDigitLessThanFive_linq13() {
        final Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        final String[] strings = {
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
        };
        Iterable<String> lowNums =
                from("n").in(numbers).
                        where(lt("n", 5)).
                        select(
                                new Indexer(
                                        new Statement(Arrays.<Expression>asList(new Constant(strings, strings.getClass()))),
                                        new Identifier("n")
                                )
                        );
        List<String> expectedNums = Arrays.asList("four", "one", "three", "two", "zero");
        Assert.assertEquals(expectedNums, asList(lowNums));
    }
    @Test
    public void canUseCompoundFromClauseToMakeAQueryThatReturnsAllPairsOfNumbersFromTwoArraysSuchThatTheNumberFromArrayAIsLessThanTheNumberFromArrayB_linq14() {
        Integer[] numbersA = {0, 2, 4, 5, 6, 8, 9};
        Integer[] numbersB = {1, 3, 5, 7, 8};
        Iterable<Variant> pairs =
                from("a").in(numbersA).
                        from("b").in(numbersB).
                        where(lt("a", "b")).
                        select(
                                create(
                                        property("a"),
                                        property("b")
                                )
                        );
        for (Variant pair : pairs) {
            Assert.assertTrue((Integer) pair.get("a") < (Integer) pair.get("b"));
        }
    }
    @Test
    public void canUseCompundFromClauseToSelectAllOrdersWhereTheOrderTotalIsLessThan500_linq15() {
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Variant> orders =
                from("c").in(customers).
                        from("o").in("c.getOrders()").
                        where(lt("o.getTotal()", 500.0)).
                        select(
                                create(
                                        property("c.getCustomerID()"),
                                        property("o.getOrderID()"),
                                        property("o.getTotal()")
                                )
                        );
        //assertions:
        List<String> expectedCustomerIds = new ArrayList<String>(Arrays.asList("ALFKI", "ALFKI", "ANATR", "ANATR", "ANATR", "ANTON", "ANTON", "AROUT", "AROUT", "AROUT", "AROUT", "AROUT", "AROUT", "AROUT", "AROUT", "BERGS", "BERGS", "BERGS", "BLAUS", "BLAUS", "BLAUS", "BLAUS", "BLONP", "BOLID", "BONAP", "BONAP", "BONAP", "BONAP", "BSBEV", "BSBEV", "BSBEV", "BSBEV", "BSBEV", "BSBEV", "BSBEV", "CACTU", "CACTU", "CACTU", "CACTU", "CACTU", "CENTC", "COMMI", "COMMI", "COMMI", "CONSH", "DRACD", "DRACD", "DRACD", "DRACD", "DUMON", "DUMON", "DUMON", "ERNSH", "FAMIA", "FAMIA", "FAMIA", "FAMIA", "FOLKO", "FOLKO", "FOLKO", "FOLKO", "FOLKO", "FOLKO", "FRANS", "FRANS", "FRANS", "FRANS", "FRANS", "FURIB", "FURIB", "FURIB", "FURIB", "GALED", "GALED", "GALED", "GALED", "GALED", "GODOS", "GODOS", "GOURL", "GOURL", "GOURL", "GOURL", "GREAL", "GREAL", "GREAL", "GREAL", "GREAL", "GROSR", "HANAR", "HANAR", "HILAA", "HILAA", "HILAA", "HILAA", "HILAA", "HUNGC", "HUNGC", "HUNGC", "HUNGC", "ISLAT", "ISLAT", "ISLAT", "ISLAT", "ISLAT", "KOENE", "KOENE", "KOENE", "LACOR", "LACOR", "LAMAI", "LAMAI", "LAMAI", "LAMAI", "LAMAI", "LAMAI", "LAMAI", "LAMAI", "LAUGB", "LAUGB", "LAUGB", "LAZYK", "LAZYK", "LEHMS", "LEHMS", "LEHMS", "LETSS", "LILAS", "LILAS", "LILAS", "LILAS", "LINOD", "LINOD", "LINOD", "LONEP", "LONEP", "LONEP", "LONEP", "LONEP", "LONEP", "MAGAA", "MAGAA", "MAGAA", "MAGAA", "MAISD", "MEREP", "MEREP", "MORGK", "MORGK", "NORTS", "NORTS", "NORTS", "OCEAN", "OCEAN", "OCEAN", "OTTIK", "PERIC", "PERIC", "PICCO", "PRINI", "QUEDE", "QUEDE", "QUEDE", "QUICK", "RANCH", "RANCH", "REGGC", "REGGC", "REGGC", "REGGC", "REGGC", "REGGC", "RICAR", "RICAR", "RICSU", "RICSU", "ROMEY", "ROMEY", "ROMEY", "ROMEY", "ROMEY", "SANTG", "SAVEA", "SIMOB", "SIMOB", "SPECD", "SPECD", "SPECD", "SPLIR", "SPLIR", "SPLIR", "SPLIR", "SUPRD", "THEBI", "THEBI", "THEBI", "THECR", "THECR", "TOMSP", "TOMSP", "TOMSP", "TORTU", "TORTU", "TRADH", "TRAIH", "VAFFE", "VICTE", "VICTE", "VICTE", "VICTE", "VICTE", "VINET", "VINET", "VINET", "WANDK", "WANDK", "WARTH", "WARTH", "WARTH", "WARTH", "WELLI", "WELLI", "WELLI", "WELLI", "WHITC", "WILMK", "WILMK", "WILMK", "WILMK", "WILMK", "WOLZA", "WOLZA", "WOLZA", "WOLZA"));
        for (Variant order : orders) { //for each order in the result
            Assert.assertTrue("did not get customer " + order.get("customerID") + " as many times as expected", expectedCustomerIds.contains(order.get("customerID"))); //assert that the order belongs to a customer we expect
            expectedCustomerIds.remove(order.get("customerID"));  //and remove that customer from the expected object - to make sure we didn't get less copies of the same customer than we expect
        }
        Assert.assertTrue("got the same customer too many times", expectedCustomerIds.isEmpty()); //finally, to make sure we didn't get MORE of the customer than expected - assert the expected list was emptied
    }
    @Test
    public void canUseCompundFromClauseToSelectAllOrdersWhereTheOrderWasMadeIn1998OrLater_linq16() {
        Customer[] customers = Customer.getAllCustomers();
        Calendar dec31st1996 = Calendar.getInstance();
        dec31st1996.set(1996, 11, 31);
        Date cutOffDate = dec31st1996.getTime();

        Iterable<Variant> orders =
                from("c").in(customers).
                        from("o").in("c.getOrders()").
                        where(gt("o.getOrderDate()", cutOffDate)).
                        select(
                                create(
                                        property("c.getCustomerID()"),
                                        property("o.getOrderID()"),
                                        property("o.getOrderDate()")
                                )
                        );
        List<Integer> inspectedOrderIds = new ArrayList<Integer>();
        for (Variant o : orders) {
            Assert.assertFalse(inspectedOrderIds.contains(o.get("orderID")));
            inspectedOrderIds.add((Integer) o.get("orderID"));
            Assert.assertTrue(((Date) o.get("orderDate")).compareTo(cutOffDate) > 0);
        }
    }
    @Test
    public void canUseCreateToCreateNewInstancesOfDefinedTypes() {

        Integer[] orderNumbers = {1, 2, 4, 8, 16};

        Iterable<Order> orders = from("n").in(orderNumbers).
                select(
                        create(
                                Order.class,
                                property("orderID", "n")
                        )
                );
        int idx = 0;
        for (Order o : orders) {
            Assert.assertEquals((int) orderNumbers[idx++], o.getOrderID());
        }
    }
}
