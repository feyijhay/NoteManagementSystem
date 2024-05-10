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
     if(registerRequest.getUsername().isBlank() || registerRequest.getUsername().isEmpty())throw new InvalidUsernameFormatException("Invalid format!!!");
     if(registerRequest.getFirstName().isBlank() || registerRequest.getFirstName().isEmpty())throw new InvalidNameFormatException("Wrong name format!!!");
     if(registerRequest.getLastName().isBlank() || registerRequest.getLastName().isEmpty())throw new InvalidNameFormatException("Wrong name format!!");
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
       if(!user.getUsername().equals(loginRequest.getUsername())) throw new NoteManagementException("Username or password is not correct");


       user.setUsername(loginRequest.getUsername());
       user.setPassword(loginRequest.getPassword());
       user.setLoggedIn(true);


       LoginResponse loginResponse = new  LoginResponse();
       loginResponse.setUsername(user.getUsername());
       loginResponse.setMessage("Login successful");


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
        if(user == null) throw new UserNotFoundException("No user found for this username!");
        if(!user.isLoggedIn()) throw new LoginRequiredException("Please login to continue");
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setUsername(user.getUsername());
        createNoteResponse.setTitle(note.getTitle());
        createNoteResponse.setNoteContent(note.getNoteContent());
        createNoteResponse.setId(note.getId());
//        if(createNoteRequest.getTitle() == null) throw new TitleCannotBeEmptyException("Create title!");
        List <Note> notes = user.getNotes();
        notes.add(note);
        user.setNotes(notes);
        userRepository.save(user);


        return createNoteResponse;
    }


    @Override
    public ShareNoteResponse shareNote(ShareNoteRequest shareNoteRequest) {
        User sender = userRepository.findByUsername(shareNoteRequest.getSenderUsername());
        User receiver = userRepository.findByUsername(shareNoteRequest.getReceiverUsername());
        if(receiver == null )throw new UserNotFoundException("Receiver user not found");
        if (sender == null)throw new UserNotFoundException("Sender user not found");


        Note note = noteServices.shareNote(shareNoteRequest);
        if(note == null)throw new NoteNotFoundExecption("No note found!");
        List<Note> receiverNotes = receiver.getNotes();
        receiverNotes.add(note);
        userRepository.save(receiver);

        ShareNoteResponse shareNoteResponse = new ShareNoteResponse();
        shareNoteResponse.setSenderUsername(sender.getUsername());
        shareNoteResponse.setReceiverUsername(receiver.getUsername());
        shareNoteResponse.setId(note.getId());
        shareNoteResponse.setMessage("Note shared successfully!!!");

        return shareNoteResponse;
    }

    @Override
    public UnShareNoteResponse unShareNote(UnShareNoteRequest unshareNoteRequest) {
        User sender = userRepository.findByUsername(unshareNoteRequest.getSenderUsername());
        User receiver =  userRepository.findByUsername(unshareNoteRequest.getReceiverUsername());
        if(sender == null)throw new UserNotFoundException("Sender not found");
        if (receiver == null) {
            throw new UserNotFoundException("Receiver not found");
        }

        Note note = noteServices.unshareNote(unshareNoteRequest);
        if (note == null) {
            throw new NoteNotFoundExecption("Note not found ");
        }
        List<Note> receiverNotes = receiver.getNotes();
        receiverNotes.remove(note);
        userRepository.save(receiver);

        UnShareNoteResponse unshareNoteResponse = new UnShareNoteResponse();
        unshareNoteResponse.setSenderUsername(sender.getUsername());
        unshareNoteResponse.setReceiverUsername(receiver.getUsername());
        unshareNoteResponse.setId(note.getId());
        unshareNoteResponse.setMessage("Note unshared successfully!");

        return unshareNoteResponse;
    }


    @Override
    public FindNoteResponse findNote(String id) {
        Note note = noteServices.findNote(id);
        FindNoteResponse findNoteResponse = new FindNoteResponse();
        findNoteResponse.setId(note.getId());
        findNoteResponse.setNoteContent(note.getNoteContent());
        return  findNoteResponse;
    }

    @Override
    public User findUser(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest) {
        Note note = noteServices.updateNote(updateNoteRequest);
        UpdateNoteResponse updateNoteResponse = new UpdateNoteResponse();
        updateNoteResponse.setUsername(note.getUsername());
        updateNoteResponse.setNoteContent(note.getNoteContent());
        updateNoteResponse.setTitle(note.getTitle());

        return updateNoteResponse ;
    }

    @Override
    public ViewNoteResponse viewNote(String id) {
        Note note =  noteServices.viewNote(id);
        if (note == null) {
            throw new NoteNotFoundExecption("Note does not exist");
        }
        ViewNoteResponse viewNoteResponse = new ViewNoteResponse();
        viewNoteResponse.setId(note.getId());
        viewNoteResponse.setTitle(note.getTitle());
        viewNoteResponse.setNoteContent(note.getNoteContent());


        return viewNoteResponse;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest deleteNoteRequest) {
        User user = userRepository.findByUsername(deleteNoteRequest.getUsername());
        Note note = noteServices.findNote(deleteNoteRequest.getId());

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
