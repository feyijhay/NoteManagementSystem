package com.example.Note_Management_System.dtos.response;

import lombok.Data;

@Data
public class ShareNoteResponse {
    private String senderUsername ;
    private String receiverUsername;
    private String id;
    private String message;
}
