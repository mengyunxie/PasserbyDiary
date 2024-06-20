package com.passerby.apigateway.filter;

import com.passerby.apigateway.config.WhiteList;
import com.passerby.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements WebFilter {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final JwtUtil jwtUtil;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ROLE_USER = "ROLE_USER";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("JwtAuthenticationFilter 1: path - {}", path);

        // Check if the request path matches any of the whitelisted paths
        if (WhiteList.WHITELISTED_PATHS.stream().anyMatch(whiteListedPath -> pathMatcher.match(whiteListedPath, path))) {
            return chain.filter(exchange); // Skip filtering for whitelisted paths
        }

        // Get the Authorization header from the request
        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
        log.info("JwtAuthenticationFilter 2: authHeader - {}", authHeader);
        // Check if the header is present and starts with Bearer
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            // If no header or invalid header, proceed without authentication
            return chain.filter(exchange);
        }

        // Extract the JWT token from the Authorization header
        String jwtToken = authHeader.substring(BEARER_PREFIX.length());
        log.info("JwtAuthenticationFilter 3: jwtToken - {}", jwtToken);
        // Verify the JWT token
        if (!jwtUtil.validateToken(jwtToken)) {
            // Proceed with the filter chain
            return chain.filter(exchange);
        }

        String username = jwtUtil.getUsername(jwtToken);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singleton(new SimpleGrantedAuthority(ROLE_USER)));
        log.info("JwtAuthenticationFilter 4: authentication - {}", authentication);

        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }
}
