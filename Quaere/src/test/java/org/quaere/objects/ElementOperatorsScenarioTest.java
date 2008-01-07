package org.quaere.objects;

import junit.framework.Assert;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.model.Product;

public class ElementOperatorsScenarioTest {
    @Test
    public void canUseFirstToReturnTheFirstMatchingElementAsAProductInsteadOfASequenceContainingAProduct_linq58() {
        Product[] products = Product.getAllProducts();
        Product product12 = first(
                from("p").in(products).
                        where(eq("p.getProductID()", 12)).
                        select("p")
        );

        Assert.assertEquals(12, product12.getProductID());
    }
    @Test
    public void canUseFirstToFindTheFirstElementInAnArrayThatStartsWithO_linq59() {
        String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String startsWithO = first.in(strings).as("s").where("s.startsWith(\"o\")");
        Assert.assertEquals("one", startsWithO);
    }
    @Test
    public void canUseFirstWithAnIndexParameterToFindTheFirstNumberThatIsBothEvenAndIsAtAnEvenIndexWithinTheArray_linq60() {
        // NOTE: This scenario is commented out in the LINQ 101 samples.
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Integer evenNumber = first.in(numbers).as("num").withIndexer("index").where(eq("num % 2", 0).and(eq("index % 2", 0)));
        Assert.assertEquals(6, (int) evenNumber);
    }
}
