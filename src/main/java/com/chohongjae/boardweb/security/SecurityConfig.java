package com.chohongjae.boardweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(securityUserDetailsService);

        http.authorizeRequests().antMatchers("/", "/system/**").permitAll();
        http.authorizeRequests().antMatchers("/board/**").authenticated();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        http.csrf().disable();
        http.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
        http.exceptionHandling().accessDeniedPage("/system/accessDenied");
        http.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
