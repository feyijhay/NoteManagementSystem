package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.data.model.User;
import com.example.Note_Management_System.dtos.request.*;
import com.example.Note_Management_System.dtos.response.*;

import java.util.List;

public interface UserServices {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);

   ShareNoteResponse shareNote(ShareNoteRequest shareNoteRequest);

    FindNoteResponse findNote(String id);

    User findUser(String username);

    UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest);

    ViewNoteResponse viewNote(String id);

    DeleteNoteResponse deleteNote(DeleteNoteRequest deleteNoteRequest);
    long getNumberOfUser();

    LogoutResponse logout(LogoutRequest logoutRequest);

    boolean loggedIn(String username);

    int getNumberOfNotesFor(String username);

    long getTotalNumberOfNotes();
    List<Note> findNotesBYTitle(String title);

    UnShareNoteResponse unShareNote(UnShareNoteRequest unshareNoteRequest);
}