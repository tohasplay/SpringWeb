package com.demo.service.presentor;

import com.demo.dto.Customer;
import com.demo.dto.Order;
import com.demo.service.data.CustomerDataBaseAccess;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerPresentorImpl implements CustomerPresentor {

    private final CustomerDataBaseAccess dataBaseAccess;

    private CustomerPresentorImpl(@Autowired CustomerDataBaseAccess dataBaseAccess) {
        this.dataBaseAccess = dataBaseAccess;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) dataBaseAccess.getAll();
    }

    @Override
    public Customer add(Customer data) {
        return dataBaseAccess.put(data);
    }

    @Override
    public Customer get(long id) {
        return dataBaseAccess.findById(id);
    }

    @Override
    public void delete(long id) {
        dataBaseAccess.delete(
                id
        );
    }

    @Override
    public Customer update(long id, Customer data) {
        dataBaseAccess.update(id, data);
        return data;
    }

    @Override
    public Node<Float, Integer> getChek(long id) {
        Node<Float, Integer> result = new Node<>(0f, 0);
        for (Order o :
                dataBaseAccess.getOrders(id)) {
            result.first += o.getPrice();
            result.second++;
        }
        return result;
    }
}
