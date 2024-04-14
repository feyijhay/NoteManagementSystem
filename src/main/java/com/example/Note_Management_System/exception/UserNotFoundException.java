package com.example.Note_Management_System.exception;

public class UserNotFoundException extends NoteManagementException{
    public UserNotFoundException(String statement){
        super(statement);
    }
}
