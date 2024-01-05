package com.springbanking.easymoney.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        /**
        Approach 1- Where we ue withDefaultPasswordEncoder() method while creating the user details- deprecated
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").authorities("admin").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").authorities("read").build();
        return new InMemoryUserDetailsManager(admin, user);
         */

        // Approach-2: Using NoOpPasswordEncoder - simplest password encoder- not for production
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails admin1 = User.withUsername("admin").password("admin").authorities("admin").build();
        UserDetails user1 = User.withUsername("user").password("user").authorities("read").build();
        inMemoryUserDetailsManager.createUser(admin1);
        inMemoryUserDetailsManager.createUser(user1);
        return inMemoryUserDetailsManager;
    }

    // Only applicable for Approach2
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
