package com.demo.service.presentor;

import com.demo.utils.Node;

public interface CustomerUndefinedPresentor<T> extends Presentor<T> {
    Node<Float, Integer> getChek(long id);
}
