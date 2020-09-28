package com.demo.service.presentor;

import com.demo.businesscore.order.Order;

public interface OrderUndefinedPresentor extends Presentor<Order> {
    @Override
    default Order add(Order data){
        return null;
    };

    Order add(Order data, long id, String password);


    @Override
    default void delete(long id) {
    }

    void delete(Order data, long id);

    @Override
    default Order update(long id, Order data) {
        return null;
    }

    Order update(Order data, long id);



}
