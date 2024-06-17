package com.passerby.diaryservice.dto;

import com.passerby.diaryservice.model.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {
    private String id;
    private String username;
    private String avatar;
    private LocalDateTime date;
    private Label label;
    private boolean published;
    private String details;
    private String intro;
    // Optionally, you can add a method to set intro automatically when details are set
    public void setDetails(String details) {
        this.details = details;
        this.intro = details != null && details.length() > 50 ? details.substring(0, 50) : details;
    }
}
