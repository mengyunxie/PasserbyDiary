package com.passerby.userservice.controller;

import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.User;
import com.passerby.userservice.repository.UserRepository;
import com.passerby.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();

        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("- - - Get user info for id: {} - - -", id);
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        log.info("- - - Get user info for email: {} - - -", email);
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    @PostMapping("/{id}/avatar")
//    public ResponseEntity<User> uploadAvatar(@PathVariable Long id, @RequestParam("avatar") MultipartFile avatarFile) {
//        try {
//            return ResponseEntity.ok(userService.uploadAvatar(id, avatarFile));
//        } catch (IOException e) {
//            return ResponseEntity.status(500).build();
//        }
//    }

    @PostMapping("/{id}/password")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestParam("password") String newPassword) {
        return ResponseEntity.ok(userService.changePassword(id, newPassword));
    }
}
