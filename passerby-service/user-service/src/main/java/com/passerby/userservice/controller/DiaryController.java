package com.passerby.userservice.controller;

import com.passerby.userservice.dto.DiaryDTO;
import com.passerby.userservice.dto.Result;
import com.passerby.userservice.service.DiaryService;
import com.passerby.userservice.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public List<DiaryDTO> getDiaries() {
        return diaryService.getAllDiaries();
    }

    @GetMapping("/{diaryId}")
    public DiaryDTO getDiary(@PathVariable Long diaryId) {
        return diaryService.getDiary(diaryId);
    }

    // Add: Post - /api/v1/diaries
    @PostMapping
    public Result addDiary(@RequestBody DiaryDTO diary, @CookieValue(value = "sid", defaultValue = "") String sid) {
        String username = sessionService.getSessionUser(sid);
        return Result.success();
    }

    // update: PATCH - /${diaryId}

    // delete : DELETE - /${diaryId}

    // Get a user's passersby's diaries: /username/{username}

    // Get a user's diaries of different labels: /label/{label}
}
