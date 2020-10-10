package com.demo.interactor;

import com.demo.dto.Customer;
import com.demo.exception.AlreadyRegisteredException;
import com.demo.service.data.DataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * integrated with {@link com.demo.service.presentor.CustomerPresentor}
 */
@Deprecated
@Component
public class CustomerRegistrationUnit {

    private final DataBaseAccess<Customer> dataBaseAccess;

    CustomerRegistrationUnit(@Autowired DataBaseAccess<Customer> dataBaseAccess) {
        this.dataBaseAccess = dataBaseAccess;
    }

    public Customer registerNewCustomer(String name, String mail, String phone, String password) {
        Customer register = new Customer(0, name, mail, phone, password);
        if (!dataBaseAccess.contains(register)) {
            return dataBaseAccess.put(register);
        } else {
            throw new AlreadyRegisteredException("User already registered");
        }
    }
}
