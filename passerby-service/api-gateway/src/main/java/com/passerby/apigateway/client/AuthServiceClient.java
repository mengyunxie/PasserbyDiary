package com.passerby.apigateway.client;

import com.passerby.apigateway.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:8082")
public interface AuthServiceClient {
    @PostMapping("/api/isTokenValid")
    public boolean isTokenValid(@RequestHeader("Authorization") String token, @RequestBody UserDTO userDetails);
}
