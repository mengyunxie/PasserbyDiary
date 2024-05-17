package com.passerby.authservice.client;

import com.passerby.authservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/v1/users/{username}")
    User getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/api/v1/users")
    User createUser(@RequestBody User user);

}
