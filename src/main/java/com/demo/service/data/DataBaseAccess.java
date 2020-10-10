package com.demo.service.data;

import com.demo.dto.Customer;

import java.util.List;
//DONE: rename
public interface DataBaseAccess<T> {
    List<T> getAll();
    T findById(long id);
    Customer put(T data);
    void delete(long id);
    void update(long id, T data);
    boolean contains(T data);
}
