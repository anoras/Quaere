package org.quaere.objects;

import junit.framework.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Variant;
import org.quaere.model.Product;

public class QuantifiersScenarioTest {
    @Test
    public void linq67() {
        String[] words = {"believe", "relief", "receipt", "field"};
        Assert.assertTrue(any.in(words).as("w").where("w.contains(\"ei\")"));
    }
    @Test
    public void linq69() {
        Product[] products = Product.getAllProducts();
        Iterable<Variant> productGroups =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        where(any.in("g.getGroup()").as("p").where(eq("p.getUnitsInStock()", 0))).
                        select(
                                create(
                                        property("category", "g.getKey()"),
                                        property("products", "g.getGroup()")
                                )
                        );
        for (Variant v : productGroups) {
            System.out.println(v.get("category"));
        }
    }
}
