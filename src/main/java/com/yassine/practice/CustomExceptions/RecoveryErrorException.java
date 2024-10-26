package com.yassine.practice.CustomExceptions;

public class RecoveryErrorException extends RuntimeException {
    public RecoveryErrorException(String message) {
        super(message);
    }
}
