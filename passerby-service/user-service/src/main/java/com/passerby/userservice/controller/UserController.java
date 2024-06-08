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
    public ResponseEntity<Result> loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        String username = request.getUsername();
        if(username == null || username.equals("")) { // The username is empty
            return new ResponseEntity<>(Result.error("required-username"), HttpStatus.BAD_REQUEST);
        }

        if (!userService.isValidUsername(username)) {
            return new ResponseEntity<>(Result.error("invalid-username"), HttpStatus.BAD_REQUEST);
        }

        if(username.equals("dog")) {
            return new ResponseEntity<>(Result.error("auth-insufficient"), HttpStatus.FORBIDDEN);
        }

        UserDTO userDTO = userService.loginUser(username);

        String sid = sessionService.addSession(username);
        ResponseCookie cookie = ResponseCookie.from("sid", sid).path("/").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
    }

    /* Check for existing session (used on page load) */
    @GetMapping()
    public ResponseEntity<Result> checkSession(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = userService.getUser(username);
        return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
    }

    /* Update user's avatar */
    @PatchMapping()
    public ResponseEntity<Result> updateUserAvatar(@RequestBody UpdateAvatarRequest request, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || !userService.isValidUsername(username)) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }

        String avatar = request.getAvatar();

        if(!avatarService.isValidAvatar(avatar)) { // Check if this avatar is a system's built-in avatar
            return new ResponseEntity<>(Result.error("invalid-avatar"), HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = userService.updateUserAvatar(username, request.getAvatar()); // Update the user's avatar
        diaryService.updateDiariesUserAvatar(username, avatar); // Update the user's avatar of the diaries object
        return new ResponseEntity<>(Result.success(userDTO), HttpStatus.OK);
    }

    /* Logout */
    @DeleteMapping()
    public ResponseEntity<Result> logoutUser(@CookieValue(value = "sid", defaultValue = "") String sid, HttpServletResponse response) {
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
        return new ResponseEntity<>(Result.success(responseBody), HttpStatus.OK);
    }
}
