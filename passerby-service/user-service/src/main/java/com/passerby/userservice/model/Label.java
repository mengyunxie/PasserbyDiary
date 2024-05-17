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

    @Column(name = "`key`", nullable = false)
    private String key;

    private String color;
    private String type;

}
