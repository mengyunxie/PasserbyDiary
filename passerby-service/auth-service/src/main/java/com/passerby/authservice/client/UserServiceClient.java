package com.passerby.authservice.client;

import com.passerby.authservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserServiceClient {
//    @GetMapping("/api/v1/users/{id}")
//    UserDTO getUserById(@PathVariable("id") Long id);


    @GetMapping("/api/v1/users/{email}")
    UserDTO getUserByEmail(@PathVariable("email") String email);

    @PostMapping("/api/v1/users/register")
    public boolean registerUser(@RequestBody UserDTO userDetails);
}
