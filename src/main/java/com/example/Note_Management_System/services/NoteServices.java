package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.dtos.request.*;

import java.util.List;

public interface NoteServices {

    Note createNote(CreateNoteRequest createNoteRequest);

    Note findNote(String id);

    Note updateNote(UpdateNoteRequest updateNoteRequest);

    Note viewNote(String id);

    Note shareNote(ShareNoteRequest shareNoteRequest);

    Note deleteNote(DeleteNoteRequest deleteNoteRequest);

    long getTotalNumberOfNotes();

    Note unshareNote(UnShareNoteRequest unshareNoteRequest);

    List<Note> findNotesByTitle(String title);
}
