package com.passerby.userservice.dto;

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
    private Long id;
    private String username;
    private String avatar;
    private String date;
    private String label;
    private boolean isPasserby;
    private String details;
    private String intro;

}
