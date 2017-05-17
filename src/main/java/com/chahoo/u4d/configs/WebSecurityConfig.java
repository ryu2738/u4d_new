package com.chahoo.u4d.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Keesun Baik
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// csrf토큰이 반드시 필요하므로 disable 시킴
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //cors처리를 위한 메소드 허용
                .anyRequest().permitAll();

    }

}
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// csrf토큰이 반드시 필요하므로 disable 시킴

        http.httpBasic();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/accounts*//**").hasRole("USER")
 .antMatchers(HttpMethod.PUT, "/accounts*//**").hasRole("USER")
 .antMatchers(HttpMethod.DELETE, "/accounts*//**").hasRole("USER")
 .anyRequest().permitAll();

 }*/

// ROLE Hieracy
