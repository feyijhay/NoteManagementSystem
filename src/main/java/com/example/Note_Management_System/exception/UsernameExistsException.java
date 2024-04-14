package com.example.Note_Management_System.exception;

public class UsernameExistsException extends NoteManagementException{
    public UsernameExistsException(String statement){
        super(statement);
    }
}
