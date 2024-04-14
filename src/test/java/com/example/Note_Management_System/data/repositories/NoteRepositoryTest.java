package com.example.Note_Management_System.data.repositories;

import com.example.Note_Management_System.data.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class NoteRepositoryTest {
@Autowired
    private NoteRepository noteRepository;

    @Test
    public void testSave(){
//        noteRepository.deleteAll();
        Note note = new Note();
        noteRepository.save(note);
        assertEquals(1, noteRepository.count());
    }


}