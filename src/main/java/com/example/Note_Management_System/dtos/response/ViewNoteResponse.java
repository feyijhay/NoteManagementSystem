package com.example.Note_Management_System.dtos.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
@Data
public class ViewNoteResponse {
    private String id;
    private String title;
    private String noteContent;

}
