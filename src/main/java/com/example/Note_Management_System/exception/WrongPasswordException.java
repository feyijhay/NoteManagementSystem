package com.example.Note_Management_System.exception;

public class WrongPasswordException extends NoteManagementException{
    public WrongPasswordException(String statement){
        super(statement);
    }
}
