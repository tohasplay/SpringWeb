package com.demo.service.presentor;

import java.util.ArrayList;

public interface Presentor<T> {
    T add(T data);
    T get(long id);
    void delete(long id);
    T update(long id, T data);
    ArrayList<T> getAll();
}
