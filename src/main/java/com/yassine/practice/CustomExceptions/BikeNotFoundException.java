package com.yassine.practice.CustomExceptions;

import jakarta.persistence.EntityNotFoundException;

public class BikeNotFoundException extends EntityNotFoundException {
    public BikeNotFoundException(String message)
    {
        super(message);
    }
}
