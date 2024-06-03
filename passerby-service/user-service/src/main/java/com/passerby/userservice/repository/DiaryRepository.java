package com.passerby.userservice.repository;

import com.passerby.userservice.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiaryRepository extends MongoRepository<Diary, Long> {
    List<Diary> findByUsername(String username);
    List<Diary> findByUsernameAndLabel(String username, String label);
}
