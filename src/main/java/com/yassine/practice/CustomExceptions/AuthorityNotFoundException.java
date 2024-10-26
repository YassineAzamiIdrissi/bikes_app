package com.yassine.practice.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class AuthorityNotFoundException extends EntityNotFoundException {
    public AuthorityNotFoundException(String message) {
        super(message);
    }
}
