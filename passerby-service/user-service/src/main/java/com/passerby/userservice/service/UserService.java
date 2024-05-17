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
        UserDTO user = getUser(request.getUsername());
        if(user == null) { // New user, create default labels and avatars for this user
            user = UserDTO.builder()
                    .username(username)
                    .avatar_key("Default")
                    .build();
            createUser(user);
        }
        String sid = sessionService.addSession(username);
        ResponseCookie cookie = ResponseCookie.from("sid", sid).path("/").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return Result.success(user);
    }

    public void createUser(UserDTO userDTO) {
        User user = User.builder()
                        .username(userDTO.getUsername())
                        .avatar_key(userDTO.getAvatar_key())
                        .build();

        userRepository.save(user);
    }

    public UserDTO updateUserAvatar(String username, String avatar) {
        User user = userRepository.findByUsername(username);
        user.setAvatar_key(avatar);
        userRepository.save(user);
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar_key(user.getAvatar_key())
                .build();
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> mapToUserDTO(user)).toList();
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar_key(user.getAvatar_key())
                .build();
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username);
        return UserDTO.builder()
                .username(user.getUsername())
                .avatar_key(user.getAvatar_key())
                .build();
    }

    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("^[A-Za-z0-9]{1,20}$");
    }
}
