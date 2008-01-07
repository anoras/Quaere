package org.quaere.objects;

import static org.quaere.DSL.*;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.quaere.model.Product;

import java.util.Arrays;

public class OrderingOperatorsScenarioTest {
    @Test
    public void canUseOrderByToSortAListOfWordsAlphabetiacally_linq28() {
        String[] words = {"cherry", "apple", "blueberry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderBy("w").
                        select("w");

        Arrays.sort(words);
        Assert.assertEquals(Arrays.asList(words), asList(sortedWords));
    }
    @Test
    public void canUseOrderByToSortAListOfWordsByTheLengthOfEachWord_linq29() {
        String[] words = {"cherry", "apple", "blueberry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderBy("w.length()").
                        select("w");

        Integer lastLength = Integer.MIN_VALUE;
        for (String word : sortedWords) {
            Assert.assertTrue(word.length() >= lastLength);
            lastLength = word.length();
        }
    }
    @Test
    public void canUseOrderByToSortAListOfProductsByTheProductName_linq30() {
        Product[] products = Product.getAllProducts();
        Iterable<Product> sortedProducts =
                from("p").in(products).
                        orderBy("p.getProductName()").
                        select("p");
        // NOTE: Moved ids 16 and 53 two places to the left to account for different collation in Java and .NET.
        int[] expectedProductIds = {17, 3, 40, 60, 18, 1, 2, 39, 4, 5, 48, 38, 58, 52, 71, 33, 15, 56, 31, 6, 37, 24, 69, 44, 26, 22, 10, 36, 43, 41, 13, 76, 67, 74, 65, 66, 51, 32, 49, 9, 72, 30, 8, 25, 77, 70, 16, 53, 55, 11, 12, 59, 57, 75, 45, 73, 28, 34, 27, 68, 42, 20, 21, 61, 46, 35, 62, 19, 29, 14, 54, 23, 7, 50, 63, 64, 47};
        int index = 0;
        for (Product p : sortedProducts) {
            Assert.assertEquals(expectedProductIds[index++], p.getProductID());
        }
    }
    @Test
    public void canUseOrderByWithACustomComparatorToPerformACaseInsensitiveSortOfTheWordsInAnArray_linq32() {
        String[] words = {"aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderBy("w", String.CASE_INSENSITIVE_ORDER).
                        select("w");
        String[] expectedWords = {"AbAcUs", "aPPLE", "BlUeBeRrY", "bRaNcH", "cHeRry", "ClOvEr"};
        int index = 0;
        for (String word : sortedWords) {
            Assert.assertEquals(expectedWords[index++], word);
        }
    }
    @Test
    public void canUseOrderByDescendingToOrderAListOfDoublesFromHighetsToLowest_linq33() {
        Double[] doubles = {1.7, 2.3, 1.9, 4.1, 2.9};
        Iterable<Double> sortedDoubles =
                from("d").in(doubles).
                        orderByDescending("d").
                        select("d");
        double[] expectedDoubles = {4.1, 2.9, 2.3, 1.9, 1.7};
        int index = 0;
        for (Object d : sortedDoubles) {
            Assert.assertEquals(expectedDoubles[index++], d);
        }
        Assert.assertEquals(expectedDoubles.length, index);
    }
    @Test
    public void canUseOrderByToSortAListOfProductsInStockFromHighestToLowest_linq34() {
        Product[] products = Product.getAllProducts();
        Iterable<Product> sortedProducts =
                from("p").in(products).
                        orderByDescending("p.getUnitsInStock()").
                        select("p");
        // NOTE: Order of expected IDs is diffrent from LINQ scenario because products with same number of units
        // in stock are collated differently.
        int[] expectedProductIds = {75, 40, 6, 55, 61, 33, 36, 34, 22, 73, 46, 12, 41, 59, 25, 65, 39, 50, 58, 23, 76, 4, 67, 27, 18, 20, 1, 15, 52, 47, 57, 14, 77, 10, 9, 16, 44, 28, 42, 69, 71, 19, 13, 63, 11, 64, 54, 56, 24, 35, 51, 60, 2, 38, 43, 62, 7, 26, 48, 70, 72, 3, 37, 30, 49, 32, 8, 68, 45, 66, 74, 21, 5, 17, 29, 31, 53};
        int index = 0;
        int lastUnitsInStock = Integer.MAX_VALUE;
        for (Object obj : sortedProducts) {
            Product p = (Product) obj;
            Assert.assertTrue(lastUnitsInStock >= p.getUnitsInStock());
            lastUnitsInStock = p.getUnitsInStock();
            Assert.assertEquals(expectedProductIds[index++], p.getProductID());
        }
    }
    @Test
    public void canUseOrderbByWithACustomComparatorToPerformACaseInsensitiveDescendingSortOfTheWordsInAnArray_linq35() {
        String[] words = {"aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderByDescending("w", String.CASE_INSENSITIVE_ORDER).
                        select("w");
        String[] expectedWords = {"ClOvEr", "cHeRry", "bRaNcH", "BlUeBeRrY", "aPPLE", "AbAcUs"};
        int index = 0;
        for (Object word : sortedWords) {
            Assert.assertEquals(expectedWords[index++], word);
        }
    }
    @Test
    public void canUseMultipleOrderByClausesToSortAListOfDigitsByItsLengthAndThenByItsName_linq36() {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Iterable<String> sortedDigits =
                from("d").in(digits).
                        orderBy("d.length()").orderBy("d").
                        select("d");
        String[] expectedDigits = {"one", "six", "two", "five", "four", "nine", "zero", "eight", "seven", "three"};
        int index = 0;
        for (Object d : sortedDigits) {
            Assert.assertEquals(expectedDigits[index++], d);
        }
    }
    @Test
    public void canCombineOrderByAndThenByClauseWithCustomComparatorToSortFirstByWordLengthAndThenByACaseInsensitiveSortOfTheWordsInAnArray_linq37() {
        String[] words = {"aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderBy("w.length()").orderBy("w", String.CASE_INSENSITIVE_ORDER).
                        select("w");
        String[] expectedWords = {"aPPLE", "AbAcUs", "bRaNcH", "cHeRry", "ClOvEr", "BlUeBeRrY"};
        int index = 0;
        for (Object word : sortedWords) {
            Assert.assertEquals(expectedWords[index++], word);
        }
    }
    @Test
    public void canUseCompundOrderByClauseToSortAListOfProductsFirstByCategoryAndThenByUnitPriceFromHighestToLowest_linq38() {
        Product[] products = Product.getAllProducts();
        Iterable<Product> sortedProducts =
                from("p").in(products).
                        orderBy("p.getCategory()").orderByDescending("p.getUnitPrice()").
                        select("p");
        int[] expectedProductIds = {38, 43, 2, 1, 35, 39, 76, 70, 34, 67, 75, 24, 63, 8, 61, 6, 4, 5, 65, 44, 66, 15, 77, 3, 20, 62, 27, 26, 49, 16, 50, 25, 48, 68, 21, 47, 19, 59, 12, 69, 72, 60, 32, 71, 11, 31, 33, 56, 64, 22, 57, 42, 23, 52, 29, 9, 17, 53, 55, 54, 51, 28, 7, 14, 74, 18, 10, 37, 30, 36, 40, 73, 58, 46, 41, 45, 13};
        int index = 0;
        for (Object obj : sortedProducts) {
            Product p = (Product) obj;
            Assert.assertEquals(expectedProductIds[index++], p.getProductID());
        }
    }
    @Test
    public void canCombineOrderByAndThenByClauseWithACustomComparatorToSortFirstByWordLengthAndThenByACaseInsensitiveDecendingSortOfTheWordsInAnArray_lin39() {
        String[] words = {"aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
        Iterable<String> sortedWords =
                from("w").in(words).
                        orderBy("w.length()").orderByDescending("w", String.CASE_INSENSITIVE_ORDER).
                        select("w");
        String[] expectedWords = {"aPPLE", "ClOvEr", "cHeRry", "bRaNcH", "AbAcUs", "BlUeBeRrY"};
        int index = 0;
        for (Object word : sortedWords) {
            Assert.assertEquals(expectedWords[index++], word);
        }
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseReverseToCreateAListOfDigitsWhoseSecondLetterIsIThatIsReversedFromTheOrderOfTheOrignialArray_linq39() {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Iterable<Object> reversedIDigits =
                reverse(
                        from("d").in(digits).
                                where(eq("d[1]", "i")).
                                select("d")
                );
        String[] expectedDigits = {"nine", "eight", "six", "five"};
        int index = 0;
        for (Object d : reversedIDigits) {
            Assert.assertEquals(expectedDigits[index++], d);
        }
    }
}
