package org.quaere.quaere4objects;

import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Product;

public class JoinOperatorsScenarioTest {
    @Test
    public void canEfficentlyJoinElementsOfTwoSequencesBasedOnEqualityBetweenKeyExpresionsOverTheTwo_linq102() {
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
                        join("p").in(products).on("c").equals("p.getCategory()").
                        select(
                                create(
                                        property("category", "c"),
                                        property("p.getProductName()")
                                )
                        );
        for (Variant v : q) {
            System.out.println(String.format("%s: %s", v.get("category"), v.get("productName")));
        }
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
        for (Variant v : q) {
            System.out.println(String.format("%s: %s", v.get("category"), v.get("products")));
        }
    }
    // TODO: Write a test case for linq104
}
