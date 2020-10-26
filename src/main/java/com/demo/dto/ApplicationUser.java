package com.demo.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "application_user")
@Data
public class ApplicationUser {

    @Id
    private String username;
    private String password;
    private Date expiration;
    private int locked;
    private int enabled;
    private String role;
}
