package com.demo.service.customers;

import com.demo.exception.NotFoundException;
import com.demo.service.orders.Order;
import com.demo.service.orders.OrderInstance;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class CustomersHandler implements CustomerInstance{

    @Autowired
    private OrderInstance orders;
    private static long counter = 1;
    private final ArrayList<Customer> customers = new ArrayList<>(){{
        add(new Customer(counter++, "Patrik", "ptrk@email.com", "0500450"));
        add(new Customer(counter++, "Adam","adm@email.com", "0500450"));
        add(new Customer(counter++, "Rubin","rbn@email.com", "0500450"));
    }};

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        return getCustomer(id);
    }

    private Customer getCustomer(long id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customers.contains(customer)){
            return customer;
        }
        customer.setId(counter++);
        customers.add(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        return null;
    }

    @Override
    public Node<Float, Integer> getChek(long id) {
        Node<Float, Integer> result = new Node<>(0f,0);
        for (Order o :
                orders.getAllOrders()) {
            if (o.getCustomer_id() == id){
                result.first += o.getPrice();
                result.second++;
            }
        }
        return result;
    }

    @Override
    public void deleteCustomer() {

    }
}
