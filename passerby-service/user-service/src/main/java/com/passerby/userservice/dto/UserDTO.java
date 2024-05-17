package com.passerby.userservice.dto;

import com.passerby.userservice.model.Label;
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
    private String username;
    private String avatar;

    private Map<String, String> avatars;
    private Map<String, Label> labels;

}
