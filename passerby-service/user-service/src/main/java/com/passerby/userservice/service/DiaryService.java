package com.passerby.userservice.service;

import com.passerby.userservice.dto.DiaryDTO;
import com.passerby.userservice.model.Diary;
import com.passerby.userservice.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public List<DiaryDTO> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();

        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }

    public DiaryDTO getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        return mapToDiaryDTO(diary);
    }

    public DiaryDTO addDiary(DiaryDTO diaryDTO) {
        Diary diary = mapToDiary(diaryDTO);
        diaryRepository.save(diary);
        return diaryDTO;
    }

    public DiaryDTO updateDiary(DiaryDTO diaryDTO) {
        Diary diary = mapToDiary(diaryDTO);
        diaryRepository.save(diary);
        return diaryDTO;
    }

    public void deleteDiary(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    public List<DiaryDTO> getDiariesByUsername(String username) {
        List<Diary> diaries = diaryRepository.findByUsername(username);
        return diaries.stream().map(diary -> mapToDiaryDTO(diary)).toList();
    }

    public List<DiaryDTO> getDiariesByLabel(String username, String label) {
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
                .isPasserby(diary.isPasserby())
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
                .isPasserby(diaryDTO.isPasserby())
                .intro(diaryDTO.getIntro())
                .details(diaryDTO.getDetails())
                .build();
    }


    public void updateDiariesUserAvatar(String username, String avatar) {
        List<Diary> diaries = diaryRepository.findByUsername(username);
        for (Diary diary : diaries) {
            diary.setAvatar(avatar);
        }
        diaryRepository.saveAll(diaries);
    }
}
