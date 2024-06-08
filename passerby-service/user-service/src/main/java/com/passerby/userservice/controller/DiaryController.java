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
    public Result addDiary(@RequestBody DiaryRequest diaryRequest, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return Result.error("invalid-diary-details");
        }
        if(!labelService.isValid(diaryRequest.getLabelKey())) {
            return Result.error("invalid-label");
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
        return Result.success(diaryDTO);
    }

    // update: PATCH - /${diaryId}
    @PatchMapping("/{diaryId}")
    public Result updateDiary(@PathVariable String diaryId, @RequestBody DiaryRequest diaryRequest, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        if(!diaryService.isValid(diaryRequest.getDetails())) {
            return Result.error("invalid-diary-details");
        }
        if(!labelService.isValid(diaryRequest.getLabelKey())) {
            return Result.error("invalid-label");
        }

        boolean exists = diaryService.contains(diaryId);
        if(!exists) {
            return Result.error("noSuchDiaryId");
        }

        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        if(!diaryDTO.getUsername().equals(username)) { // Check if the User id is match diary id
            return Result.error("notMatchUser");
        }

        Label label = labelService.getLabel(diaryRequest.getLabelKey());
        diaryDTO.setDetails(diaryRequest.getDetails());
        diaryDTO.setLabel(label);
        diaryDTO.setPublished(diaryRequest.isPublished());
        diaryDTO = diaryService.updateDiary(diaryId, diaryDTO);

        return Result.success(diaryDTO);
    }

    // delete : DELETE - /${diaryId}
    @DeleteMapping("/{diaryId}")
    public Result deleteDiary(@PathVariable String diaryId, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        if (!userService.isValidUsername(username)) {
            return Result.error("invalid-username");
        }

        boolean exists = diaryService.contains(diaryId);
        if(exists) diaryService.deleteDiary(diaryId);
        // Create the response map
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", exists ? ("The diaryId:  " + diaryId + " has be deleted"):("Diary " + diaryId + " did not exist"));
        return Result.success(responseBody);
    }

    @GetMapping("/{diaryId}")
    public Result getDiary(@PathVariable String diaryId, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        boolean exists = diaryService.contains(diaryId);
        if(!exists) {
            return Result.error("noSuchDiaryId");
        }
        DiaryDTO diaryDTO = diaryService.getDiary(diaryId);
        return Result.success(diaryDTO);
    }

    @GetMapping()
    public Result getPublishedDiaries(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        List<DiaryDTO> diaryList = diaryService.getPublishedDiaries();
        return Result.success(diaryList);
    }

    // Get a user's passersby's diaries: /username/{username}
    @GetMapping("/username/mine")
    public Result getPublishedDiariesByUsername(@CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        List<DiaryDTO> diaryList = diaryService.getPublishedDiariesByUsername(username);
        return Result.success(diaryList);
    }

    // Get a user's diaries of different labels: /label/{label}
    @GetMapping("/label/{labelKey}")
    public Result getDiariesByLabel(@PathVariable String labelKey, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        if (sid == null || username == null) {
            return Result.error("auth-missing");
        }
        if(!labelService.isValid(labelKey)) {
            return Result.error("invalid-label");
        }
        List<DiaryDTO> diaryList;

        if(labelKey == "all") { // Get all labels' diaries
            diaryList = diaryService.getDiariesByUsername(username);
        } else { // Get this label's diaries
            diaryList = diaryService.getDiariesByUsernameAndLabel(username, labelKey);
        }

        return Result.success(diaryList);
    }
}
