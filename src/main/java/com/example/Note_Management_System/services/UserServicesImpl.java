package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.model.Note;
import com.example.Note_Management_System.data.model.User;
import com.example.Note_Management_System.data.repositories.UserRepository;
import com.example.Note_Management_System.dtos.request.*;
import com.example.Note_Management_System.dtos.response.*;
import com.example.Note_Management_System.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteServices noteServices;





    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
     if(userRepository.existsByUsername(registerRequest.getUsername())) throw new UsernameExistsException("Username already taken!");
    User user = new User();
    user.setFirstName(registerRequest.getFirstName());
    user.setLastName(registerRequest.getLastName());
    user.setUsername(registerRequest.getUsername());
    user.setPassword(registerRequest.getPassword());
    User savedUser =userRepository.save(user);
    RegisterResponse registerResponse = new RegisterResponse();
    registerResponse.setFirstName(savedUser.getFirstName());
    registerResponse.setLastName(savedUser.getLastName());
    registerResponse.setUsername(savedUser.getUsername());
    registerResponse.setPassword(savedUser.getPassword());
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
       User user = userRepository.findByUsername(loginRequest.getUsername());
       if (user == null)throw new UserNotFoundException("This user name does not exist");
       if (user.getPassword().equals(loginRequest.getPassword()))user.setLoggedIn(true);
       if(!user.getPassword().equals(loginRequest.getPassword())) throw new NoteManagementException("Input correct password");
       if(!user.getUsername().equals(loginRequest.getUsername())) throw new NoteManagementException("You need to register to be logged in!");


       user.setUsername(loginRequest.getUsername());
       user.setPassword(loginRequest.getPassword());
       user.setLoggedIn(true);


       LoginResponse loginResponse = new  LoginResponse();
       loginResponse.setUsername(user.getUsername());
       loginResponse.setPassword(user.getPassword());
       loginResponse.setLocalDateTime(loginResponse.getLocalDateTime());

       userRepository.save(user);
       return  loginResponse;

    }

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        if(createNoteRequest.getTitle() == null) throw new TitleCannotBeEmptyException("Create title!");
        if (createNoteRequest.getTitle().isBlank())throw new TitleCannotBeEmptyException("Create title!");
        if (createNoteRequest.getTitle().isEmpty()) throw new TitleCannotBeEmptyException("Create title!");
        Note note = noteServices.createNote(createNoteRequest);

        User user = userRepository.findByUsername(createNoteRequest.getUsername());
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setUsername(user.getUsername());
        createNoteResponse.setTitle(note.getTitle());
        createNoteResponse.setNoteContent(note.getNoteContent());
//        if(createNoteRequest.getTitle() == null) throw new TitleCannotBeEmptyException("Create title!");
        List <Note> notes = user.getNotes();
        notes.add(note);
        user.setNotes(notes);
        userRepository.save(user);


        return createNoteResponse;
    }

    @Override
    public FindNoteResponse findNote(String title) {
        Note note = noteServices.findNote(title);
        FindNoteResponse findNoteResponse = new FindNoteResponse();
        findNoteResponse.setTitle(note.getTitle());
        findNoteResponse.setNoteContent(note.getNoteContent());
        findNoteResponse.setLocalDateTime(note.getDateCreated());
//        if(!note.equals(findNote(title))) throw new NoteNotFoundForThisTitleException("Note not found!");
        return  findNoteResponse;
    }

    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest) {
        Note note = noteServices.updateNote(updateNoteRequest);
        UpdateNoteResponse updateNoteResponse = new UpdateNoteResponse();
        updateNoteResponse.setNoteContent(note.getNoteContent());
        updateNoteResponse.setTitle(note.getTitle());
        updateNoteResponse.setLocalDateTime(note.getDateCreated());
        return updateNoteResponse ;
    }

    @Override
    public ViewNoteResponse viewNote(ViewNoteRequest viewNoteRequest) {
        Note note =  noteServices.viewNote(viewNoteRequest);
        ViewNoteResponse viewNoteResponse = new ViewNoteResponse();
        viewNoteResponse.setTitle(note.getTitle());
        viewNoteResponse.setNoteContent(note.getNoteContent());



        return viewNoteResponse;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest deleteNoteRequest) {
        User user = userRepository.findByUsername(deleteNoteRequest.getUsername());

        Note note = noteServices.findNote(deleteNoteRequest.getTitle());
        DeleteNoteResponse deleteNoteResponse = new DeleteNoteResponse();
        deleteNoteResponse.setTitle(note.getTitle());
        deleteNoteResponse.setUsername(note.getUsername());
        List<Note> notes = user.getNotes();
        notes.remove(note);
        userRepository.save(user);
        noteServices.deleteNote(deleteNoteRequest);

        return deleteNoteResponse;
    }

    @Override
    public long getNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        User user = userRepository.findByUsername(logoutRequest.getUsername());
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setUsername(user.getUsername());
        logoutResponse.setPassword(user.getPassword());
        user.setLoggedIn(false);
        userRepository.save(user);

        return logoutResponse;
    }

    @Override
    public boolean loggedIn(String username) {
        return false;
    }

    @Override
    public int getNumberOfNotesFor(String username) {
        User user = userRepository.findByUsername(username);
        int numberOfNotes = user.getNotes().size();
        return numberOfNotes;
    }

    @Override
    public long getTotalNumberOfNotes() {
        return noteServices.getTotalNumberOfNotes();
    }

    @Override
    public List<Note> findNotesBYTitle(String title) {
        return noteServices.findNotesByTitle(title);
    }
}
