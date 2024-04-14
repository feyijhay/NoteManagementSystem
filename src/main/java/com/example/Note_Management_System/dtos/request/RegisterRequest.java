package com.example.Note_Management_System.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
