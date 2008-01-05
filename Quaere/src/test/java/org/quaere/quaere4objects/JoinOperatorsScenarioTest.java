package org.quaere.quaere4objects;

import org.junit.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Product;

import java.util.Arrays;
import java.util.List;

public class JoinOperatorsScenarioTest {
    @Test
    public void canEfficentlyJoinElementsOfTwoSequencesBasedOnEqualityBetweenKeyExpresionsOverTheTwo_linq102() {
        // NOTE: This is a test for inner joins.
        String[] categories = {
                "Beverages",
                "Condiments",
                "Vegetables",
                "Dairy Products",
                "Seafood"
        };
        Product[] products = Product.getAllProducts();
        Iterable<Variant> innerJoinQuery =
                from("category").in(categories).
                        join("prod").in(products).on("category").equals("prod.getCategory()").
                        select(
                                create(
                                        property("category"),
                                        property("prod.getProductName()")
                                )
                        );
        List<String> expectedCombinations = Arrays.asList("Seafood: Ikura",
                "Seafood: Konbu",
                "Seafood: Carnarvon Tigers",
                "Seafood: Nord-Ost Matjeshering",
                "Seafood: Inlagd Sill",
                "Seafood: Gravad lax",
                "Seafood: Boston Crab Meat",
                "Seafood: Jack's New England Clam Chowder",
                "Seafood: Rogede sild",
                "Seafood: Spegesild",
                "Seafood: Escargots de Bourgogne",
                "Seafood: Röd Kaviar",
                "Dairy Products: Queso Cabrales",
                "Dairy Products: Queso Manchego La Pastora",
                "Dairy Products: Gorgonzola Telino",
                "Dairy Products: Mascarpone Fabioli",
                "Dairy Products: Geitost",
                "Dairy Products: Raclette Courdavault",
                "Dairy Products: Camembert Pierrot",
                "Dairy Products: Gudbrandsdalsost",
                "Dairy Products: Flotemysost",
                "Dairy Products: Mozzarella di Giovanni",
                "Condiments: Aniseed Syrup",
                "Condiments: Chef Anton's Cajun Seasoning",
                "Condiments: Chef Anton's Gumbo Mix",
                "Condiments: Grandma's Boysenberry Spread",
                "Condiments: Northwoods Cranberry Sauce",
                "Condiments: Genen Shouyu",
                "Condiments: Gula Malacca",
                "Condiments: Sirop d'érable",
                "Condiments: Vegie-spread",
                "Condiments: Louisiana Fiery Hot Pepper Sauce",
                "Condiments: Louisiana Hot Spiced Okra",
                "Condiments: Original Frankfurter grüne Soße",
                "Beverages: Chai",
                "Beverages: Chang",
                "Beverages: Guaraná Fantástica",
                "Beverages: Sasquatch Ale",
                "Beverages: Steeleye Stout",
                "Beverages: Côte de Blaye",
                "Beverages: Chartreuse verte",
                "Beverages: Ipoh Coffee",
                "Beverages: Laughing Lumberjack Lager",
                "Beverages: Outback Lager",
                "Beverages: Rhönbräu Klosterbier",
                "Beverages: Lakkalikööri");
        int itemCount = 0;
        for (Variant item : innerJoinQuery) {
            Assert.assertTrue(expectedCombinations.contains(String.format("%s: %s", item.get("category"), item.get("productName"))));
            itemCount++;
        }
        Assert.assertEquals(expectedCombinations.size(), itemCount);
    }
    @Test
    public void canUseGroupJoinToGetAllTheProductsThatMatchAGivenCategoryBundledAsASequence_linq103() {
        String[] categories = {
                "Beverages",
                "Condiments",
                "Vegetables",
                "Dairy Products",
                "Seafood"
        };
        Product[] products = Product.getAllProducts();
        Iterable<Variant> q =
                from("c").in(categories).
                        join("p").in(products).on("c").equals("p.getCategory()").into("ps").
                        select(
                                create(
                                        property("category", "c"),
                                        property("products", "ps")
                                )
                        );
        int rowCount = 0;
        for (Variant v : q) {
            rowCount++;
            System.out.println(v.get("category"));
            for (Product p : (Iterable<Product>) v.get("products")) {
                System.out.println("    " + p.getProductName() + " (" + p.getCategory() + ")");
                Assert.assertEquals(v.get("category"), p.getCategory());
            }
        }
        Assert.assertEquals(categories.length - 1, rowCount);   // There is no "Vegtable" category, hence this is empty.
    }
    // TODO: Write a test case for linq104

}
