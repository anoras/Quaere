package org.quaere.alias.test;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.quaere.alias.CompareType.*;
import static org.quaere.alias.ListProvider.*;

public class SamplesManyTest {
    
    public static class Pair {
        public Integer a, b;
    }
    @Test
    public void testSelectManyCompoundFrom1() {
//        public void Linq14() {
//            int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
//            int[] numbersB = { 1, 3, 5, 7, 8 };
//            var pairs =
//                from a in numbersA,
//                        b in numbersB
//                where a < b
//                select new {a, b};
//            Console.WriteLine("Pairs where a < b:");
//            foreach (var pair in pairs) {
//                Console.WriteLine("{0} is less than {1}", pair.a, pair.b);
//            }
//        }
        int[] numbersA = { 0, 2, 4, 5, 6, 8, 9 };
        int[] numbersB = { 1, 3, 5, 7, 8 };
        Pair x = template(Pair.class);
        List<Pair> pairs = from(numbersA, A)
            .join(numbersB, B)
            .where(test(A, SMALLER, B))
            .select(x, set(x.a, A), set(x.b, B));
        String result = "";
        for (Pair p : pairs) {
            result += p.a + "<" + p.b + ";";
        }
        Assert.assertEquals(result, "0<1;0<3;0<5;0<7;0<8;2<3;2<5;2<7;2<8;4<5;4<7;4<8;5<7;5<8;6<7;6<8;");
    }
    
    @Test
    public void testSelectManyCompoundFrom2() {
//        public void Linq15() {
//            List customers = GetCustomerList();
//            var orders =
//                from c in customers,
//                        o in c.Orders
//                where o.Total < 500.00M
//                select new {c.CustomerID, o.OrderID, o.Total};
//            ObjectDumper.Write(orders);
//        }        
//        CustomerID=ALFKI OrderID=10702 Total=330.00
//        CustomerID=ALFKI OrderID=10952 Total=471.20
//        CustomerID=ANATR OrderID=10308 Total=88.80
//        CustomerID=ANATR OrderID=10625 Total=479.75
//        CustomerID=ANATR OrderID=10759 Total=320.00
//        CustomerID=ANTON OrderID=10365 Total=403.20
//        CustomerID=ANTON OrderID=10682 Total=375.50
//        CustomerID=AROUT OrderID=10355 Total=480.00
//        ...
        List<Customer> customers = Customer.getCustomerList();
        Customer c = alias(Customer.class);
        Order o = alias(Order.class);
        class CustOrder {
            String customerId;
            Integer orderId;
            BigDecimal total;
        }
        CustOrder t = template(CustOrder.class);
        List<CustOrder> orders = from(customers, c)
            .join(c.orders, o)
            .where(test(o.total, SMALLER, 500.00))
            .select(t, set(t.customerId, c.customerId), set(t.orderId, o.orderId), set(t.total, o.total));
        int todoImplementListResolution;
        int todoVerifyResult;
    }
    
    @Test
    public void testSelectManyCompoundFrom3() {
//        public void Linq16() {
//            List customers = GetCustomerList();
//            var orders =
//                from c in customers,
//                        o in c.Orders
//                where o.OrderDate >= new DateTime(1998, 1, 1)
//                select new {c.CustomerID, o.OrderID, o.OrderDate};
//            ObjectDumper.Write(orders);
//        }
//        CustomerID=ALFKI OrderID=10835 OrderDate=1/15/1998
//        CustomerID=ALFKI OrderID=10952 OrderDate=3/16/1998
//        CustomerID=ALFKI OrderID=11011 OrderDate=4/9/1998
//        CustomerID=ANATR OrderID=10926 OrderDate=3/4/1998
//        CustomerID=ANTON OrderID=10856 OrderDate=1/28/1998
//        CustomerID=AROUT OrderID=10864 OrderDate=2/2/1998
//        CustomerID=AROUT OrderID=10920 OrderDate=3/3/1998
    }
    
    @Test
    public void testSelectManyFromAssignment() {
//        public void Linq17() {
//            List customers = GetCustomerList();
//            var orders =
//                from c in customers,
//                        o in c.Orders,
//                        total = o.Total
//                where total >= 2000.0M
//                select new {c.CustomerID, o.OrderID, total};
//            ObjectDumper.Write(orders);
//        }        
//        CustomerID=ANTON OrderID=10573 total=2082.00
//        CustomerID=AROUT OrderID=10558 total=2142.90
//        CustomerID=AROUT OrderID=10953 total=4441.25
//        CustomerID=BERGS OrderID=10384 total=2222.40
//        CustomerID=BERGS OrderID=10524 total=3192.65
//        CustomerID=BERGS OrderID=10672 total=3815.25
//        CustomerID=BERGS OrderID=10857 total=2048.21
//        CustomerID=BLONP OrderID=10360 total=7390.20
    }
    
    @Test
    public void testSelectManyMultipleFrom() {
//        List customers = GetCustomerList();
//        DateTime cutoffDate = new DateTime(1997, 1, 1);
//        var orders =
//            from c in customers
//            where c.Region == "WA"
//            from o in c.Orders
//            where o.OrderDate >= cutoffDate
//            select new {c.CustomerID, o.OrderID};
//        ObjectDumper.Write(orders);
//        CustomerID=LAZYK OrderID=10482
//        CustomerID=LAZYK OrderID=10545
//        CustomerID=TRAIH OrderID=10574
//        CustomerID=TRAIH OrderID=10577
//        CustomerID=TRAIH OrderID=10822
//        CustomerID=WHITC OrderID=10469
//        CustomerID=WHITC OrderID=10483
    }
    
    @Test
    public void testSelectManyIndexed() {
//        public void Linq19() {
//            List customers = GetCustomerList();
//            var customerOrders =
//                customers.SelectMany(
//                    (cust, custIndex) =>
//                    cust.Orders.Select(o => "Customer #" + (custIndex + 1) +
//                                            " has an order with OrderID " + o.OrderID) );
//            ObjectDumper.Write(customerOrders);
//        }
//        Customer #1 has an order with OrderID 10643
//        Customer #1 has an order with OrderID 10692
//        Customer #1 has an order with OrderID 10702
//        Customer #1 has an order with OrderID 10835
//        Customer #1 has an order with OrderID 10952
//        Customer #1 has an order with OrderID 11011
//        Customer #2 has an order with OrderID 10308
//        Customer #2 has an order with OrderID 10625
        
    }

}
