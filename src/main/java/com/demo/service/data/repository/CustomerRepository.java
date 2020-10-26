package com.demo.service.data.repository;

import com.demo.dto.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE id = (SELECT max(id) FROM customer)", nativeQuery = true)
    Customer maxId();

    @Modifying
    @Query(value = "UPDATE customer SET name = ?2, mail = ?3, phone = ?4, password = ?5 WHERE id = ?1", nativeQuery = true)
    void update(long id, String name, String mail, String phone, String password);

    boolean existsCustomerByMail(String mail);

    boolean existsCustomerByIdAndPassword(long id, String password);



}
