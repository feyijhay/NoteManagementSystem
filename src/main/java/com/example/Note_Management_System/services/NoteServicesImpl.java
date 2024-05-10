package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.data.repositories.NoteRepository;
import com.example.Note_Management_System.dtos.request.*;
import com.example.Note_Management_System.exception.NoteNotFoundExecption;
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
    public Note findNote(String id) {
        Note note = noteRepository.findNoteById(id);
        if (note == null )throw new NoteNotFoundForThisTitleException("this note cannot be found");
        return  note;

    }

    @Override
    public Note updateNote(UpdateNoteRequest updateNoteRequest) {
        Note note = noteRepository.findNoteById(updateNoteRequest.getId());
        if (note == null) {
            throw new NoteNotFoundExecption("Note not Found");
        }
        note.setUsername(updateNoteRequest.getUsername());
        if(updateNoteRequest.getTitle() != null){
            note.setTitle(updateNoteRequest.getTitle());
        }
        if(updateNoteRequest.getNoteContent() != null){
            note.setNoteContent(updateNoteRequest.getNoteContent());
        }
        return noteRepository.save(note);

    }

    @Override
    public Note viewNote(String id) {
        return noteRepository.findNoteById(id);
    }

    @Override
    public Note shareNote(ShareNoteRequest shareNoteRequest) {
        Note note = noteRepository.findNoteById(shareNoteRequest.getId());
        return note;
    }
    @Override
    public Note unshareNote(UnShareNoteRequest unshareNoteRequest) {
        Note note = noteRepository.findNoteById(unshareNoteRequest.getId());
        return note;
    }
    @Override
    public Note deleteNote(DeleteNoteRequest deleteNoteRequest) {
        Note note = noteRepository.findNoteById(deleteNoteRequest.getId());
         noteRepository.delete(note);
         return note;
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
