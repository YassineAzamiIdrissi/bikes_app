package com.yassine.practice.ResetPassword;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordCodeRepository extends JpaRepository
        <ResetPasswordCode, Integer> {
    Optional<ResetPasswordCode> findByCode(String code);
}
