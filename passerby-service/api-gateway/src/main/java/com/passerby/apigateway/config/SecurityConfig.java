package com.passerby.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import java.util.*;
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final List<String> WHITELISTED_PATHS = Arrays.asList(
            "/api/v1/session/**",
            "/api/v1/diaries/**",
            "/api/v1/avatars/**",
            "/api/v1/labels/**"
    );

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(WHITELISTED_PATHS.toArray(new String[0]))
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .cors(cors -> cors.disable())
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
        return serverHttpSecurity.build();
    }
}