package org.quaere.quaere4objects;

import static org.quaere.DSL.*;
import org.quaere.model.Product;
import org.quaere.Variant;
import org.junit.Test;
import junit.framework.Assert;

public class QuantifiersTestCase {
    @Test
    public void linq67()
    {
        String[] words = { "believe", "relief", "receipt", "field" };
        Assert.assertTrue(any(words,"w => w.contains(\"ei\")"));
    }
    @Test
    public void linq69()
    {
        Product[]  products= Product.getAllProducts();
        Iterable<Variant> productGroups=
                from("p").in(products).
                group("p").by("p.getCategory()").into("g").
                where(any("g.getGroup()","p => p.getUnitsInStock() == 0")).
                select(
                    create(
                        property("category","g.getKey()"),
                        property("products","g.getGroup()")
                    )
                );
        for (Variant v: productGroups) {
            System.out.println(v.get("category"));
        }
    }
}
