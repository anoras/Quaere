package org.quaere.quaere4objects;

import org.junit.Assert;
import org.junit.Test;
import org.quaere.Convert;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Product;

import java.util.HashMap;
import java.util.List;

public class AggregationOperatorsScenarioTest {
    @Test
    public void canUseMinToGetLowestNumberInIntegerArray_linq81() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2}; // NOTE: Removed 0 from list to improve test case...
        Double minNum = min.in(numbers);
        Assert.assertEquals(1D, minNum);
    }
    @Test
    public void canUseMinToGetTheTengthOfTheShortestWordInAStringArray_linq82() {
        String[] words = {"cherry", "apple", "blueberry"};
        Double shortestWord = min.<Double>qualify("w").by("w.length()").in(words);
        Assert.assertEquals(Convert.coerce("apple".length(), Double.class), shortestWord);
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
        Product[] products= Product.getAllProducts();
        Iterable<Variant> categories =
                from("p").in(products).
                group("p").by("p.getCategory()").into("g").
                declare("minPrice").as(min.qualify("p").by("p.getUnitPrice()").in("g.getGroup()")).
                select(
                    create(
                        property("Category","g.getKey()"),
                        property("CheapestProducts",
                                from("p").in("g.getGroup()").
                                where(eq("minPrice","p.getUnitPrice()")).
                                select("p")
                                )
                    )        
                );
        HashMap<String,Integer> expectedCategories=new HashMap<String, Integer>();
        expectedCategories.put("Beverages",24);
        expectedCategories.put("Condiments",3);
        expectedCategories.put("Produce",74);
        expectedCategories.put("Meat/Poultry",54);
        expectedCategories.put("Seafood",13);
        expectedCategories.put("Dairy Products",33);
        expectedCategories.put("Confections",19);
        expectedCategories.put("Grains/Cereals",52);
        for (Variant category : categories) {
            Assert.assertEquals(expectedCategories.get(category.get("Category")), ((List<Product>)category.get("CheapestProducts")).get(0).getProductID());
        }

    }
}
