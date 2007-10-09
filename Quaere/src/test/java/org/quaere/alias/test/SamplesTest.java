package org.quaere.alias.test;

import static org.quaere.alias.CompareType.*;
import static org.quaere.alias.ListProvider.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Implementation of the 101 LINQ Samples as described in
 * http://msdn2.microsoft.com/en-us/vcsharp/aa336760.aspx
 */
public class SamplesTest {
    
    @Test
    public void testWhereSimple1() {
//        public void Linq1() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            var lowNums =
//                from n in numbers
//                where n < 5
//                select n;
//            Console.WriteLine("Numbers < 5:");
//            foreach (var x in lowNums) {
//                Console.WriteLine(x);
//            }
//        }
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        Integer n = alias(numbers);
        List<Integer> lowNums = from(n)
            .where(test(n, SMALLER, 5))
            .select();
        String result = "";
        for (int x : lowNums) {
            result += x + ";";
        }
        Assert.assertEquals(result, "4;1;3;2;0;");
    }
    
    @Test    
    public void testWhereSimple2() {
//        public void Linq2() {
//            List products = GetProductList();
//            var soldOutProducts =
//                from p in products
//                where p.UnitsInStock == 0
//                select p;
//            Console.WriteLine("Sold out products:");
//            foreach (var product in soldOutProducts) {
//                Console.WriteLine("{0} is sold out!", product.ProductName);
//            }
//        }        
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class, products);
        List<Product> soldOutProducts = from(p)
            .where(equal(p.unitsInStock, 0))
            .select();
        String result = "";
        for (Product x : soldOutProducts) {
            result += x.productName + ";";
        }
        Assert.assertEquals(result, "Chef Anton's Gumbo Mix;Alice Mutton;Thüringer Rostbratwurst;Gorgonzola Telino;Perth Pasties;");
    }
    
    @Test    
    public void testWhereSimple3() {
//        public void Linq3() {
//            List products = GetProductList();
//            var expensiveInStockProducts =
//                from p in products
//                where p.UnitsInStock > 0 && p.UnitPrice > 3.00M
//                select p;
//            Console.WriteLine("In-stock products that cost more than 3.00:");
//            foreach (var product in expensiveInStockProducts) {
//                Console.WriteLine("{0} is in stock and costs more than 3.00.", product.ProductName);
//            }
//        }
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class, products);
        List<Product> expensiveInStockProducts = from(p)
            .where(test(p.unitsInStock, BIGGER, 0).and(p.unitPrice, BIGGER, 3.0))
            .select();
        String result = "";
        for (Product x : expensiveInStockProducts) {
            result += x.productName + ";";
        }
        Assert.assertEquals(
                        result,
                        "Chai;Chang;Aniseed Syrup;Chef Anton's Cajun Seasoning;Grandma's Boysenberry Spread;"
                                + "Uncle Bob's Organic Dried Pears;Northwoods Cranberry Sauce;Mishi Kobe Niku;Ikura;"
                                + "Queso Cabrales;Queso Manchego La Pastora;Konbu;Tofu;Genen Shouyu;Pavlova;"
                                + "Carnarvon Tigers;Teatime Chocolate Biscuits;Sir Rodney's Marmalade;Sir Rodney's Scones;"
                                + "Gustaf's Knäckebröd;Tunnbröd;Guaraná Fantástica;NuNuCa Nuß-Nougat-Creme;Gumbär Gummibärchen;"
                                + "Schoggi Schokolade;Rössle Sauerkraut;Nord-Ost Matjeshering;Mascarpone Fabioli;Sasquatch Ale;"
                                + "Steeleye Stout;Inlagd Sill;Gravad lax;Côte de Blaye;"
                                + "Chartreuse verte;Boston Crab Meat;Jack's New England Clam Chowder;"
                                + "Singaporean Hokkien Fried Mee;Ipoh Coffee;Gula Malacca;Rogede sild;"
                                + "Spegesild;Zaanse koeken;Chocolade;Maxilaku;Valkoinen suklaa;"
                                + "Manjimup Dried Apples;Filo Mix;Tourtière;Pâté chinois;"
                                + "Gnocchi di nonna Alice;Ravioli Angelo;Escargots de Bourgogne;"
                                + "Raclette Courdavault;Camembert Pierrot;Sirop d'érable;"
                                + "Tarte au sucre;Vegie-spread;Wimmers gute Semmelknödel;"
                                + "Louisiana Fiery Hot Pepper Sauce;Louisiana Hot Spiced Okra;"
                                + "Laughing Lumberjack Lager;Scottish Longbreads;Gudbrandsdalsost;"
                                + "Outback Lager;Flotemysost;Mozzarella di Giovanni;Röd Kaviar;"
                                + "Longlife Tofu;Rhönbräu Klosterbier;Lakkalikööri;"
                                + "Original Frankfurter grüne Soße;");        
    }
    
    // GetCustomerList code is missing
//    public void Linq4() {
//        List customers = GetCustomerList();
//        var waCustomers =
//            from c in customers
//            where c.Region == "WA"
//            select c;
//        Console.WriteLine("Customers from Washington and their orders:");
//        foreach (var customer in waCustomers) {
//            Console.WriteLine("Customer {0}: {1}", customer.CustomerID, customer.CompanyName);
//            foreach (var order in customer.Orders) {
//                Console.WriteLine(" Order {0}: {1}", order.OrderID, order.OrderDate);
//            }
//        }
//    }
    
    @Test    
    public void testWhereSimple5() {
//        public void Linq5() {
//            string[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//            var shortDigits = digits.Where((digit, index) => digit.Length < index);
//            Console.WriteLine("Short digits:");
//            foreach (var d in shortDigits) {
//                Console.WriteLine("The word {0} is shorter than its value.", d);
//            }
//        }        
        String[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String digit = alias(digits);
        List<String> shortDigits = from(digit)
            .where(test(length(digit), SMALLER, index()))
            .select();
        String result = "";
        for (String x : shortDigits) {
            result +=  x + ";";
        }
        Assert.assertEquals(result, "five;six;seven;eight;nine;");
    }
    
    @Test
    public void testSelectSimple1() {
//        public void Linq6() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            var numsPlusOne =
//                from n in numbers
//                select n + 1;
//            Console.WriteLine("Numbers + 1:");
//            foreach (var i in numsPlusOne) {
//                Console.WriteLine(i);
//            }
//        }        
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        Integer n = alias(numbers);
        List<Integer> lowNums = from(n)
            .select(add(n, 1));
        String result = "";
        for (int x : lowNums) {
            result += x + ";";
        }
        Assert.assertEquals(result, "6;5;2;4;10;9;7;8;3;1;");
    }
    
    @Test
    public void testSelectSimple2() {
//    public void Linq7() {
//        List products = GetProductList();
//        var productNames =
//            from p in products
//            select p.ProductName;
//        Console.WriteLine("Product Names:");
//        foreach (var productName in productNames) {
//            Console.WriteLine(productName);
//        }
//    }
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class, products);
        List<String> productNames = from(p)
            .select(p.productName);
        for (int i = 0; i < products.size(); i++) {
            Assert.assertEquals(products.get(i).productName, productNames.get(i));
        }
    }        
    
    @Test
    public void testSelectTransformation() {
//        public void Linq8() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            string[] strings = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//            var textNums =
//                from n in numbers
//                select strings[n];
//            Console.WriteLine("Number strings:");
//            foreach (var s in textNums) {
//                Console.WriteLine(s);
//            }        
//        }
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        String[] strings = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        Integer n = alias(numbers);
        List<String> textNums = from(n)
            .select(get(strings, n));
        String result = "";
        for (String x : textNums) {
            result += x + ";";
        }        
        Assert.assertEquals(result, "five;four;one;three;nine;eight;six;seven;two;zero;");
    }
    
    public static class UpperLower {
        public String upper, lower;
    }

    @Test
    public void testAnonymousTypes1() {
//        public void Linq9() {
//            string[] words = { "aPPLE", "BlUeBeRrY", "cHeRry" };
//            var upperLowerWords =
//                from w in words
//                select new {Upper = w.ToUpper(), Lower = w.ToLower()};
//            foreach (var ul in upperLowerWords) {
//                Console.WriteLine("Uppercase: {0}, Lowercase: {1}", ul.Upper, ul.Lower);
//            }
//        }
        String[] words = { "aPPLE", "BlUeBeRrY", "cHeRry" };
        String w = alias(words);
        UpperLower x = template(UpperLower.class);
        List<UpperLower> upperLowerWords = from(w)
            .select(x, set(x.upper, upper(w)), set(x.lower, lower(w)));
        String resultUpper = "", resultLower = "";
        for (UpperLower ul : upperLowerWords) {
            resultUpper += ul.upper + ";";
            resultLower += ul.lower + ";";
        }
        Assert.assertEquals(resultUpper, "APPLE;BLUEBERRY;CHERRY;");
        Assert.assertEquals(resultLower, "apple;blueberry;cherry;");
    }
    
    
    public static class Digit {
        public String digit;
        public Boolean even;
    }
    
    @Test
    public void testAnonymousTypes2() {
//        public void Linq10() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            string[] strings = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//            var digitOddEvens =
//                from n in numbers
//                select new {Digit = strings[n], Even = (n % 2 == 0)};
//            foreach (var d in digitOddEvens) {
//                Console.WriteLine("The digit {0} is {1}.", d.Digit, d.Even ? "even" : "odd");
//            }
//        }        
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        String[] strings = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        Integer n = alias(numbers);
        Digit x = template(Digit.class);
        List<Digit> digitOddEvens = from(n)
            .select(x, set(x.digit, get(strings, n)), set(x.even, equal(mod(n, 2), 0)));
        String result = "";
        for (Digit d : digitOddEvens) {
            result += d.digit + ":" + (d.even ? "even" : "odd") + ";";
        }
        Assert.assertEquals("five:odd;four:even;one:odd;three:odd;nine:odd;eight:even;six:even;seven:odd;two:even;zero:even;", result);
    }
    
    public static class ProductPrice {
        public String productName;
        public String category;
        public Double price;
    }
    
    @Test
    public void testAnonymousTypes3() {
//    public void Linq11() {
//        List products = GetProductList();
//        var productInfos =
//            from p in products
//            select new {p.ProductName, p.Category, Price = p.UnitPrice};
//        Console.WriteLine("Product Info:");
//        foreach (var productInfo in productInfos) {
//            Console.WriteLine("{0} is in the category {1} and costs {2} per unit.", productInfo.ProductName, productInfo.Category, productInfo.Price);
//        }
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class, products);
        ProductPrice x = template(ProductPrice.class);
        List<ProductPrice> productInfos = from(p)
            .select(x, 
                    set(x.productName, p.productName), 
                    set(x.category, p.category), 
                    set(x.price, p.unitPrice));
        for (int i = 0; i < productInfos.size(); i++) {
            Assert.assertEquals(products.get(i).productName, productInfos.get(i).productName);
            Assert.assertEquals(products.get(i).category, productInfos.get(i).category);
            Assert.assertEquals(products.get(i).unitPrice, productInfos.get(i).price);
        }
    }
    
    public static class NumberInplace {
        public Integer num;
        public Boolean inPlace;
    }
    
    @Test
    public void testSelectIndexes() {
//        public void Linq12() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            var numsInPlace = numbers.Select((num, index) => new {Num = num, InPlace = (num == index)});
//            Console.WriteLine("Number: In-place?");
//            foreach (var n in numsInPlace) {
//                Console.WriteLine("{0}: {1}", n.Num, n.InPlace);
//            }
//        }
        // TODO warn about non-public fields, and null / unknown objects passed to set
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        Integer i = alias(numbers);
        NumberInplace x = template(NumberInplace.class);
        List<NumberInplace> numsInPlace = from(i).select(x, set(x.num, i), set(x.inPlace, equal(i, index())));
        String result = "";
        for (NumberInplace n : numsInPlace) {
            result += n.num + ":" + n.inPlace + ";";
        }
        Assert.assertEquals("5:false;4:false;1:false;3:true;9:false;8:false;6:true;7:true;2:false;0:false;", result);
    }
    
    @Test
    public void testSelectFiltered() {
//        public void Linq13() {
//            int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//            string[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//            var lowNums =
//                from n in numbers
//                where n < 5
//                select digits[n];
//            Console.WriteLine("Numbers < 5:");
//            foreach (var num in lowNums) {
//                Console.WriteLine(num);
//            }    
//        }
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
        String[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        Integer n = alias(numbers);
        List<String> lowNums = from(n).where(test(n, SMALLER, 5)).select(get(digits, n));
        String result = "";
        for (String x : lowNums) {
            result += x + ";";
        }
        Assert.assertEquals("four;one;three;two;zero;", result);
        
    }
    
}
