package com.example.Note_Management_System.dtos.request;

import lombok.Data;

@Data
public class UnShareNoteRequest {

    private String senderUsername;
    private String receiverUsername;
    private String id;

}
