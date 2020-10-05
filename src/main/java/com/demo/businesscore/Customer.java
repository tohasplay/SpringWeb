package com.demo.businesscore;

import lombok.Data;

public @Data
class Customer {
    private long id;
    private String name;
    private String mail;
    private String phone;
    private String password;

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
