package com.example.Note_Management_System.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ShareNoteRequest {
    private String senderUsername;
    private String receiverUsername;
    @Id
    private String id;
}
