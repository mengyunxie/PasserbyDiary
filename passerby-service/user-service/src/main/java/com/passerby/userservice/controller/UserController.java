package com.passerby.userservice.controller;

import com.passerby.userservice.dto.LoginRequest;
import com.passerby.userservice.dto.UpdateAvatarRequest;
import com.passerby.userservice.dto.Result;
import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.User;
import com.passerby.userservice.service.AvatarService;
import com.passerby.userservice.service.DiaryService;
import com.passerby.userservice.service.SessionService;
import com.passerby.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/session")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private DiaryService diaryService;

    /* Create a new session (login) */
    @PostMapping()
    public Result loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        String username = request.getUsername();
        if(username == null || username.equals("")) { // The username is empty
            return Result.error("required-username");
        }

        if (!userService.isValidUsername(username)) {
            return Result.error("invalid-username");
        }

        if(username.equals("dog")) {
            return Result.error("auth-insufficient");
        }

        UserDTO userDTO = userService.loginUser(username);

        String sid = sessionService.addSession(username);
        ResponseCookie cookie = ResponseCookie.from("sid", sid).path("/").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return Result.success(userDTO);
    }

    /* Check for existing session (used on page load) */
    @GetMapping()
    public Result checkSession(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        UserDTO userDTO = userService.getUser(username);
        return Result.success(userDTO);
    }

    /* Update user's avatar */
    @PatchMapping()
    public Result updateUserAvatar(@RequestBody UpdateAvatarRequest request, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || !userService.isValidUsername(username)) {
            return Result.error("auth-missing");
        }

        String avatar = request.getAvatar();

        if(!avatarService.isValidAvatar(avatar)) { // Check if this avatar is a system's built-in avatar
            return Result.error("invalid-avatar");
        }

        UserDTO userDTO = userService.updateUserAvatar(username, request.getAvatar()); // Update the user's avatar
        diaryService.updateDiariesUserAvatar(username, avatar); // Update the user's avatar of the diaries object
        return Result.success(userDTO);
    }

    /* Logout */
    @DeleteMapping()
    public Result logoutUser(@CookieValue(value = "sid", defaultValue = "") String sid, HttpServletResponse response) {
        String username = sessionService.getSessionUser(sid);
        if(sid == null) {
            ResponseCookie cookie = ResponseCookie.from("sid", "").path("/").maxAge(0).build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }
        if (username != null) {
            sessionService.deleteSession(sid);
        }

        // Create the response map
        Map<String, Boolean> responseBody = new HashMap<>();
        responseBody.put("wasLoggedIn", username == null? false : !username.isEmpty());
        return Result.error(responseBody);
    }
}
