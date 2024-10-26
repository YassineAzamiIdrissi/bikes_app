package com.yassine.practice.CustomExceptions;

public class RecoveryCodeExpiredException extends RuntimeException {
    public RecoveryCodeExpiredException(String message) {
        super(message);
    }
}
