package com.yassine.practice.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class ActivationCodeNotFoundException extends EntityNotFoundException {
    public ActivationCodeNotFoundException(String message) {
        super(message);
    }
}
