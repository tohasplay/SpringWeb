package com.demo.utils;

import java.util.Objects;

public class Node<T,V> {
    public T first;
    public V second;

    public Node(T first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?, ?> node = (Node<?, ?>) o;

        if (!Objects.equals(first, node.first)) return false;
        return Objects.equals(second, node.second);
    }

}
