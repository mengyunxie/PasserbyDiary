package com.passerby.userservice.repository;

import com.passerby.userservice.model.Label;
import com.passerby.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, String> {
    Optional<Label> findByLabelKey(String labelKey);
}
