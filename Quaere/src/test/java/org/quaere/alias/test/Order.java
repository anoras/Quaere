package org.quaere.alias.test;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    public Integer orderId;
    public Date orderDate;
    public BigDecimal total;
    
    public Order(Integer orderId, String total, String orderDate) {
        this.orderId = orderId;
        this.total = new BigDecimal(total);
        this.orderDate = java.sql.Date.valueOf(orderDate);
    }
    
    public Order() {
    }
}
