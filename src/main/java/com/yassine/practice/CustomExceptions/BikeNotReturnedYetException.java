package com.yassine.practice.CustomExceptions;

public class BikeNotReturnedYetException extends RuntimeException
{
    public BikeNotReturnedYetException(String message)
    {
        super(message);
    }
}
