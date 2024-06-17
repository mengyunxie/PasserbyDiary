package com.passerby.diaryservice.client;

import com.passerby.diaryservice.dto.UserDTO;
import com.passerby.diaryservice.model.Label;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "label-service")
@FeignClient(value = "label", url = "http://localhost:8081")
public interface LabelServiceClient {

    @GetMapping("/api/v1/session/labels/valid/{labelKey}")
    boolean isValid(@PathVariable("labelKey") String labelKey);

    @GetMapping("/api/v1/session/labels/{labelKey}")
    Label getLabel(@PathVariable("labelKey") String labelKey);
}
