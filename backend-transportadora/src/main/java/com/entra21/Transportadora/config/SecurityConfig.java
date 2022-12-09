package com.entra21.Transportadora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and().authorizeRequests((req) -> req
            .antMatchers(HttpMethod.POST, "/pessoa").permitAll()
            .antMatchers("/pessoa/login").permitAll()
            .antMatchers("/pessoa/cadastro").permitAll()
            .antMatchers("/funcionario/cadastro").permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
