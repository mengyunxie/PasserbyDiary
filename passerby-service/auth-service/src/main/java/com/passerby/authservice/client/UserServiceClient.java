package com.passerby.authservice.client;

import com.passerby.authservice.dto.LoginRequest;
import com.passerby.authservice.dto.Result;
import com.passerby.authservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @PostMapping("/api/v1/users/login")
    Result<UserDTO> getUserOrCreate(@RequestBody LoginRequest request);

    @GetMapping("/api/v1/users/{username}")
    UserDTO getUser(@PathVariable("username") String username);
}
