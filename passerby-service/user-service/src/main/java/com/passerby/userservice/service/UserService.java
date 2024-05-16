package com.passerby.userservice.service;

import com.passerby.userservice.model.User;
import com.passerby.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public User uploadAvatar(Long id, MultipartFile avatarFile) throws IOException {
//        return userRepository.findById(id).map(user -> {
//            // Logic to store the avatar file
//            user.setAvatarUrl("path/to/avatar/" + avatarFile.getOriginalFilename());
//            return userRepository.save(user);
//        }).orElseThrow(() -> new RuntimeException("User not found"));
//    }

    public User changePassword(Long id, String newPassword) {
        return userRepository.findById(id).map(user -> {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
