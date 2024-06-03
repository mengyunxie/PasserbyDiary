package com.passerby.userservice.service;

import com.passerby.userservice.model.Avatar;
import com.passerby.userservice.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;

    public Avatar getAvatar(String key) {
        return avatarRepository.findById(key).orElse(null);
    }

    public Map<String, String> getAllAvatars() {
        List<Avatar> avatarList = avatarRepository.findAll();
        return avatarList.stream().collect(Collectors.toMap(Avatar::getAvatar_key, Avatar::getAvatar_value));
    }

    public boolean isValidAvatar(String key) {
        Avatar avatar = avatarRepository.findById(key).orElse(null);
        return avatar != null;
    }
}
