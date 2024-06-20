package com.passerby.diaryservice.controller;

import com.passerby.diaryservice.client.LabelServiceClient;
import com.passerby.diaryservice.client.SessionServiceClient;
import com.passerby.diaryservice.client.UserServiceClient;
import com.passerby.diaryservice.dto.DiaryDTO;
import com.passerby.diaryservice.dto.DiaryRequest;
import com.passerby.diaryservice.dto.Result;
import com.passerby.diaryservice.dto.UserDTO;
import com.passerby.diaryservice.model.Label;
import com.passerby.diaryservice.service.DiaryService;
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
@RequestMapping("/api/v1/diaries")
@Slf4j
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @Autowired
    private SessionServiceClient sessionServiceClient;

    @Autowired
    private LabelServiceClient labelServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/test")
    public void test() {
        log.info("Diary test");
    }

    // Add: Post - /api/v1/diaries
    @PostMapping
    public ResponseEntity<Result> addDiary(@RequestBody DiaryRequest diaryRequest, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return new ResponseEntity<>(Result.error("invalid-diary-details"), HttpStatus.BAD_REQUEST);
        }
        if(!labelServiceClient.isValid(diaryRequest.getLabelKey())) {
            return new ResponseEntity<>(Result.error("invalid-label"), HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = userServiceClient.getUser(username);
        Label label = labelServiceClient.getLabel(diaryRequest.getLabelKey());
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
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return new ResponseEntity<>(Result.error("invalid-diary-details"), HttpStatus.BAD_REQUEST);
        }
        if(!labelServiceClient.isValid(diaryRequest.getLabelKey())) {
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

        Label label = labelServiceClient.getLabel(diaryRequest.getLabelKey());
        diaryDTO.setDetails(diaryRequest.getDetails());
        diaryDTO.setLabel(label);
        diaryDTO.setPublished(diaryRequest.isPublished());
        diaryDTO = diaryService.updateDiary(diaryId, diaryDTO);

        return new ResponseEntity<>(Result.success(diaryDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateDiariesUserAvatar(@RequestParam String username, @RequestParam String avatar) {
        diaryService.updateDiariesUserAvatar(username, avatar);
        return ResponseEntity.ok().build();
    }

    // delete : DELETE - /${diaryId}
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Result> deleteDiary(@PathVariable String diaryId, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if (!userServiceClient.isValidUsername(username)) {
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
        String username = sessionServiceClient.getSessionUser(sid);
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
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        List<DiaryDTO> diaryList = diaryService.getPublishedDiaries();
        return new ResponseEntity<>(Result.success(diaryList), HttpStatus.OK);
    }

    // Get a user's passersby's diaries: /username/{username}
    @GetMapping("/username/mine")
    public ResponseEntity<Result> getPublishedDiariesByUsername(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }

        List<DiaryDTO> diaryList = diaryService.getPublishedDiariesByUsername(username);
        return new ResponseEntity<>(Result.success(diaryList), HttpStatus.OK);
    }

    // Get a user's diaries of different labels: /label/{label}
    @GetMapping("/label/{labelKey}")
    public ResponseEntity<Result> getDiariesByLabel(@PathVariable String labelKey, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionServiceClient.getSessionUser(sid);
        if (sid == null || username == null) {
            return new ResponseEntity<>(Result.error("auth-missing"), HttpStatus.UNAUTHORIZED);
        }
        if(!labelServiceClient.isValid(labelKey)) {
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
