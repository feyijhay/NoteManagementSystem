package com.example.Note_Management_System.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class CreateNoteRequest {
    private String title;
    private String noteContent;
    private String username;



}
