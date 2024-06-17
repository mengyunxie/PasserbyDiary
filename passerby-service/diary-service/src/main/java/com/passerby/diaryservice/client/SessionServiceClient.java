package com.passerby.diaryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "session-service")
@FeignClient(value = "session", url = "http://localhost:8081")
public interface SessionServiceClient {
    @GetMapping("/api/v1/session/sid/{sid}")
    String getSessionUser(@PathVariable("sid") String sid);
}
