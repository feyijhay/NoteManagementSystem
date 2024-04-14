package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.dtos.request.CreateNoteRequest;
import com.example.Note_Management_System.dtos.request.DeleteNoteRequest;
import com.example.Note_Management_System.dtos.request.UpdateNoteRequest;
import com.example.Note_Management_System.dtos.request.ViewNoteRequest;

import java.util.List;

public interface NoteServices {

    Note createNote(CreateNoteRequest createNoteRequest);

    Note findNote(String title);

    Note updateNote(UpdateNoteRequest updateNoteRequest);

    Note viewNote(String title);

    void deleteNote(DeleteNoteRequest deleteNoteRequest);

    long getTotalNumberOfNotes();

    List<Note> findNotesByTitle(String title);
}
