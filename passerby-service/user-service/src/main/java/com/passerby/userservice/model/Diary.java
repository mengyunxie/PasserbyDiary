package com.passerby.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "diaries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    @Id
    private String id;
    private String username;
    private String avatar;
    private LocalDateTime date;
    private Label label;
    private boolean published;
    private String details;
    private String intro;
}
