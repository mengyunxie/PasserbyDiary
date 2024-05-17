package com.passerby.userservice.repository;

import com.passerby.userservice.model.Label;
import com.passerby.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, String> {
}
