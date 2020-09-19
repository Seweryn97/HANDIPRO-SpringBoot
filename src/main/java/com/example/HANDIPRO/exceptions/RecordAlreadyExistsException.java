package com.example.HANDIPRO.exceptions;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException(String message){
        super(message + " already exists");
    }
}
