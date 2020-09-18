package com.example.HANDIPRO.exceptions;

public class IllegalFilePathException extends Exception{
    public IllegalFilePathException(String filename){
        super("Illegal file path: "+filename);
    }
}
