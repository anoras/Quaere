package org.quaere.alias.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.quaere.alias.CompareType.*;
import static org.quaere.alias.ListProvider.*;

public class SamplesSetOperators {
    @Test
    public void testDistinct1() {
//        public void Linq46() {
//            int[] factorsOf300 = { 2, 2, 3, 5, 5 };
//            var uniqueFactors = factorsOf300.Distinct();
//            Console.WriteLine("Prime factors of 300:");
//            foreach (var f in uniqueFactors) {
//                Console.WriteLine(f);
//            }
//        }        
        int[] factorsOf300 = { 2, 2, 3, 5, 5 };
        List<Integer> uniqueFactors = from(factorsOf300, F)
            .orderBy(F)
            .selectDistinct();
        Assert.assertEquals("[2, 3, 5]", uniqueFactors.toString());
    }
    
    @Test
    public void testDistinct2() {
//    public void Linq47() {
//        List products = GetProductList();
//        var categoryNames = (
//            from p in products
//            select p.Category)
//            .Distinct();
//        Console.WriteLine("Category names:");
//        foreach (var n in categoryNames) {
//            Console.WriteLine(n);
//        }
        List<Product> products = Product.getProductList();
        Product p = alias(Product.class);
        List<String> categoryNames = from(products, p)
            .orderBy(p.category)
            .selectDistinct(p.category);
        Assert.assertEquals(
                "[Beverages, Condiments, Confections, Dairy Products, Grains/Cereals, Meat/Poultry, Produce, Seafood]", 
                categoryNames.toString());
    }
    
    @Test
    public void testUnion1() {
//    public void Linq48() {
//        int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
//        int[] numbersB = { 1, 3, 5, 7, 8 };
//        var uniqueNumbers = numbersA.Union(numbersB);
//        Console.WriteLine("Unique numbers from both arrays:");
//        foreach (var n in uniqueNumbers) {
//            Console.WriteLine(n);
//        }
        int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
        int[] numbersB = { 1, 3, 5, 7, 8 };
        // TODO union
        // List<Integer> uniqueNumbers = from(numbersA, A).select()
    }
    
    @Test
    public void testUnion2() {
//        public void Linq49() {
//            List products = GetProductList();List customers = GetCustomerList();
//            var productFirstChars =
//                from p in products
//                select p.ProductName[0];
//            var customerFirstChars =
//                from c in customers
//                select c.CompanyName[0];
//            var uniqueFirstChars = productFirstChars.Union(customerFirstChars);
//            Console.WriteLine("Unique first letters from Product names and Customer names:");
//            foreach (var ch in uniqueFirstChars) {
//                Console.WriteLine(ch);
//            }
        
    }
    
    @Test
    public void testIntersect1() {
//        public void Linq50() {
//            int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
//            int[] numbersB = { 1, 3, 5, 7, 8 };
//            var commonNumbers = numbersA.Intersect(numbersB);
//            Console.WriteLine("Common numbers shared by both arrays:");
//            foreach (var n in commonNumbers) {
//                Console.WriteLine(n);
//            }
//        }        
        // TODO intersect
    }
    
    @Test
    public void testIntersect2() {
//        public void Linq51() {
//            List products = GetProductList();
//            List customers = GetCustomerList();
//            var productFirstChars =
//                from p in products
//                select p.ProductName[0];
//            var customerFirstChars =
//                from c in customers
//                select c.CompanyName[0];
//            var commonFirstChars = productFirstChars.Intersect(customerFirstChars);
//            Console.WriteLine("Common first letters from Product names and Customer names:");
//            foreach (var ch in commonFirstChars) {
//                Console.WriteLine(ch);
//            }
    }
    
    @Test
    public void testExcept1() {
//        public void Linq52() {
//            int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
//            int[] numbersB = { 1, 3, 5, 7, 8 };
//            IEnumerable<int> aOnlyNumbers = numbersA.Except(numbersB);
//            Console.WriteLine("Numbers in first array but not second array:");
//            foreach (var n in aOnlyNumbers) {
//                Console.WriteLine(n);
//            }
    }    
    
    @Test
    public void testExcept2() {
//        public void Linq53() {
//            List products = GetProductList();
//            List customers = GetCustomerList();
//            var productFirstChars =
//                from p in products
//                select p.ProductName[0];
//            var customerFirstChars =
//                from c in customers
//                select c.CompanyName[0];
//            var productOnlyFirstChars = productFirstChars.Except(customerFirstChars);
//            Console.WriteLine("First letters from Product names, but not from Customer names:");
//            foreach (var ch in productOnlyFirstChars) {
//                Console.WriteLine(ch);
//            }
        // TODO except
    }
}
