package com.example.Note_Management_System.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean isSuccessful;
    private Object result;

}
