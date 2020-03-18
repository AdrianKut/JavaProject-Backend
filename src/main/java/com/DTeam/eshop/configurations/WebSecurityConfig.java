package com.DTeam.eshop.configurations;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).rolePrefix("ROLE_")
                .dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, password as credentials, enabled from users where email=?")
                .authoritiesByUsernameQuery("select user_email as principal, role_name as role from users_roles where user_email = ? ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/register", "/",  "/login", "/css/**", "/js/**","/img/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/employee/**").hasAnyRole("pracownik")
                .antMatchers("/customer/**").hasAnyRole("klient")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}