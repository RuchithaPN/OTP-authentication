package com.example.autentication.otp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/register", "/verify").permitAll() // Allow public access to these endpoints
                .anyRequest().authenticated() // Require authentication for other endpoints
            .and()
            .csrf().disable() // Disable CSRF protection for simplicity
            .httpBasic(); // Use basic authentication for simplicity
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication provider for in-memory user details
        auth.inMemoryAuthentication()
                .withUser("user") // Username
                .password("{noop}password") // Password (Note: {noop} indicates plaintext password, this is not secure and should not be used in production)
                .roles("USER"); // Role(s) for the user
    }
}