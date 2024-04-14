package com.example.Note_Management_System.exception;

public class NoteNotFoundForThisTitleException extends NoteManagementException{
    public NoteNotFoundForThisTitleException(String statement){
        super(statement);
    }
}
