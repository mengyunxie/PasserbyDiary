package com.passerby.authservice.service;

import com.passerby.authservice.client.UserServiceClient;
import com.passerby.authservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String registerUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userServiceClient.registerUser(userDTO);
        return "user added to the system";
    }

    public String generateToken(UserDTO userDTO) {
        return jwtService.generateToken(userDTO);
    }

    public void isTokenValid(String token, UserDTO userDTO) {
        jwtService.isTokenValid(token, userDTO);
    }
}
