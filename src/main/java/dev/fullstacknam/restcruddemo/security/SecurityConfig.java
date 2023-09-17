package dev.fullstacknam.restcruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        var admin = User.builder().roles("ADMIN", "EMPLOYEE").username("admin").password("{noop}admin123").build();

        var john = User.builder().roles("EMPLOYEE").username("john").password("{noop}123").build();

        return new InMemoryUserDetailsManager(admin, john);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN"));

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
