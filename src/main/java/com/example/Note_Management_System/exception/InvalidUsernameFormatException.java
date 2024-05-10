package com.example.Note_Management_System.exception;

public class InvalidUsernameFormatException extends NoteManagementException{
    public InvalidUsernameFormatException(String statement) {
        super(statement);
    }
}
