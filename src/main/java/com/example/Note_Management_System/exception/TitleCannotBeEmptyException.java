package com.example.Note_Management_System.exception;

public class TitleCannotBeEmptyException extends NoteManagementException{
    public TitleCannotBeEmptyException (String statement){
        super(statement);

    }
}
