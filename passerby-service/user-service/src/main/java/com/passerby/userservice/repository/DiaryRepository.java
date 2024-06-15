package com.passerby.userservice.repository;

import com.passerby.userservice.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DiaryRepository extends MongoRepository<Diary, String> {
    List<Diary> findByUsername(String username);

    @Query("{ 'username': ?0, 'label.labelKey': ?1 }")
    List<Diary> findByUsernameAndLabel(String username, String labelKey);
    @Query("{'published': true}")
    List<Diary> findPublishedDiaries();
    @Query("{'published': true, 'username': ?0}")
    List<Diary> findPublishedDiariesByUsername(String username);
}
