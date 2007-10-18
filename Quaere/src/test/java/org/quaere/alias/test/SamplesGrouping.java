package org.quaere.alias.test;

import static org.quaere.alias.CompareType.*;
import static org.quaere.alias.ListProvider.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class SamplesGrouping {
    @Test
    public void testSimple1() {
//    public void Linq40() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        var numberGroups =
//            from n in numbers
//            group n by n % 5 into g
//            select new { Remainder = g.Key, Numbers = g };
//        foreach (var g in numberGroups) {
//            Console.WriteLine("Numbers with a remainder of {0} when divided by 5:", g.Remainder);
//            foreach (var n in g.Numbers) {
//                Console.WriteLine(n);
//            }
//        }
//}
        int todo;
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        class RemainderList {
//            Integer remainder;
//            List<Integer> numbers;
//        }
//        RemainderList list = template(RemainderList.class);
//        List<RemainderList> numberGroups = from(numbers, N)
//            .groupBy(mod(N, 5), G)
//            .select(list, set(list.remainder, key(G)), set(list.numbers, G));
    }
    
    @Test
    public void testSimple2() {
//        public void Linq41() {
//            string[] words = { "blueberry", "chimpanzee", "abacus", "banana", "apple", "cheese" };
//            var wordGroups =
//                from w in words
//                group w by w[0] into g
//                select new { FirstLetter = g.Key, Words = g };
//            foreach (var g in wordGroups) {
//                Console.WriteLine("Words that start with the letter '{0}':", g.FirstLetter);
//                foreach (var w in g.Words) {
//                    Console.WriteLine(w);
//                }
//            }
//        }
    }
    
    @Test
    public void testSimple3() {
//        public void Linq42() {
//            List<Product> products = GetProductList();
//            var orderGroups =
//                from p in products
//                group p by p.Category into g
//                select new { Category = g.Key, Products = g };
//            ObjectDumper.Write(orderGroups, 1);
//        }        
    }
    
    @Test
    public void testNested() {
//        public void Linq43() {
//            List<Customer> customers = GetCustomerList();
//            var customerOrderGroups =
//                from c in customers
//                select
//                    new {c.CompanyName,
//                         YearGroups =
//                             from o in c.Orders
//                             group o by o.OrderDate.Year into yg
//                             select
//                                 new {Year = yg.Key,
//                                      MonthGroups =
//                                          from o in yg
//                                          group o by o.OrderDate.Month into mg
//                                          select new { Month = mg.Key, Orders = mg }
//                                     }
//                        };
//            ObjectDumper.Write(customerOrderGroups, 3);
//        }
        
    }    
    
    @Test
    public void testComparer() {
//        public class AnagramEqualityComparer : IEqualityComparerspan class="qs-keyword">string> { 
//            public bool Equals(string x, string y) { 
//                return getCanonicalString(x) == getCanonicalString(y); 
//            } 
//            publici nt GetHashCode(string obj) { 
//                return getCanonicalString(obj).GetHashCode(); 
//            } 
//            private string getCanonicalString(string word) { 
//                char[] wordChars = word.ToCharArray(); 
//                Array.Sortspan class="qs-keyword">char>(wordChars); 
//                return new string(wordChars); 
//            } 
//        } 
//        public void Linq44() { 
//            string[] anagrams = {"from ", " salt", " earn ", " last ", " near ", " form "}; 
//            var orderGroups = anagrams
//                .GroupBy(w => w.Trim(), new AnagramEqualityComparer()); 
//            ObjectDumper.Write(orderGroups, 1); }
    }    
}
