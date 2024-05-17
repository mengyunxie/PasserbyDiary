package com.passerby.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "t_avatar")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {
    @Id
    private String avatar_key;
    private String avatar_value;
}
