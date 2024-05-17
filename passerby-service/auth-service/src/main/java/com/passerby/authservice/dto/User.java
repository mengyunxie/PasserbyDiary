package com.passerby.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;

    private String avatar;

    private List<String> avatars;

    private List<String> labels;

    public User(String username) {
    }
}
