package com.demo.service.presentor;

import com.demo.businesscore.Customer;
import com.demo.interacor.CustomerManager;
import com.demo.interacor.CustomerRegistrationUnit;
import com.demo.exception.NotFoundException;
import com.demo.service.data.DataBaseAccess;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerPresentor implements CustomerUndefinedPresentor<Customer> {

    private final CustomerRegistrationUnit registrationUnit;
    private final DataBaseAccess<Customer> dataBaseAccess;
    private final CustomerManager customerManager;

    private CustomerPresentor(@Autowired CustomerRegistrationUnit registrationUnit,
                              @Autowired DataBaseAccess<Customer> dataBaseAccess,
                              @Autowired CustomerManager customerManager) {
        this.registrationUnit = registrationUnit;
        this.dataBaseAccess = dataBaseAccess;
        this.customerManager = customerManager;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) dataBaseAccess.getAllData();
    }

    @Override
    public Customer add(Customer data) {
        return registrationUnit.registerNewCustomer(
                data.getName(), data.getMail(), data.getPhone(), data.getPassword()
        );
    }

    @Override
    public Customer get(long id) {
        return dataBaseAccess.getAllData().stream().filter(customer -> customer.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(long id) {
        dataBaseAccess.deleteObject(
                dataBaseAccess.getAllData().stream().filter(customer -> customer.getId() == id).findFirst()
                        .orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public Customer update(long id, Customer data) {
        dataBaseAccess.updateObject(id, data);
        return data;
    }

    @Override
    public Node<Float, Integer> getChek(long id) {
        return customerManager.calculateTotal(id);
    }
}
