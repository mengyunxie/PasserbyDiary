package com.passerby.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diaries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    @Id
    private Long id;
    private String username;
    private String avatar;
    private String date;
    private String label;
    private boolean isPasserby;
    private String details;
    private String intro;

    // Optionally, you can add a method to set intro automatically when details are set
    public void setDetails(String details) {
        this.details = details;
        this.intro = details != null && details.length() > 50 ? details.substring(0, 50) : details;
    }
}
