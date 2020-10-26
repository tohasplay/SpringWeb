package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_token")
public class UserToken{

    private String username;
    @Id
    private String series;
    private String token;
    private Date date;

}
