package com.example.Note_Management_System.data.repositories;

import com.example.Note_Management_System.data.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    Note findNoteByTitle(String  title);

    Note findNoteById(String id);

    List<Note> findNotesByTitle(String title);


}
