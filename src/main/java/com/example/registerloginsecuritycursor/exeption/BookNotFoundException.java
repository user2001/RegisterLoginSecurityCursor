package com.example.registerloginsecuritycursor.exeption;

public class BookNotFoundException extends  RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
