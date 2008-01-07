package org.quaere.jpa.model;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String companyName;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private List<Order> orders = new ArrayList<Order>();

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return String.format("Customer (%s): %s, %s, %s, %s, %s, %s, %s, %s, %s, Orders=%s", hashCode(), customerID, companyName, address, city, region, postalCode, country, phone, fax, orders != null ? orders.size() : 0);
    }

    public static Customer[] getAllCustomers() {
        Mapping mapping = new Mapping();
        try {
            mapping.loadMapping(
                    new InputSource(Thread.currentThread().getContextClassLoader().getResource("mappings.xml").getFile()));
            Unmarshaller unmarshaller = new Unmarshaller(mapping);
            unmarshaller.setIgnoreExtraElements(true);
            CustomersContainer customersContainer = (CustomersContainer) unmarshaller.unmarshal(
                    new InputSource(Thread.currentThread().getContextClassLoader().getResource("Customers.xml").getFile())
            );
            Customer[] customers = new Customer[customersContainer.getCustomers().size()];
            for (int i = 0; i < customersContainer.getCustomers().size(); i++) {
                customers[i] = customersContainer.getCustomers().get(i);
            }
            return customers;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerID != null ? !customerID.equals(customer.customerID) : customer.customerID != null) return false;

        return true;
    }

    public int hashCode() {
        return (customerID != null ? customerID.hashCode() : 0);
    }
}
