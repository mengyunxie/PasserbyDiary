package com.passerby.authservice.service;

import com.passerby.authservice.client.UserServiceClient;
import com.passerby.authservice.dto.User;
import com.passerby.authservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserServiceClient userServiceClient;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserServiceClient userServiceClient, JwtUtil jwtUtil) {
        this.userServiceClient = userServiceClient;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username) {
        User user = userServiceClient.getUserByUsername(username);
        if (user == null) {
            user = userServiceClient.createUser(new User(username));
        }
        return jwtUtil.generateToken(user);
    }

    public String refreshToken(String token) {
        return jwtUtil.refreshToken(token);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
