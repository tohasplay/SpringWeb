package com.demo.service.customers;

import com.demo.exception.NotFoundException;
import com.demo.service.orders.Order;
import com.demo.service.orders.OrdersHandler;
import com.demo.utils.Node;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomersHandler implements CustomerInstance {


    private static long counter = 1;
    private static final @Getter
    ArrayList<Customer> customers = new ArrayList<>() {{
        add(new Customer(counter++, "Patrik", "ptrk@email.com", "0500450"));
        add(new Customer(counter++, "Adam", "adm@email.com", "0500450"));
        add(new Customer(counter++, "Rubin", "rbn@email.com", "0500450"));
    }};

    public static boolean containsId(long id) {
        return customers.stream().anyMatch(customer -> customer.getId() == id);
    }

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
        if (customers.contains(customer)) {
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
        Node<Float, Integer> result = new Node<>(0f, 0);
        for (Order o :
                OrdersHandler.getOrders()) {
            if (o.getCustomer_id() == id) {
                result.first += o.getPrice();
                result.second++;
            }
        }
        return result;
    }

    @Override
    public void deleteCustomer(long id) {
        Customer delete;
        if (customers.stream().anyMatch(customer -> customer.getId() == id)) {
            delete = customers.stream().filter(customer -> customer.getId() == id).findFirst().get();
            customers.remove(delete);
        }
    }
}
