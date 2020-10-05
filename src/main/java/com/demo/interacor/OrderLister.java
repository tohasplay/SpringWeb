package com.demo.interacor;

import com.demo.businesscore.Order;
import com.demo.service.data.OrderDataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderLister {
    private final OrderDataBaseAccess dataBaseAccess;

    private OrderLister(@Autowired OrderDataBaseAccess dataBaseAccess){
        this.dataBaseAccess = dataBaseAccess;
    }

    public ArrayList<Order> listAllOrders(){
        return dataBaseAccess.getAllOrders();
    }
}
