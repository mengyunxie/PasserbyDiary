package com.passerby.authservice.config;


import com.passerby.authservice.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final JwtAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange ->
//                        exchange.pathMatchers("/auth/**")
//                                .permitAll()
//                                .anyExchange()
//                                .authenticated())
//                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));


        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
