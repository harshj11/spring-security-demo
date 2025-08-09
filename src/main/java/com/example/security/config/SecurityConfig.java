package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dashboard").authenticated()
                .anyRequest().permitAll());

        http.formLogin(Customizer.withDefaults());
        return http.build();
    }

    //@Bean
    public UserDetailsService inMemoryUserDetailsManager(PasswordEncoder encoder) {
        // Creating user1 and user2 without using password encoder.
        UserDetails user1 = User.withUsername("user1")
                .password("{noop}password")
                .build();

        UserDetails user2 = User.withUsername("user2")
                .password("{bcrypt}$2a$12$PiZleTYM2rp37e8EpWjUJ.hJB40mX/WLv36jLw6Ke4WsTQPbg2g.S")
                .build();

        // Creating user3 using password encoder. This is using BCrypt behind the scenes.
        UserDetails user3 = User.withUsername("user3")
                .password(encoder.encode("password3"))
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public UserDetailsService userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
