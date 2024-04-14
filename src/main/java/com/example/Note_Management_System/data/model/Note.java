package com.example.Note_Management_System.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Note {
    private String username;
    private String password;
    private String title;
    private String noteContent;
    @Id
    private String id;
    private LocalDateTime dateCreated ;

}
