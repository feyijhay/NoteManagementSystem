package com.example.Note_Management_System.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
@Data
public class CreateNoteResponse {
    private String title;
    private String noteContent;
    private String username;
    @Id
    private String id;

}
