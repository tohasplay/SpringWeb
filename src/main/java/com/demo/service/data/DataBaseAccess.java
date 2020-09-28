package com.demo.service.data;

import java.util.List;

public interface DataBaseAccess<T> {
    List<T> getAllData();
    T findById(long id);
    void putObject(T data);
    void deleteObject(T data);
    void updateObject(T data);
    boolean contains(T data);
}
