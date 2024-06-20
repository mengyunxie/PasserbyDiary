package com.passerby.authservice.dto;

import com.passerby.authservice.model.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private Map<String, String> avatars;
    private Map<String, Label> labels;
}
