package com.demo.interactor;

import com.demo.dto.Order;
import com.demo.service.data.OrderDataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * integrated with {@link com.demo.service.presentor.OrderPresentorImpl}
 */
@Deprecated
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
