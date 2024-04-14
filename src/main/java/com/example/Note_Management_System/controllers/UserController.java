package com.example.Note_Management_System.controllers;

import com.example.Note_Management_System.dtos.request.*;
import com.example.Note_Management_System.dtos.response.*;
import com.example.Note_Management_System.exception.NoteManagementException;
import com.example.Note_Management_System.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            RegisterResponse result = userServices.register(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse result = userServices.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest){
        try {
            LogoutResponse result = userServices.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest createNoteRequest){
        try {
            CreateNoteResponse result = userServices.createNote(createNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateNoteRequest updateNoteRequest){
        try {
            UpdateNoteResponse result = userServices.updateNote(updateNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody DeleteNoteRequest deleteNoteRequest){
        try {
            DeleteNoteResponse result = userServices.deleteNote(deleteNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/view")
    public ResponseEntity<?> view(@RequestBody ViewNoteRequest viewNoteRequest){
        try {
            ViewNoteResponse result = userServices.viewNote(viewNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> find(@RequestBody String title){
        try {
            FindNoteResponse result = userServices.findNote(title);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED );
        }
        catch (NoteManagementException e){
            return new ResponseEntity<>(new ApiResponse(false,  e.getMessage()), BAD_REQUEST);
        }
    }

}
