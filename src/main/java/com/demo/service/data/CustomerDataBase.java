package com.demo.service.data;

import com.demo.businesscore.customer.Customer;
import com.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerDataBase implements DataBaseAccess<Customer>{

    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    public ArrayList<Customer> getAllData() {
        return customers;
    }

    @Override
    public Customer findById(long id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public void putObject(Customer data) {
         customers.add(data);
    }

    @Override
    public void deleteObject(Customer data) {
        customers.stream().filter(customer -> customer.getMail().toLowerCase().equals(
                data.getMail().toLowerCase()
        )).findFirst().ifPresent(customers::remove);
    }

    @Override
    public void updateObject(Customer data) {
        customers.stream().filter(customer -> customer.getMail().toLowerCase().equals(
                data.getMail().toLowerCase()
        )).findFirst().ifPresent(customer -> {
            customer.setMail(data.getMail());
            customer.setName(data.getName());
            customer.setPassword(data.getPassword());
            customer.setPhone(data.getPhone());
        });
    }

    @Override
    public boolean contains(Customer data) {
        return customers.stream().anyMatch(customer -> customer.getMail().toLowerCase().equals(
                data.getMail().toLowerCase()
        ));
    }
}
