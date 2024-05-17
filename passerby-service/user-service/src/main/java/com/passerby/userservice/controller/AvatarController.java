package com.passerby.userservice.controller;

import com.passerby.userservice.model.Avatar;
import com.passerby.userservice.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/session/avatars")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/{key}")
    public Avatar getAvatar(@PathVariable String key) {
        return avatarService.getAvatar(key);
    }

    @GetMapping("/all")
    public Map<String, String> getAllAvatars() {
        return avatarService.getAllAvatars();
    }
}
