package com.framework.artisan.aop;

public class NewCustomerEvent {
    private Customer customer;
    public NewCustomerEvent(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
    }
}
