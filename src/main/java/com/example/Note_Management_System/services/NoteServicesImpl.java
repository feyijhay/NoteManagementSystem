package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.data.repositories.NoteRepository;
import com.example.Note_Management_System.dtos.request.CreateNoteRequest;
import com.example.Note_Management_System.dtos.request.DeleteNoteRequest;
import com.example.Note_Management_System.dtos.request.UpdateNoteRequest;
import com.example.Note_Management_System.dtos.request.ViewNoteRequest;
import com.example.Note_Management_System.exception.NoteNotFoundForThisTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServicesImpl implements NoteServices{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNote(CreateNoteRequest createNoteRequest) {
        Note note = new Note();
        note.setTitle(createNoteRequest.getTitle());
        note.setNoteContent(createNoteRequest.getNoteContent());
        note.setUsername(createNoteRequest.getUsername());

        return noteRepository.save(note);
    }

    @Override
    public Note findNote(String title) {
        Note note = noteRepository.findNoteByTitle(title);
        if (note == null )throw new NoteNotFoundForThisTitleException("this note cannote be found");
        return  note;

    }

    @Override
    public Note updateNote(UpdateNoteRequest updateNoteRequest) {
        Note note = new Note();
        noteRepository.findNoteByTitle(updateNoteRequest.getTitle());
        note.setUsername(updateNoteRequest.getUsername());
        note.setTitle(updateNoteRequest.getTitle());
        note.setNoteContent(updateNoteRequest.getNoteContent());

        return noteRepository.save(note);

    }

    @Override
    public Note viewNote(ViewNoteRequest viewNoteRequest) {
        return noteRepository.findNoteByTitle(viewNoteRequest.getTitle());
    }

    @Override
    public void deleteNote(DeleteNoteRequest deleteNoteRequest) {
        Note note = noteRepository.findNoteByTitle(deleteNoteRequest.getTitle());
        noteRepository.delete(note);



     }

    @Override
    public long getTotalNumberOfNotes() {
        return noteRepository.count();
    }

    @Override
    public List<Note> findNotesByTitle(String title) {
        return noteRepository.findNotesByTitle(title);
    }
}
