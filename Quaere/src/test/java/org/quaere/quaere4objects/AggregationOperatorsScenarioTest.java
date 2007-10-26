package org.quaere.quaere4objects;

import org.junit.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Product;

import java.util.HashMap;
import java.util.List;

public class AggregationOperatorsScenarioTest {
    @Test
    public void canUseMinToGetLowestNumberInIntegerArray_linq81() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2}; // NOTE: Removed 0 from list to improve test case...
        Integer minNum = min.in(Integer.class, numbers);
        Assert.assertEquals(1, minNum);
    }
    @Test
    public void canUseMinToGetTheTengthOfTheShortestWordInAStringArray_linq82() {
        String[] words = {"cherry", "apple", "blueberry"};
        Integer shortestWord = min.<Integer>qualify("w").by("w.length()").in(Integer.class, words);
        Assert.assertEquals("apple".length(), shortestWord);
    }

    @Test
    public void canFindTheLowestOfAllProductPricesForEachCategory_linq83() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        select(
                                create(
                                        property("Category", "g.getKey()"),
                                        property("CheapestPrice", min.qualify("p").by("p.getUnitPrice()").in("g.getGroup()"))
                                )
                        );
        HashMap<String, Double> expectedCategories = new HashMap<String, Double>();
        expectedCategories.put("Beverages", 4.5D);
        expectedCategories.put("Condiments", 10D);
        expectedCategories.put("Produce", 10D);
        expectedCategories.put("Meat/Poultry", 7.45D);
        expectedCategories.put("Seafood", 6D);
        expectedCategories.put("Dairy Products", 2.5D);
        expectedCategories.put("Confections", 9.2D);
        expectedCategories.put("Grains/Cereals", 7D);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), category.get("CheapestPrice"));
        }
    }

    @Test
    public void canFindTheCheapestProductInEachCategory_linq84() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        declare("minPrice").as(min.qualify("p").by("p.getUnitPrice()").in("g.getGroup()")).
                        select(
                                create(
                                        property("Category", "g.getKey()"),
                                        property("CheapestProducts",
                                                from("p").in("g.getGroup()").
                                                        where(eq("minPrice", "p.getUnitPrice()")).
                                                        select("p")
                                        )
                                )
                        );
        HashMap<String, Integer> expectedCategories = new HashMap<String, Integer>();
        expectedCategories.put("Beverages", 24);
        expectedCategories.put("Condiments", 3);
        expectedCategories.put("Produce", 74);
        expectedCategories.put("Meat/Poultry", 54);
        expectedCategories.put("Seafood", 13);
        expectedCategories.put("Dairy Products", 33);
        expectedCategories.put("Confections", 19);
        expectedCategories.put("Grains/Cereals", 52);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), ((List<Product>) category.get("CheapestProducts")).get(0).getProductID());
        }
    }
    @Test
    public void canUseMaxToGetTheHighestNumberInAnIntegerArray_linq85() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Integer maxNum = max.in(Integer.class, numbers);
        Assert.assertEquals(9, maxNum);
    }
    @Test
    public void canUseMaxToGetTheLengthOfTheLongestWordInAStringArray_linq86() {
        String[] words = {"cherry", "apple", "blueberry"};
        Integer longestWord = max.<Integer>qualify("w").by("w.length()").in(Integer.class, words);
        Assert.assertEquals("blueberry".length(), longestWord);
    }
    @Test
    public void canFindTheHighestOfAllProductPricesForEachCategory_linq87() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        select(
                                create(
                                        property("Category", "g.getKey()"),
                                        property("CheapestPrice", max.qualify("p").by("p.getUnitPrice()").in("g.getGroup()"))
                                )
                        );
        HashMap<String, Double> expectedCategories = new HashMap<String, Double>();
        expectedCategories.put("Beverages", 263.5D);
        expectedCategories.put("Condiments", 43.9D);
        expectedCategories.put("Produce", 53D);
        expectedCategories.put("Meat/Poultry", 123.79D);
        expectedCategories.put("Seafood", 62.5D);
        expectedCategories.put("Dairy Products", 55D);
        expectedCategories.put("Confections", 81D);
        expectedCategories.put("Grains/Cereals", 38D);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), category.get("CheapestPrice"));
        }
    }
    @Test
    public void canFindTheMostExpensiveProductInEachCategory_linq88() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        declare("minPrice").as(max.qualify("p").by("p.getUnitPrice()").in("g.getGroup()")).
                        select(
                                create(
                                        property("Category", "g.getKey()"),
                                        property("CheapestProducts",
                                                from("p").in("g.getGroup()").
                                                        where(eq("minPrice", "p.getUnitPrice()")).
                                                        select("p")
                                        )
                                )
                        );
        HashMap<String, Integer> expectedCategories = new HashMap<String, Integer>();
        expectedCategories.put("Beverages", 38);
        expectedCategories.put("Condiments", 63);
        expectedCategories.put("Produce", 51);
        expectedCategories.put("Meat/Poultry", 29);
        expectedCategories.put("Seafood", 18);
        expectedCategories.put("Dairy Products", 59);
        expectedCategories.put("Confections", 20);
        expectedCategories.put("Grains/Cereals", 56);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), ((List<Product>) category.get("CheapestProducts")).get(0).getProductID());
        }
    }
    @Test
    public void canUseAverageToGetTheAverageValueOfAllIntegersInAnArray_linq89() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Integer maxNum = avg.in(Integer.class, numbers);
        Assert.assertEquals(4, maxNum);
    }
    @Test
    public void canUseAverageToGetTheAverageLengthOfTheWordsInAStringArray_linq90() {
        String[] words = {"cherry", "apple", "blueberry"};
        Integer longestWord = avg.<Integer>qualify("w").by("w.length()").in(Integer.class, words);
        Assert.assertEquals(6, longestWord);
    }
    @Test
    public void canFindTheAveragePriceforAllProductPricesForEachCategory_linq91() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        select(
                                create(
                                        property("Category", "g.getKey()"),
                                        property("CheapestPrice", avg.qualify("p").by("p.getUnitPrice()").in("g.getGroup()"))
                                )
                        );
        HashMap<String, Double> expectedCategories = new HashMap<String, Double>();
        expectedCategories.put("Beverages", 37.979166666666666666666666667D);
        expectedCategories.put("Condiments", 23.0625D);
        expectedCategories.put("Produce", 32.37D);
        expectedCategories.put("Meat/Poultry", 54.006666666666666666666666667D);
        expectedCategories.put("Seafood", 20.6825D);
        expectedCategories.put("Dairy Products", 28.73D);
        expectedCategories.put("Confections", 25.16D);
        expectedCategories.put("Grains/Cereals", 20.25D);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), category.get("CheapestPrice"));
        }
    }
}
