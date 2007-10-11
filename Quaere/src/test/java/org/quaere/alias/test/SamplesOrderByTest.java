package org.quaere.alias.test;

import static org.quaere.alias.ListProvider.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class SamplesOrderByTest {

    @Test
    public void testOrderBySimple1() {
//    public void Linq28() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        var sortedWords =
//            from w in words
//            orderby w
//            select w;
//        Console.WriteLine("The sorted list of words:");
//        foreach (var w in sortedWords) {
//            Console.WriteLine(w);
//        }
//    }
        String[] words = { "cherry", "apple", "blueberry" };
        String w = alias(words);
        List<String> sortedWords = from(w)
            .orderBy(w)
            .select();
        String result = "";
        for (String x : sortedWords) {
            result += x + ";";
        }
        Assert.assertEquals("apple;blueberry;cherry;", result);
    }
    
    @Test
    public void testOrderBySimple2() {    
//    public void Linq29() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        var sortedWords =
//            from w in words
//            orderby w.Length
//            select w;
//        Console.WriteLine("The sorted list of words (by length):");
//        foreach (var w in sortedWords) {
//            Console.WriteLine(w);
//        }
        String[] words = { "cherry", "apple", "blueberry" };
        String w = alias(words);
        List<String> sortedWords = from(w).orderBy(length(w)).select();
        String result = "";
        for (String x : sortedWords) {
            result += x + ";";
        }
        Assert.assertEquals(result, "apple;cherry;blueberry;");
    }
    
    @Test
    public void testOrderBySimple3() {
//        public void Linq30() {
//            List products = GetProductList();
//            var sortedProducts =
//                from p in products
//                orderby p.ProductName
//                select p;
//            ObjectDumper.Write(sortedProducts);
//        }        
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class, products);
        List<Product> sortedProducts = from(p).orderBy(p.productName).select();
        String last = "";
        for (Product x : sortedProducts) {
            Assert.assertTrue(x.productName.compareTo(last) >= 0);
            last = x.productName;
        }
    }
    
    @Test
    public void testOrderByComparer() {
//        public class CaseInsensitiveComparer : IComparer<string> {
//            public int Compare(string x, string y) {
//                return string.Compare(x, y, true);
//            }
//        }
//        public void Linq31() {
//            string[] words = { "aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
//            var sortedWords = words.OrderBy(a => a, new CaseInsensitiveComparer());
//            ObjectDumper.Write(sortedWords);
//        }
        
    }
    
    @Test
    public void testOrderByDescendingSimple1() {
//        public void Linq32() {
//            double[] doubles = { 1.7, 2.3, 1.9, 4.1, 2.9 };
//            var sortedDoubles =
//                from d in doubles
//                orderby d descending
//                select d;
//            Console.WriteLine("The doubles from highest to lowest:");
//            foreach (var d in sortedDoubles) {
//                Console.WriteLine(d);
//            }
//        }
    }

    @Test
    public void testOrderByDescendingSimple2() {
//    public void Linq33() {
//        List products = GetProductList();
//        var sortedProducts =
//            from p in products
//            orderby p.UnitsInStock descending
//            select p;
//        ObjectDumper.Write(sortedProducts);
//    }
    }
    
    @Test
    public void testOrderByDescendingComparer() {
//    public class CaseInsensitiveComparer : IComparerspan class="qs-keyword">string> {
//        publicint Compare(string x, string y) {
//            returnstring.Compare(x, y, true);
//        }
//    }
//    public void Linq34() {
//        string[] words = { "aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
//        
//        var sortedWords = words.OrderByDescending(a => a, new CaseInsensitiveComparer());
//            
//        ObjectDumper.Write(sortedWords);
//    }
    }
    
    @Test
    public void testThenBySimple() {
//    public void Linq35() {
//        string[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//        var sortedDigits =
//            from d in digits 
//            orderby d.Length, d
//            select d;
//        Console.WriteLine("Sorted digits:");
//        foreach (var d in sortedDigits) {
//            Console.WriteLine(d);
//        }
//    }
    }
    
    
    @Test
    public void testThenByComparer() {
//        public class CaseInsensitiveComparer : IComparerspan class="qs-keyword">string> {
//            publicint Compare(string x, string y) {
//                returnstring.Compare(x, y, true);
//            }
//        }
//        publicvoid Linq36() {
//            string[] words = { "aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
//            var sortedWords =
//                words.OrderBy(a => a.Length)
//                        .ThenBy(a => a, new CaseInsensitiveComparer());
//            ObjectDumper.Write(sortedWords);
//        }
    }
    
    @Test
    public void testThenByDescendingSimple() {
//    public void Linq37() {
//        List products = GetProductList();
//        var sortedProducts =
//            from p in products
//            orderby p.Category, p.UnitPrice descending select p;
//
//        ObjectDumper.Write(sortedProducts);
//    }
    }
    
    @Test
    public void testThenByDescendingComprarer() {
//        public class CaseInsensitiveComparer : IComparerspan class="qs-keyword">string> {
//            publicint Compare(string x, string y) {
//                returnstring.Compare(x, y, true);
//            }
//        }
//        public void Linq38() {
//            string[] words = { "aPPLE", "AbAcUs", "bRaNcH", "BlUeBeRrY", "ClOvEr", "cHeRry"};
//            var sortedWords =
//                words.OrderBy(a => a.Length)
//                        .ThenByDescending(a => a, new CaseInsensitiveComparer());
//            ObjectDumper.Write(sortedWords);
//        }        
    }
    
    @Test
    public void testReverse() {
//        public void Linq39() {
//            string[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
//            var reversedIDigits = (
//                from d in digits
//                where d[1] == 'i'
//                select d)
//                .Reverse();
//            Console.WriteLine("A backwards list of the digits with a second character of 'i':");
//            foreach (var d in reversedIDigits) {
//                Console.WriteLine(d);
//            }             
//        }        
    }
}
