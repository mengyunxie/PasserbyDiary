package com.passerby.authservice.controller;

import com.passerby.authservice.dto.JwtResponse;
import com.passerby.authservice.dto.LoginRequest;
import com.passerby.authservice.dto.UserDTO;
import com.passerby.authservice.service.AuthService;
import com.passerby.authservice.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO user) {
        return service.registerUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserDTO user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(user);
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token, @RequestBody UserDTO user) {
        service.isTokenValid(token, user);
        return "Token is valid";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate user
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Generate JWT token
//        String token = jwtTokenUtil.generateToken(authentication);
//
//        return ResponseEntity.ok(new JwtResponse(token));
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
//        // Perform logout logic, e.g., invalidate token
//        return ResponseEntity.ok(new ApiResponse(true, "Logout successful"));
        return null;
    }
}
