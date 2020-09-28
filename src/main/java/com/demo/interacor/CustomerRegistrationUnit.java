package com.demo.interacor;

import com.demo.businesscore.customer.Customer;
import com.demo.exception.AlreadyRegisteredException;
import com.demo.service.data.DataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrationUnit {

    private final DataBaseAccess<Customer> dataBaseAccess;
    private static long id = 0;

    CustomerRegistrationUnit(@Autowired DataBaseAccess<Customer> dataBaseAccess){
        this.dataBaseAccess = dataBaseAccess;
    }

    public Customer registerNewCustomer(String name, String mail, String phone, String password){
        Customer register = new Customer(id++, name, mail, phone, password);
        if (!dataBaseAccess.contains(register)){
            dataBaseAccess.putObject(register);
            return register;
        }else {
            id--;
            throw new AlreadyRegisteredException("User already registered");
        }
    }
}
