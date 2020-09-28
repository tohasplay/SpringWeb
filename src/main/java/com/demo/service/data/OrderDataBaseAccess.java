package com.demo.service.data;

import com.demo.businesscore.order.Order;

public interface OrderDataBaseAccess extends DataBaseAccess<Order>{

    @Override
    default void putObject(Order data) {
    }

    void putObject(Order data, long id);

    @Override
    default void deleteObject(Order data) {
    }

    void deleteObject(Order data, long id);
    @Override
    default void updateObject(Order data) {
    }

    void updateObject(Order data, long id);
    @Override
    default boolean contains(Order data) {
        return false;
    }

    boolean contains(Order data, long id);
}
