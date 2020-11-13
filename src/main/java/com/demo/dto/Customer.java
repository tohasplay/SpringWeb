package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private long id;
    private String name;
    private String mail;
    private String phone;
    private String password;


//    @Override
//    public boolean equals(Object obj) {
//        if (getClass() != obj.getClass())
//            return false;
//        return this.getMail().toLowerCase().equals(((Customer) obj).getMail().toLowerCase());
//    }

    //a.equals(b) ^ !b.equals(a) extend

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (!name.equals(customer.name)) return false;
        if (!mail.equals(customer.mail)) return false;
        if (!phone.equals(customer.phone)) return false;
        return password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
