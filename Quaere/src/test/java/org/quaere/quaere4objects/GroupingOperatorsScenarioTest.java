package org.quaere.quaere4objects;

import junit.framework.Assert;
import org.apache.log4j.BasicConfigurator;
import org.junit.Ignore;
import org.junit.Test;
import static org.quaere.DSL.*;
import org.quaere.Group;
import org.quaere.Variant;
import org.quaere.model.Customer;
import org.quaere.model.Order;
import org.quaere.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GroupingOperatorsScenarioTest {
    public GroupingOperatorsScenarioTest() {
        BasicConfigurator.configure();
    }
    @Test
    public void canUseGroupByToPartitionAListOfNumbersByTheirRemainderWhenDividedByFive_linq40() {
        Integer[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        Iterable<Variant> numberGroups =
                from("n").in(numbers).
                        group("n").by("n % 5").into("g").
                        select(
                                create(
                                        property("remainder", "g.getKey()"),
                                        property("numbers", "g.getGroup()")
                                )
                        );

        HashMap<Integer, List<Integer>> expectedNumberGroups = new HashMap<Integer, List<Integer>>();
        expectedNumberGroups.put(0, Arrays.asList(5, 0));
        expectedNumberGroups.put(4, Arrays.asList(4, 9));
        expectedNumberGroups.put(1, Arrays.asList(1, 6));
        expectedNumberGroups.put(3, Arrays.asList(3, 8));
        expectedNumberGroups.put(2, Arrays.asList(7, 2));
        for (Variant g : numberGroups) {
            Assert.assertEquals(expectedNumberGroups.get(g.get("remainder")), g.get("numbers"));
        }
    }
    @Test
    @Ignore("Indexers don't work...")
    public void canUseGroupByToPartitionAListOfWordsByTheirFirstLetter_linq41() {
        String[] words = {"blueberry", "chimpanzee", "abacus", "banana", "apple", "cheese"};
        Iterable<Variant> wordGroups =
                from("w").in(words).
                        group("w").by("w[0]").into("g").
                        select(
                                create(
                                        property("firstLetter", "g.getKey()"),
                                        property("words", "g.getGroup()")
                                )
                        );
        HashMap<Character, List<String>> expectedWordGroups = new HashMap<Character, List<String>>();
        expectedWordGroups.put('b', Arrays.asList("blueberry", "banana"));
        expectedWordGroups.put('c', Arrays.asList("chimpanzee", "cheese"));
        expectedWordGroups.put('a', Arrays.asList("abacus", "apple"));
        for (Variant g : wordGroups) {
            System.out.println(g.get("firstLetter"));
            System.out.println(g.get("words"));
        }
    }
    @Test
    public void canUseGroupByClauseToPartitionAListOfProductsByCategory_linq42() {
        // NOTE: Renamed orderGroups (LINQ) -> productGroups (this)
        Product[] products = Product.getAllProducts();
        Iterable<Variant> productGroups =
                from("p").in(products).
                        group("p").by("p.getCategory()").into("g").
                        select(
                                create(
                                        property("category", "g.getKey()"),
                                        property("products", "g.getGroup()")
                                )
                        );
        HashMap<String, List<Integer>> expectedProductGroups = new HashMap<String, List<Integer>>();
        expectedProductGroups.put("Beverages", Arrays.asList(1, 2, 24, 34, 35, 38, 39, 43, 67, 70, 75, 76));
        expectedProductGroups.put("Condiments", Arrays.asList(3, 4, 5, 6, 8, 15, 44, 61, 63, 65, 66, 77));
        expectedProductGroups.put("Produce", Arrays.asList(7, 14, 28, 51, 74));
        expectedProductGroups.put("Meat/Poultry", Arrays.asList(9, 17, 29, 53, 54, 55));
        expectedProductGroups.put("Seafood", Arrays.asList(10, 13, 18, 30, 36, 37, 40, 41, 45, 46, 58, 73));
        expectedProductGroups.put("Dairy Products", Arrays.asList(11, 12, 31, 32, 33, 59, 60, 69, 71, 72));
        expectedProductGroups.put("Confections", Arrays.asList(16, 19, 20, 21, 25, 26, 27, 47, 48, 49, 50, 62, 68));
        expectedProductGroups.put("Grains/Cereals", Arrays.asList(22, 23, 42, 52, 56, 57, 64));
        for (Variant g : productGroups) {
            System.out.println(g.get("category"));
            System.out.println(g.get("products"));
        }
    }
    @SuppressWarnings({"unchecked"})
    @Test
    // TODO: Revisit subquery impl.
    public void canUseGroupByClauseToPartitionAListOfEachCustomersOrdersFirstByYearThenByMonth_linq43() {
        Customer[] customers = Customer.getAllCustomers();
        Iterable<Variant> customerOrderGroups =
                from("c").in(customers).
                select(
                    create(
                        property("c.getCompanyName()"),
                        property("yearGroups",
                            from("o1").in("c.getOrders()").
                            group("o1").by("o1.getOrderDate().getYear()").into("yg").
                            select(
                                create(
                                    property("year", "yg.getKey()"),
                                    property("monthGroups",
                                        from("o2").in("yg.getGroup()").
                                            orderBy("o2.getOrderDate().getMonth()").
                                            group("o2").by("o2.getOrderDate().getMonth()").into("mg").
                                            select(
                                            create(
                                                property("monthKey", "mg.getKey()"),
                                                property("orders", "mg.getGroup()")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                );

        for (Variant group : customerOrderGroups) {
            System.out.println("Company: "+group.get("companyName"));
            for (Variant yearGroup: (List<Variant>) group.get("yearGroups")) {
                System.out.println("  Year: "+yearGroup.get("year"));
                for (Variant monthGroup: (List<Variant>) yearGroup.get("monthGroups")) {
                    System.out.println("    Month: "+monthGroup.get("monthKey"));
                    for (Order order: (List<Order>) monthGroup.get("orders")) {
                        System.out.printf("      %s\n",order!=null?order.toString():"null");

                    }
                }
            }
        }
    }
    @Test
    public void canUseGroupByToPartitionTrimmedElementsOfAnArrayUsingACustomComparatorThatMatchesTheWordsThatAreAnagramsOfEachOther_linq44() {
        String[] anagrams = {"from   ", " salt", " earn ", "  last   ", " near ", " form  "};
        Iterable<Group> orderGroups =
                from("a").in(anagrams).
                        group("a").by("a.trim()", new AnagramEqualityComparator()).into("g").select("g");
        HashMap<Object, Iterable> expectedOrderGroups = new HashMap<Object, Iterable>();
        expectedOrderGroups.put("from", Arrays.asList("from   ", " form  "));
        expectedOrderGroups.put("salt", Arrays.asList(" salt", "  last   "));
        expectedOrderGroups.put("earn", Arrays.asList(" earn ", " near "));
        for (Group g : orderGroups) {
            Assert.assertEquals(expectedOrderGroups.get(g.getKey()), g.getGroup());
        }
    }
    private class AnagramEqualityComparator implements Comparator<String> {
        public int compare(java.lang.String stringA, java.lang.String stringB) {
            return getCanonicalString(stringA).equals(getCanonicalString(stringB)) ? 0 : -1;
        }

        private String getCanonicalString(java.lang.String word) {
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            return new String(wordChars);
        }
    }
}
