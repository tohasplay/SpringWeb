package com.demo.security;

import com.demo.auth.TokenRepositoryService;
import com.demo.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.demo.security.RoleConfig.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenRepositoryService tokenRepositoryService;


    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     UserService userService,
                                     TokenRepositoryService tokenRepositoryService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.tokenRepositoryService = tokenRepositoryService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/customer").anonymous()
                .antMatchers("/swagger-ui.html").hasRole(ADMIN.name())
                .antMatchers("/*", "/pages/*", "/js/*", "/styles/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .and()

                .rememberMe()
                .userDetailsService(userService)
                .tokenRepository(tokenRepositoryService)
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("sskoda87dsd78sd*/s*fsd7999fs")
                .and()

                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }
}
