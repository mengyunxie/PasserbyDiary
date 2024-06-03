package com.passerby.userservice.controller;

import com.passerby.userservice.dto.LoginRequest;
import com.passerby.userservice.dto.UpdateAvatarRequest;
import com.passerby.userservice.dto.Result;
import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.User;
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

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/session")
//@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }

    @PostMapping()
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        log.info(String.valueOf(request));
        Result result = userService.loginUser(request,response);
        if(result.getCode() == 0) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO userDTO = (UserDTO) result.getData();
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // or another appropriate status
        }
    }

    @GetMapping()
    public ResponseEntity<?> checkSession(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        log.info("sid: {}, username: {}",sid, username);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "auth-missing"));
        }
        UserDTO user = userService.getUser(username);
        log.info("UserDTO: {}",user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping()
    public ResponseEntity<?> updateUserAvatar(@RequestBody UpdateAvatarRequest request, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (username == null || !userService.isValidUsername(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "auth-missing"));
        }
        UserDTO userDTO = userService.updateUserAvatar(username, request.getAvatar());
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping()
    public ResponseEntity<?> logoutUser(@CookieValue(value = "sid", defaultValue = "") String sid, HttpServletResponse response) {
        String username = sessionService.getSessionUser(sid);
        if (username != null) {
            sessionService.deleteSession(sid);
        }
        ResponseCookie cookie = ResponseCookie.from("sid", "").path("/").maxAge(0).build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(Map.of("wasLoggedIn", username != null));
    }
}
