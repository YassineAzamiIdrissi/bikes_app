package com.yassine.practice.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class RecoveryCodeNotFoundException extends EntityNotFoundException {
    public RecoveryCodeNotFoundException(String message) {
        super(message);
    }
}
