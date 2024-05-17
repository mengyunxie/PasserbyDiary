package com.passerby.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String label_key;
    private String label_color;
    private String label_type;

}
