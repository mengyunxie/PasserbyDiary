package com.passerby.authservice.controller;

import com.passerby.authservice.client.UserServiceClient;
import com.passerby.authservice.dto.LoginRequest;
import com.passerby.authservice.dto.Result;
import com.passerby.authservice.dto.UserDTO;
import com.passerby.authservice.service.AuthService;
import com.passerby.authservice.service.CustomAuthenticationManager;
import com.passerby.authservice.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private AuthenticationManager customAuthenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    public AuthController(AuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @GetMapping("/test")
    public void test() {
        log.info("Auth test");
    }

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();

        try {
            // Verify user details with user-service
            Result<UserDTO> result = userServiceClient.getUserOrCreate(request);
            UserDTO userDTO = result.getData();
            if (userDTO == null
                    || !userDTO.getUsername().equals(username)
                    || !userDTO.getPassword().equals(password)) {
                return ResponseEntity.status(401).body(Result.error("Invalid username or password"));
            }

            // Authenticate user
            Authentication authentication = customAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateToken(username);
            String authStr = "Bearer " + token;

            response.setHeader("Authorization", authStr);

//            // Set JWT token in cookie
//            Cookie cookie = new Cookie("jwt", token);
//            cookie.setHttpOnly(true);
//            cookie.setSecure(true); // Use true in production to only send the cookie over HTTPS
//            cookie.setPath("/");
//            cookie.setMaxAge(10 * 60 * 60); // Set cookie expiry time
//            response.addCookie(cookie);

            return ResponseEntity.ok().body(Result.success(authStr));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Result.error("Invalid username or password"));
        }
    }
}
