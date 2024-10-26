package com.yassine.practice.CustomExceptions;

public class BikeNotBorrowableException extends RuntimeException {
    public BikeNotBorrowableException(String message) {
        super(message);
    }
}
