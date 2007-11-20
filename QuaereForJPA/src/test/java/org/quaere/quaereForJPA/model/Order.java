package org.quaere.quaereForJPA.model;

import java.util.Date;

public class Order {
    private int orderID;
    private Date orderDate;
    private double total;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String toString() {
        return String.format("Order (%s): OrderID=%s, OrderDate=%s, Total=%s", hashCode(), orderID, orderDate, total);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderID != order.orderID) return false;

        return true;
    }

    public int hashCode() {
        return orderID;
    }
}
