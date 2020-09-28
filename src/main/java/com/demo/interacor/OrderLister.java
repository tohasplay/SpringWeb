package com.demo.interacor;

import com.demo.businesscore.customer.Customer;
import com.demo.businesscore.order.Order;
import com.demo.service.data.DataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderLister {
    private final DataBaseAccess<Customer> dataBaseAccess;

    private OrderLister(@Autowired DataBaseAccess<Customer> dataBaseAccess){
        this.dataBaseAccess = dataBaseAccess;
    }

    public ArrayList<Order> listAllOrders(){
        ArrayList<Order> list = new ArrayList<>();
        for (Customer c :
                dataBaseAccess.getAllData()) {
            list.addAll(c.getOrders());
        }
        return list;
    }
}
