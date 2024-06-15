package com.passerby.userservice.controller;

import com.passerby.userservice.dto.DiaryDTO;
import com.passerby.userservice.dto.DiaryRequest;
import com.passerby.userservice.dto.Result;
import com.passerby.userservice.dto.UserDTO;
import com.passerby.userservice.model.Label;
import com.passerby.userservice.service.DiaryService;
import com.passerby.userservice.service.LabelService;
import com.passerby.userservice.service.SessionService;
import com.passerby.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/session/diaries")
@Slf4j
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private UserService userService;

    // Add: Post - /api/v1/diaries
    @PostMapping
    public ResponseEntity<Result> addDiary(@RequestBody DiaryRequest diaryRequest, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return new ResponseEntity<>(Result.error("invalid-diary-details"), HttpStatus.BAD_REQUEST);
        }
        if(!labelService.isValid(diaryRequest.getLabelKey())) {
            return new ResponseEntity<>(Result.error("invalid-label"), HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = userService.getUser(username);
        Label label = labelService.getLabel(diaryRequest.getLabelKey());
        LocalDateTime currentDateTime = LocalDateTime.now();
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .username(userDTO.getUsername())
                .avatar(userDTO.getAvatar())
                .label(label)
                .date(currentDateTime)
                .published(diaryRequest.isPublished())
                .build();
        diaryDTO.setDetails(diaryRequest.getDetails());
        diaryDTO = diaryService.addDiary(diaryDTO);
        return new ResponseEntity<>(Result.success(diaryDTO), HttpStatus.OK);
    }

    // update: PATCH - /${diaryId}
    @PatchMapping("/{diaryId}")
    public ResponseEntity<Result> updateDiary(@PathVariable String diaryId, @RequestBody DiaryRequest diaryRequest, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return new ResponseEntity<>(Result.error("invalid-diary-details"), HttpStatus.BAD_REQUEST);
        }
        if(!labelService.isValid(diaryRequest.getLabelKey())) {
            return new ResponseEntity<>(Result.error("invalid-label"), HttpStatus.BAD_REQUEST);
        }

        boolean exists = diaryService.contains(diaryId);
        if(!exists) {
            return new ResponseEntity<>(Result.error("noSuchDiaryId"), HttpStatus.NOT_FOUND);
        }

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        if(!diaryDTO.getUsername().equals(username)) { // Check if the User id is match diary id
            return new ResponseEntity<>(Result.error("notMatchUser"), HttpStatus.NOT_FOUND);
        }

        Label label = labelService.getLabel(diaryRequest.getLabelKey());
        diaryDTO.setDetails(diaryRequest.getDetails());
        diaryDTO.setLabel(label);
        diaryDTO.setPublished(diaryRequest.isPublished());
        diaryDTO = diaryService.updateDiary(diaryId, diaryDTO);

        return new ResponseEntity<>(Result.success(diaryDTO), HttpStatus.OK);
    }

    // delete : DELETE - /${diaryId}
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Result> deleteDiary(@PathVariable String diaryId, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if (!userService.isValidUsername(username)) {
            return new ResponseEntity<>(Result.error("invalid-username"), HttpStatus.BAD_REQUEST);
        }

        boolean exists = diaryService.contains(diaryId);
        if(exists) diaryService.deleteDiary(diaryId);
        // Create the response map
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", exists ? ("The diaryId:  " + diaryId + " has be deleted"):("Diary " + diaryId + " did not exist"));
        return new ResponseEntity<>(Result.success(responseBody), HttpStatus.OK);
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<Result> getDiary(@PathVariable String diaryId, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        boolean exists = diaryService.contains(diaryId);
        if(!exists) {
            return new ResponseEntity<>(Result.error("noSuchDiaryId"), HttpStatus.NOT_FOUND);
        }
        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        return new ResponseEntity<>(Result.success(diaryDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Result> getPublishedDiaries(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        List<DiaryDTO> diaryList = diaryService.getPublishedDiaries();
        return new ResponseEntity<>(Result.success(diaryList), HttpStatus.OK);
    }

    // Get a user's passersby's diaries: /username/{username}
    @GetMapping("/username/mine")
    public ResponseEntity<Result> getPublishedDiariesByUsername(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }

        List<DiaryDTO> diaryList = diaryService.getPublishedDiariesByUsername(username);
        return new ResponseEntity<>(Result.success(diaryList), HttpStatus.OK);
    }

    // Get a user's diaries of different labels: /label/{label}
    @GetMapping("/label/{labelKey}")
    public ResponseEntity<Result> getDiariesByLabel(@PathVariable String labelKey, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!labelService.isValid(labelKey)) {
            return new ResponseEntity<>(Result.error("invalid-label"), HttpStatus.BAD_REQUEST);
        }
        List<DiaryDTO> diaryList;

        if(labelKey.equals("all")) { // Get all labels' diaries
            diaryList = diaryService.getDiariesByUsername(username);
        } else { // Get this label's diaries
            diaryList = diaryService.getDiariesByUsernameAndLabel(username, labelKey);
        }

        return new ResponseEntity<>(Result.success(diaryList), HttpStatus.OK);
    }
}
