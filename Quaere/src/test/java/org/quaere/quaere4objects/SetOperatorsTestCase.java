package org.quaere.quaere4objects;

import org.quaere.model.Product;
import org.quaere.model.Customer;
import static org.quaere.DSL.*;
import org.junit.Test;
import org.junit.Ignore;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

public class SetOperatorsTestCase {
    @Test
    public void canUseDistinctToFindUniqueCategoryNames_linq47() {
        Product[] products = Product.getAllProducts();
        Iterable<Object> categoryNames =
                distinct(
                        from("p").in(products).select("p.getCategory()")
                );

        List<String> expectedCategoryNames = Arrays.asList("Beverages", "Condiments", "Produce", "Meat/Poultry", "Seafood", "Dairy Products", "Confections", "Grains/Cereals");
        Assert.assertEquals(expectedCategoryNames, categoryNames);
    }
    @Test
    public void canUseUnionToCreateOneSequenceThatContainsTheUniqueValeusFromTwoAArrays_linq48() {
        Integer[] numbersA = {0, 2, 4, 5, 6, 8, 9};
        Integer[] numbersB = {1, 3, 5, 7, 8};
        Iterable<Integer> uniqueNumbers = union(numbersA, numbersB);

        Assert.assertEquals(Arrays.asList(0, 2, 4, 5, 6, 8, 9, 1, 3, 7), uniqueNumbers);
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseUnionToCreateOneSequenceThatContainsTheUniqueFirstLettersFromBothProductAndCustomerNames_linq49() {
        Product[] products = Product.getAllProducts();
        Customer[] customers = Customer.getAllCustomers();
        Iterable<String> productFirstChars =
                from("p").in(products).select("p.getProductName()[0]");
        Iterable<String> customerFirstChars =
                from("c").in(customers).select("c.getCompanyName()[0]");
        Iterable<String> uniqueFirstChars = union(productFirstChars, customerFirstChars);

        List<String> expectedUniqueFirstChars = Arrays.asList("C", "A", "G", "U", "N", "M", "I", "Q", "K", "T", "P", "S", "R", "B", "J", "Z", "V", "F", "E", "W", "L", "O", "D", "H");
        Assert.assertEquals(expectedUniqueFirstChars, uniqueFirstChars);
    }
    @Test
    public void canUseIntersectToCreateOneSequenceThatContainsTheCommonValuesSharedByTwoArrays_linq50() {
        Integer[] numbersA = {0, 2, 4, 5, 6, 8, 9};
        Integer[] numbersB = {1, 3, 5, 7, 8};
        Iterable<Integer> commonNumbers = intersect(numbersA, numbersB);

        for (Integer n : commonNumbers) {
            Assert.assertTrue(Arrays.binarySearch(numbersA, n) >= 0);
            Assert.assertTrue(Arrays.binarySearch(numbersB, n) >= 0);
        }
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseIntersectToCreateOneSequenceThatContainsTheCommonFirstLetterFromBothProductAndCustomerNames_linq51() {
        Product[] products = Product.getAllProducts();
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Character> productFirstChars =
                from("p").in(products).select("p.getProductName()[0]");
        Iterable<Character> customerFirstChars =
                from("c").in(customers).select("c.getCompanyName()[0]");
        Iterable<Character> commonFirstChars = intersect(productFirstChars, customerFirstChars);

        Collection<Character> productFirstCharsCollection = (Collection<Character>) productFirstChars;
        Collection<Character> customerFirstCharsCollection = (Collection<Character>) customerFirstChars;
        for (Character c : commonFirstChars) {
            Assert.assertTrue(productFirstCharsCollection.contains(c));
            Assert.assertTrue(customerFirstCharsCollection.contains(c));
        }
    }
    @Test
    public void canUseExceptToCreateASequenceOfTheNumbersInOneArrayThatAreNotAlsoInAnotherArray_linq52() {
        Integer[] numbersA = {0, 2, 4, 5, 6, 8, 9};
        Integer[] numbersB = {1, 3, 5, 7, 8};
        Iterable<Integer> aOnlyNumbers = except(numbersA, numbersB);

        for (Integer n : aOnlyNumbers) {
            Assert.assertTrue(Arrays.binarySearch(numbersA, n) >= 0);
            Assert.assertTrue(Arrays.binarySearch(numbersB, n) < 0);
        }
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseExceptToCreateASequenceThatContainsTheFirstLettersOfProductNamesThatAreNotAlsoFirstLettersOfCustomerNames_linq53() {
        Product[] products = Product.getAllProducts();
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Character> productFirstChars =
                from("p").in(products).select("p.getProductName()[0]");
        Iterable<Character> customerFirstChars =
                from("c").in(customers).select("c.getCompanyName()[0]");
        Iterable<Character> productOnlyFirstChars = except(productFirstChars, customerFirstChars);

        Collection<Character> productFirstCharsCollection = (Collection<Character>) productFirstChars;
        Collection<Character> customerFirstCharsCollection = (Collection<Character>) customerFirstChars;
        for (Character c : productOnlyFirstChars) {
            Assert.assertTrue(productFirstCharsCollection.contains(c));
            Assert.assertFalse(customerFirstCharsCollection.contains(c));
        }
    }
}
