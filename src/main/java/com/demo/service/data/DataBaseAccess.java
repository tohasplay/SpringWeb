package com.demo.service.data;

import com.demo.businesscore.Customer;

import java.util.List;

public interface DataBaseAccess<T> {
    List<T> getAllData();
    T findById(long id);
    Customer putObject(T data);
    void deleteObject(T data);
    void updateObject(long id, T data);
    boolean contains(T data);
}
