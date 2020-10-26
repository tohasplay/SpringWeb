package com.demo.service.data;

import com.demo.dto.Order;
import com.demo.exception.NotFoundException;
import com.demo.service.data.repository.CustomerRepository;
import com.demo.service.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class OrderDataService implements OrderDataBaseAccess {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderDataService(OrderRepository orderRepository,
                            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order put(Order data, long id) {
        orderRepository.save(data.getText(), data.getPrice(), id);
        return orderRepository.maxId();
    }

    @Override
    public void delete(long id) {
       orderRepository.deleteById(id);
    }

    @Override
    public void update(Order data) {
        orderRepository.update(data.getText(), data.getPrice(), data.getId());
    }

    @Override
    public Order getById(long id) {
        Order order = orderRepository.getByIdEquals(id);
        if (order == null) throw new NotFoundException();
        return order;
    }

    @Override
    public boolean verifyUser(long id, String password) {
        return customerRepository.existsCustomerByIdAndPassword(id, password);
    }

}

