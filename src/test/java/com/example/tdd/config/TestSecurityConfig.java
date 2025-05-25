package com.example.tdd.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Test 환경에서는 시큐리티 인가 설정을 모두 허용하는 Config를 작성하는 방법
 */
@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable());

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(("/**")).permitAll());

        return httpSecurity.build();
    }
}
