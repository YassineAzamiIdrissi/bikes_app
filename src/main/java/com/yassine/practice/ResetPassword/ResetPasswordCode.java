package com.yassine.practice.ResetPassword;

import com.yassine.practice.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResetPasswordCode {
    @Id
    @GeneratedValue
    private Integer id;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used;
    // many to one :
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
