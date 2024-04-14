package com.example.Note_Management_System.dtos.request;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private String username;
    private String title;
    private String noteContent;
}
