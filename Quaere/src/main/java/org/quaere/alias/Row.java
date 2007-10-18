package org.quaere.alias;

public class Row<T> {
    
    private final T data;
    private final Object[] orderList;
    
    Row(T data, Object[] orderList) {
        this.data = data;
        this.orderList = orderList;
    }
    
    T getData() {
        return data;
    }
    
    Object[] getOrderList() {
        return orderList;
    }
    
    public int hashCode() {
        return data.hashCode();
    }
    
    public boolean equals(Object o) {
        return data.equals(((Row) o).data);
    }

}
