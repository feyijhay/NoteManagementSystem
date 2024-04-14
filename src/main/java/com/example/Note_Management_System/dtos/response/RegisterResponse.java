package com.example.Note_Management_System.dtos.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private LocalDateTime localDateTime ;
}
