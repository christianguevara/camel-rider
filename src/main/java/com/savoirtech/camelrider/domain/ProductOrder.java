package com.savoirtech.camelrider.domain;

import com.savoirtech.camelrider.domain.Customer;
import com.savoirtech.camelrider.domain.Item;

import java.io.Serializable;

public class ProductOrder implements Serializable{

    private Customer customer;

    private String product;

    private int quanitity;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuanitity() {
        return quanitity;
    }

    public void setQuanitity(int quanitity) {
        this.quanitity = quanitity;
    }
}
