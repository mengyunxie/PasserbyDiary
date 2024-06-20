package com.passerby.userservice.service;

import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.User;
import com.passerby.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AvatarService avatarService;

    @Autowired
    LabelService labelService;

    public UserDTO getUserOrCreate (String  username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user == null) { // New user, create default labels and avatars for this user
            user = createUser(username, password, "Default");
        }

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .avatars(avatarService.getAllAvatars())
                .labels(labelService.getAllLabels())
                .build();

        return userDTO;
    }

    public UserDTO getUser (String  username) {
        User user = userRepository.findByUsername(username);
        return mapToUserDTO(user);
    }

    public User createUser(String username, String password, String avatar) {
        User user = User.builder()
                        .password(password)
                        .username(username)
                        .avatar(avatar)
                        .build();

        userRepository.save(user);
        return user;
    }

    public UserDTO updateUserAvatar(String username, String avatar) {
        User user = userRepository.findByUsername(username);
        user.setAvatar(avatar);
        userRepository.save(user);
        return mapToUserDTO(user);
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> mapToUserDTO(user)).toList();
    }

    private UserDTO mapToUserDTO(User user) {
        if(user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .avatars(avatarService.getAllAvatars())
                .labels(labelService.getAllLabels())
                .build();
    }

    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("^[A-Za-z0-9]{1,20}$");
    }
}
