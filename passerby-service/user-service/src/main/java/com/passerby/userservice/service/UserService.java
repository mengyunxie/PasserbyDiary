package com.passerby.userservice.service;

import com.passerby.userservice.dto.LoginRequest;
import com.passerby.userservice.dto.Result;
import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.User;
import com.passerby.userservice.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;
    @Autowired
    AvatarService avatarService;

    @Autowired
    LabelService labelService;
    public Result loginUser (@RequestBody LoginRequest request, HttpServletResponse response) {
        String username = request.getUsername();
        if(username == null || username.equals("")) {
            return Result.error("required-username");
        }

        if (!isValidUsername(username)) {

            return Result.error("invalid-username");
        }
        UserDTO userDTO = getUser(username);
        if(userDTO == null) { // New user, create default labels and avatars for this user
            userDTO = UserDTO.builder()
                    .username(username)
                    .avatar("Default")
                    .avatars(avatarService.getAllAvatars())
                    .labels(labelService.getAllLabels())
                    .build();
            createUser(userDTO);
        }
        String sid = sessionService.addSession(username);
        ResponseCookie cookie = ResponseCookie.from("sid", sid).path("/").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return Result.success(userDTO);
    }

    public void createUser(UserDTO userDTO) {
        User user = User.builder()
                        .username(userDTO.getUsername())
                        .avatar(userDTO.getAvatar())
                        .build();

        userRepository.save(user);
    }

    public UserDTO updateUserAvatar(String username, String avatar) {
        User user = userRepository.findByUsername(username);
        user.setAvatar(avatar);
        userRepository.save(user);
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .avatars(avatarService.getAllAvatars())
                .labels(labelService.getAllLabels())
                .build();
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> mapToUserDTO(user)).toList();
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .avatars(avatarService.getAllAvatars())
                .labels(labelService.getAllLabels())
                .build();
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .avatars(avatarService.getAllAvatars())
                .labels(labelService.getAllLabels())
                .build();
    }

    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("^[A-Za-z0-9]{1,20}$");
    }
}
