package com.example.Note_Management_System.dtos.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private String username;
    private String password;

    private LocalDateTime localDateTime ;
}
