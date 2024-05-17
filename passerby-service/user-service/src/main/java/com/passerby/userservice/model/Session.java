package com.passerby.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_session")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    private String sid;

    private String username;
}
