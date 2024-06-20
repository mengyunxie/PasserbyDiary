package com.passerby.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    private String id;
    private String labelKey;
    private String labelColor;
    private String labelType;

}
