package com.demo.auth.repository;

import com.demo.dto.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<ApplicationUser,String> {

     Optional<ApplicationUser> getByUsernameEquals(String username);
}
