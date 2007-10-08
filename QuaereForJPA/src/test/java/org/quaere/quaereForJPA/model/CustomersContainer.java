package org.quaere.model;

import java.util.ArrayList;
import java.util.List;

public class CustomersContainer {
    private List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}