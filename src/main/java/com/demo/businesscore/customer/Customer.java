package com.demo.businesscore.customer;

import com.demo.businesscore.order.Order;
import lombok.Data;

import java.util.ArrayList;

public @Data
class Customer {
    private long id;
    private String name;
    private String mail;
    private String phone;
    private String password;
    private ArrayList<Order> orders = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(long id, String name, String mail, String phone, String password) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        return this.getMail().toLowerCase().equals(((Customer) obj).getMail().toLowerCase());
    }

}
