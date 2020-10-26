package com.demo.auth;

import com.demo.auth.repository.AppUserRepository;
import com.demo.dto.ApplicationUser;
import com.demo.security.RoleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserDaoService implements UserDao{

    private final AppUserRepository repository;

    @Autowired
    public UserDaoService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        Optional<ApplicationUser> user = repository.getByUsernameEquals(username);
        if (user.isPresent()){
            ApplicationUser applicationUser = user.get();
            return Optional.of(
                            new User(
                                    RoleConfig.valueOf(applicationUser.getRole()).getGrantedAuthorities(),
                                    applicationUser.getPassword(),
                                    applicationUser.getUsername(),
                                    applicationUser.getExpiration().after(Calendar.getInstance().getTime()),
                                    applicationUser.getLocked() == 1,
                                    applicationUser.getExpiration().after(Calendar.getInstance().getTime()),
                                    applicationUser.getEnabled() == 1
                            )
                    );
        }
        return Optional.empty();
    }

}
