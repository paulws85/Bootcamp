package com.pawel.wojtanka.bootcamp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
//            .antMatchers("/admin/**").authenticated()
//            .antMatchers("/panel-trenera/**").authenticated()
//            .antMatchers("/panel-klienta/**").authenticated()
            .antMatchers("/admin/**").hasRole("admin")
            .antMatchers("/panel-trenera/**").hasRole("teacher")
            .antMatchers("/panel-klienta/**").hasRole("user")
//            .antMatchers("/panel-klienta/**").hasAnyRole("admin", "user") //jak chcemy podawac kilka ról
            .anyRequest().permitAll()
            .and()
                .formLogin()
            .and()
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//            .withUser("pawel1").password("{bcrypt}$2y$12$42g0eG1LQRl6o8xasS8Y3erzCOcKjj6wOeasjkUS2nyPrdpRoAfiW").roles("admin") // pawel1 to jest nasz hash a nie hasło do zahashowania
//            .and()
//            .withUser("pawel2").password("{noop}pawel2").roles("student");
            .withUser("admin").password("$2y$12$Ruc34mWFQkP8hJK/7SsfZuxKC.O.GdSSbSb5GI6aWjce9NMfG.O6y").roles("admin")
            .and()
            .withUser("teacher").password("$2y$12$RJKaxxKn2TRUk9uTRxZnzOPDuekgq2r2Z1D7GAmqUTcxsClF401be").roles("teacher")
            .and()
            .withUser("user").password("$2y$12$S55gOVRHa45Qig/H5HxNjuSLRJKtbMXZl9uIULPnS4jrmsAnUZYdC").roles("user");
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //wówczas klucz enkodera w linii 29 i 31 nie jest stosowany
        return new BCryptPasswordEncoder();
    }

}
