package com.demo.auth.repository;

import com.demo.dto.UserToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface TokenRepository extends CrudRepository<UserToken, String> {

    @Modifying
    @Query(value = "UPDATE application_token SET token = ?2, date = ?3 WHERE series = ?1", nativeQuery = true)
    void update(String series, String tokenValue, Date lastUsed);

    void deleteByUsername(String username);

    UserToken findUserTokenBySeries(String series);

}
