package org.quaere.alias.test;

public class SamplesQuantifiers {
    // Any - Simple
//    public void Linq67() {
//        string[] words = { "believe", "relief", "receipt", "field" };
//        bool iAfterE = words.Any(w => w.Contains("ei"));
//        Console.WriteLine("There is a word that contains in the list that contains 'ei': {0}", iAfterE);
//     }
    // true
    
    // Any - Indexed
//    public void Linq68() {
//        int[] numbers = { -9, -4, -8, -3, -5, -2, -1, -6, -7 };
//        bool negativeMatch = numbers.Any((n, index) => n == -index);
//        Console.WriteLine("There is a number that is the negative of its index: {0}", negativeMatch);
//     }
    // true
    
    // Any - Grouped
//    public void Linq69() {
//        List products = GetProductList();
//        var productGroups =
//           from p in products
//           group p by p.Category into g
//           where g.Group.Any(p => p.UnitsInStock == 0)
//           select new {Category = g.Key, Products = g.Group};
//           ObjectDumper.Write(productGroups, 1);
//     }    
    
    // All - Simple
//    public void Linq70() {
//        int[] numbers = { 1, 11, 3, 19, 41, 65, 19 };
//        bool onlyOdd = numbers.All(n => n % 2 == 1);
//        Console.WriteLine("The list contains only odd numbers: {0}", onlyOdd);
//     }
    // true
    
    // All - Indexed
//    public void Linq71() {
//        int[] lowNumbers = { 1, 11, 3, 19, 41, 65, 19 };
//        int[] highNumbers = { 7, 19, 42, 22, 45, 79, 24 };
//        bool allLower = lowNumbers.All((num, index) => num < highNumbers[index]);
//        Console.WriteLine("Each number in the first list is lower than its counterpart in the second list: {0}", allLower);
//     }   
    // true
    
    // All - Grouped
//    public void Linq72() {
//        List products = GetProductList();
//        var productGroups =
//           from p in products
//           group p by p.Category into g
//           where g.Group.All(p => p.UnitsInStock > 0)
//           select new {Category = g.Key, Products = g.Group};
//        ObjectDumper.Write(productGroups, 1);
//     }

}
