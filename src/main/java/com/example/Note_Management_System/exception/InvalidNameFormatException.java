package com.example.Note_Management_System.exception;

public class InvalidNameFormatException extends NoteManagementException{
    public InvalidNameFormatException(String statement) {
        super(statement);
    }
}
