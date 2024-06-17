package com.passerby.diaryservice.model;

import lombok.*;

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
