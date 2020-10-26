package com.demo.service.data;

import com.demo.dto.Customer;
import com.demo.dto.Order;
import com.demo.exception.NotFoundException;
import com.demo.service.data.repository.CustomerRepository;
import com.demo.service.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

//DONE: jdbc right way + try with resources
//DONE: rewrite spring data (repository)
@Service
@Transactional
public class CustomerDataService implements CustomerDataBaseAccess {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerDataService(CustomerRepository customerRepository,
                               OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer findById(long id) {
        AtomicReference<Customer> customer = new AtomicReference<>();
        customerRepository.findById(id).ifPresent(customer::set);
        if (customer.get() == null) throw new NotFoundException();
        return customer.get();
    }

    @Override
    public Customer put(Customer data) {
        customerRepository.save(data);
        return customerRepository.maxId();
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void update(long id, Customer data) {
        customerRepository.update(id, data.getName(), data.getMail(), data.getPhone(), data.getPassword());
    }

    @Override
    public ArrayList<Order> getOrders(long id) {
        return orderRepository.getOrders(id);
    }
}
