package com.yassine.practice.ActivationCode;

import com.yassine.practice.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivationCode {
    @Id
    @GeneratedValue
    private Integer id;
    private String activationCode;
    private LocalDateTime createdAt;
    private LocalDateTime validatedAt;
    private LocalDateTime expiresAt;
    // linking attributes :
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
