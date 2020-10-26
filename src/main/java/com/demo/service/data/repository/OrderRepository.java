package com.demo.service.data.repository;

import com.demo.dto.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders WHERE customer_id = ?1", nativeQuery = true)
    ArrayList<Order> getOrders(long id);

    @Modifying
    @Query(value = "INSERT INTO orders VALUES(0, ?1, ?2, ?3)", nativeQuery = true)
    void save(String text, float price, long customer_id);

    @Modifying
    @Query(value = "UPDATE orders SET text = ?1, price = ?2 WHERE id = ?3", nativeQuery = true)
    void update(String text, float price, long id);

    Order getByIdEquals(long id);

    @Query(value = "SELECT * FROM orders WHERE id = (SELECT max(id) FROM orders)", nativeQuery = true)
    Order maxId();
}
