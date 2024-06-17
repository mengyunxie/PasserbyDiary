package com.passerby.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "diary-service")
@FeignClient(value = "diary", url = "http://localhost:8082")
public interface DiaryServiceClient {

    @PutMapping("/api/v1/diaries/update")
    void updateDiariesUserAvatar(@RequestParam("username") String username, @RequestParam("avatar") String avatar);
}