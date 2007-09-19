package org.quaere.quaere4objects;

import static org.quaere.DSL.*;
import org.junit.Test;
import org.junit.Ignore;
import junit.framework.Assert;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class ConversionOperatorsTestCase {
    @Test
    public void canUseAsArrayToImmendiatlyEvaluateASequenceIntoAnArray_linq54() {
        // NOTE: Renamed ToArray (LINQ) -> asArray (this)
        Double[] doubles = {1.7, 2.3, 1.9, 4.1, 2.9};
        Iterable<Double> sortedDoubles =
                from("d").in(doubles).orderByDescending("d").select("d");
        Double[] doublesArray = asArray(new Double[0], sortedDoubles);

        Assert.assertEquals(doubles.length, doublesArray.length);
        Double lastValue = Double.MAX_VALUE;
        for (Double d : doublesArray) {
            Assert.assertTrue(lastValue >= d);
            lastValue = d;
        }
    }
    @Test
    @Ignore
    public void canUseAsListToImmediatlyEvaluateASequenceIntoAList() {
        // NOTE: Renamed ToList (LINQ) -> asList (this)
        String[] words = {"cherry", "apple", "blueberry"};
        List<Object> sortedWords =
                asList(
                    from("w").in(words).orderByDescending("w.length()").select("w")
                );

        Integer lastLength = 0;
        for (Object o : sortedWords) {
            String word= (String) o;
            System.out.println(word);
            Assert.assertNotNull("lastLength",lastLength); // <- For some unexplainable reason this assertion fails for the word "cherry" !?
            Assert.assertNotNull("word.length()",word.length());
            Assert.assertTrue(lastLength <= word.length());
            lastLength = word.length();
        }
    }

    @Test
    public void canUseOfClassToReturnOnlyTheElementsOfAnArrayThatAreOfClassDouble_linq57() {
        Object[] numbers = {null, 1.0, "two", 3, "four", 5, "six", 7.0};
        Iterable<Double> doubles = ofClass(Double.class, numbers);

        Assert.assertEquals(Arrays.asList(1.0, 7.0), doubles);
    }
}
