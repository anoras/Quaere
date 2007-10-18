package org.quaere.alias.test;

import java.util.Arrays;
import java.util.List;

public class Customer {
    
    public String customerId;
    public String region;
    public List<Order> orders;
    
    public static List<Customer> getCustomerList() {
        Customer[] list = new Customer[] {
                create("ALFKI", "WA", new Order[] { 
                        createOrder(10702, "330.00", "2007-01-02"),
                        createOrder(10952, "471.20", "2007-02-03") }),
                create("ANATR", "WA", new Order[] { 
                        createOrder(10308, "88.80", "2007-01-03"),
                        createOrder(10625, "479.75", "2007-03-03"), 
                        createOrder(10759, "320.00", "2007-04-01") }),
                create("ANTON", "CA", new Order[] { 
                        createOrder(10365, "403.20", "2007-02-13"),
                        createOrder(10682, "375.50", "2007-03-13"), 
                        createOrder(10355, "480.00", "2007-04-11") }) };
        return Arrays.asList(list);
    }
    
    private static Customer create(String customerId, String region, Order[] orders) {
        return new Customer(customerId, region, orders);
    }
    
    private static Order createOrder(Integer orderId, String total, String orderDate) {
        return new Order(orderId, total, orderDate);
    }
    
    public Customer() {
    }
    
    public Customer(String customerId, String region, Order[] orders) {
        this.customerId = customerId;
        this.region = region;
        this.orders = Arrays.asList(orders);
    }
    
}
