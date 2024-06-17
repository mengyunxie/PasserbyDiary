package com.passerby.diaryservice.client;

import com.passerby.diaryservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "user-service")
@FeignClient(value = "user", url = "http://localhost:8081")
public interface UserServiceClient {
    @GetMapping("/api/v1/session/valid/{username}")
    boolean isValidUsername(@PathVariable("username") String username);

    @GetMapping("/api/v1/session/user/{username}")
    UserDTO getUser(@PathVariable("username") String username);
}
