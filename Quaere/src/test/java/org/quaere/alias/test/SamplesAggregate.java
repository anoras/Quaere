package org.quaere.alias.test;

public class SamplesAggregate {
// Count - Simple
//    public void Linq73() {
//        int[] factorsOf300 = { 2, 2, 3, 5, 5 };
//        int uniqueFactors = factorsOf300.Distinct().Count();
//        Console.WriteLine("There are {0} unique factors of 300.", uniqueFactors);
//     }
    // 3
    
    // Count - Conditional
//    public void Linq74() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        int oddNumbers = numbers.Count(n => n % 2 == 1);
//        Console.WriteLine("There are {0} odd numbers in the list.", oddNumbers);
//     }    
    // 5

    // Count - Indexed
//    public void Linq75() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        int oddEvenMatches = numbers.Count((n, index) => n % 2 == index % 2);
//        Console.WriteLine("There are {0} numbers in the list whose odd/even status " +
//             "matches that of their position.", oddEvenMatches);
//     }
    // 4
    
    // Count - Nested
//    public void Linq76() {
//        List customers = GetCustomerList();
//        var orderCounts =
//           from c in customers
//           select new {c.CustomerID, OrderCount = c.Orders.Count()};
//        ObjectDumper.Write(orderCounts);
//     }
    
    // Count - Grouped
//    public void Linq77() {
//        List products = GetProductList();
//        var categoryCounts =
//           from p in products
//           group p by p.Category into g
//           select new {Category = g.Key, ProductCount = g.Group.Count()};
//        ObjectDumper.Write(categoryCounts);
//     }
    
    // Sum - Simple
//    public void Linq78() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        double numSum = numbers.Sum();
//        Console.WriteLine("The sum of the numbers is {0}.", numSum);
//     }
    // 45
    
    // Sum - Projection
//    public void Linq79() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        double totalChars = words.Sum(w => w.Length);
//        Console.WriteLine("There are a total of {0} characters in these words.", totalChars);
//     }
    // 20
    
    // Sum - Grouped
//    public void Linq80() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           select new {Category = g.Key, TotalUnitsInStock = g.Group.Sum(p => p.UnitsInStock)};
//        ObjectDumper.Write(categories);
//     }
    
    // Min - Simple
//    public void Linq81() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        int minNum = numbers.Min();
//        Console.WriteLine("The minimum number is {0}.", minNum);
//     }
    // 0
    
    // Min - Projection
//    public void Linq82() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        int shortestWord = words.Min(w => w.Length);
//        Console.WriteLine("The shortest word is {0} characters long.", shortestWord);
//     }
    // 5
    
    // Min - Grouped
//    public void Linq83() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           select new {Category = g.Key, CheapestPrice = g.Group.Min(p => p.UnitPrice)};
//        ObjectDumper.Write(categories);
//     }
    
    // Min - Elements
//    public void Linq84() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           from minPrice = g.Group.Min(p => p.UnitPrice)
//           select new {Category = g.Key, CheapestProducts = g.Group.Where(p => p.UnitPrice == minPrice)};
//        ObjectDumper.Write(categories, 1);
//     }
    
    // Max - Simple
//    public void Linq85() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        int maxNum = numbers.Max();
//        Console.WriteLine("The maximum number is {0}.", maxNum);
//     }
    // 9
    
    // Max - Projection
//    public void Linq86() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        int longestLength = words.Max(w => w.Length);
//        Console.WriteLine("The longest word is {0} characters long.", longestLength);
//     }
    // 9
    
    // Max - Grouped
//    public void Linq87() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           select new {Category = g.Key, MostExpensivePrice = g.Group.Max(p => p.UnitPrice)};
//        ObjectDumper.Write(categories);
//     }
    
    // Max - Elements
//    public void Linq88() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           from maxPrice = g.Group.Max(p => p.UnitPrice)
//           select new {Category = g.Key, MostExpensiveProducts = g.Group.Where(p => p.UnitPrice == maxPrice)};
//        ObjectDumper.Write(categories, 1);
//     }
    
    // Average - Simple
//    public void Linq89() {
//        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
//        double averageNum = numbers.Average();
//        Console.WriteLine("The average number is {0}.", averageNum);
//     }
    // 4.5
    
    // Average - Projection
//    public void Linq90() {
//        string[] words = { "cherry", "apple", "blueberry" };
//        double averageLength = words.Average(w => w.Length);
//        Console.WriteLine("The average word length is {0} characters.", averageLength);
//     }
    // 6.66666666666667
    
    // Average - Grouped
//    public void Linq91() {
//        List products = GetProductList();
//        var categories =
//           from p in products
//           group p by p.Category into g
//           select new {Category = g.Key, AveragePrice = g.Group.Average(p => p.UnitPrice)};
//        ObjectDumper.Write(categories);
//     }
    
    // Fold - Simple
//    public void Linq92() {
//        double[] doubles = { 1.7, 2.3, 1.9, 4.1, 2.9 };
//        double product = doubles.Fold((runningProduct, nextFactor) => runningProduct * nextFactor);
//        Console.WriteLine("Total product of all numbers: {0}", product);
//     }
    // 88.33081
    
    // Fold - Seed
//    public void Linq93() {
//        double startBalance = 100.0;
//        int[] attemptedWithdrawals = { 20, 10, 40, 50, 10, 70, 30 };
//        double endBalance =
//           attemptedWithdrawals.Fold(startBalance,
//              (balance, nextWithdrawal) =>
//                 ( (nextWithdrawal <= balance) ? (balance - nextWithdrawal) : balance ) );
//        Console.WriteLine("Ending balance: {0}", endBalance);
//     }
    // 20

}
