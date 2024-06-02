package com.passerby.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_label")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label_key")
    private String labelKey;

    @Column(name = "label_color")
    private String labelColor;

    @Column(name = "label_type")
    private String labelType;

}
