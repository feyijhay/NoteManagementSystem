package com.example.Note_Management_System.exception;

public class NoteManagementException extends RuntimeException{
    public NoteManagementException(String statement){
        super(statement);
    }
}
