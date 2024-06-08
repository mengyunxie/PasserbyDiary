package com.passerby.userservice.service;

import com.passerby.userservice.dto.DiaryDTO;
import com.passerby.userservice.dto.DiaryRequest;
import com.passerby.userservice.model.Diary;
import com.passerby.userservice.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public List<DiaryDTO> getPublishedDiaries() {
        List<Diary> diaries = diaryRepository.findPublishedDiaries();

        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }


    public DiaryDTO getDiary(String diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        return mapToDiaryDTO(diary);
    }

    public boolean contains(String diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        return diary != null;
    }

    public DiaryDTO addDiary(DiaryDTO diaryDTO) {
        Diary diary = mapToDiary(diaryDTO);
        return mapToDiaryDTO(diaryRepository.save(diary));
    }

    public DiaryDTO updateDiary(String diaryId, DiaryDTO diaryDTO) {
        Diary diary = mapToDiary(diaryDTO);
        diaryRepository.save(diary);
        return diaryDTO;
    }

    public void deleteDiary(String diaryId) {
       diaryRepository.deleteById(diaryId);
    }

    public List<DiaryDTO> getPublishedDiariesByUsername(String username) {
        List<Diary> diaries = diaryRepository.findPublishedDiariesByUsername(username);
        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }

    public List<DiaryDTO> getDiariesByUsernameAndLabel(String username, String label) {
        List<Diary> diaries = diaryRepository.findByUsernameAndLabel(username, label);
        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }

    private DiaryDTO mapToDiaryDTO(Diary diary) {
        return DiaryDTO.builder()
                .id(diary.getId())
                .username(diary.getUsername())
                .avatar(diary.getAvatar())
                .date(diary.getDate())
                .label(diary.getLabel())
                .published(diary.isPublished())
                .intro(diary.getIntro())
                .details(diary.getDetails())
                .build();
    }

    private Diary mapToDiary(DiaryDTO diaryDTO) {
        return Diary.builder()
                .id(diaryDTO.getId())
                .username(diaryDTO.getUsername())
                .avatar(diaryDTO.getAvatar())
                .date(diaryDTO.getDate())
                .label(diaryDTO.getLabel())
                .published(diaryDTO.isPublished())
                .intro(diaryDTO.getIntro())
                .details(diaryDTO.getDetails())
                .build();
    }


    public void updateDiariesUserAvatar(String username, String avatar) {
        List<Diary> diaries = diaryRepository.findPublishedDiariesByUsername(username);
        for (Diary diary : diaries) {
            diary.setAvatar(avatar);
        }
        diaryRepository.saveAll(diaries);
    }

    public boolean isValid(String details) {
        if(details == null) return false;
        details = details.trim();
        return details.length() <= 3000;
    }

    public List<DiaryDTO> getDiariesByUsername(String username) {
        List<Diary> diaries = diaryRepository.findByUsername(username);
        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }
}
