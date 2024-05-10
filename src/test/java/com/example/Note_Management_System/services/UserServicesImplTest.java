package com.example.Note_Management_System.services;

import com.example.Note_Management_System.data.repositories.NoteRepository;
import com.example.Note_Management_System.data.repositories.UserRepository;
import com.example.Note_Management_System.dtos.request.*;
import com.example.Note_Management_System.dtos.response.CreateNoteResponse;
import com.example.Note_Management_System.dtos.response.ViewNoteResponse;
import com.example.Note_Management_System.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class  UserServicesImplTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;


    @BeforeEach
    public void deleteNote(){
        noteRepository.deleteAll();
    }

    public void register(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Jumoke");
        registerRequest.setLastName("Joseph");
        registerRequest.setUsername("Jhay");
        registerRequest.setPassword("Jummy");
        userServices.register(registerRequest);
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Jhay");
        loginRequest.setPassword("Jummy");
        userServices.login(loginRequest);
    }

    public void createNote(){
        CreateNoteRequest createNoteRequest =new CreateNoteRequest();
        createNoteRequest.setUsername("Jhay");
        createNoteRequest.setTitle("Today's note");
        createNoteRequest.setNoteContent("Today is such a great day!");
        userServices.createNote(createNoteRequest);

    }


    @Test
    public void testThatUserCanRegister(){
        userRepository.deleteAll();
        register();


        assertEquals(1, userServices.getNumberOfUser());


    }

    @Test
    public void testThat2UserCanRegister(){
        userRepository.deleteAll();
        register();

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Ayinla");
        registerRequest1.setLastName("Temitope");
        registerRequest1.setUsername("Temi");
        registerRequest1.setPassword("Temmy");
        userServices.register(registerRequest1);

        assertEquals(2, userServices.getNumberOfUser());
    }

    @Test
    public void testThatUserCanLogin(){
        userRepository.deleteAll();
        register();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Jhay");
        loginRequest.setPassword("Jummy");
        userServices.login(loginRequest);

        assertEquals(1, userServices.getNumberOfUser());

    }


    @Test
    public void testThatUserCanLogout(){
        userRepository.deleteAll();
       register();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Jhay");
        loginRequest.setPassword("Jummy");
        userServices.login(loginRequest);
        assertEquals(1, userServices.getNumberOfUser());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("Jhay");
        userServices.logout(logoutRequest);

        assertFalse(userServices.loggedIn("Jhay"));

    }

    @Test
    public void testThatCreateNote(){
        userRepository.deleteAll();
       register();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Jhay");
        loginRequest.setPassword("Jummy");
        userServices.login(loginRequest);
        assertEquals(1, userServices.getNumberOfUser());

        CreateNoteRequest createNoteRequest =new CreateNoteRequest();
        createNoteRequest.setUsername("Jhay");
        createNoteRequest.setTitle("Today's note");
        createNoteRequest.setNoteContent("Today is such a great day");
        userServices.createNote(createNoteRequest);
        assertEquals(1, userServices.getNumberOfNotesFor("Jhay"));

    }

   @Test
   public void testThatUserCanDeleteNote(){
       userRepository.deleteAll();

       register();

       RegisterRequest registerRequest1 = new RegisterRequest();
       registerRequest1.setFirstName("Ayinla");
       registerRequest1.setLastName("Dayo");
       registerRequest1.setUsername("Moh");
       registerRequest1.setPassword("Tope");
       userServices.register(registerRequest1);


        login();

       LoginRequest loginRequest1 = new LoginRequest();
       loginRequest1.setUsername("Moh");
       loginRequest1.setPassword("Tope");
       userServices.login(loginRequest1);
       assertEquals(2, userServices.getNumberOfUser());


       CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
       createNoteRequest1.setUsername("Moh");
       createNoteRequest1.setTitle("Dayo And Beejay");
       createNoteRequest1.setNoteContent("Woooosh");
       userServices.createNote(createNoteRequest1);


       CreateNoteRequest createNoteRequest =new CreateNoteRequest();
       createNoteRequest.setUsername("Jhay");
       createNoteRequest.setTitle("Today's note");
       createNoteRequest.setNoteContent("Today is such a great day!");
       CreateNoteResponse response = userServices.createNote(createNoteRequest);
       assertEquals(2, userServices.getTotalNumberOfNotes());

       DeleteNoteRequest deleteNoteRequest = new DeleteNoteRequest();
       deleteNoteRequest.setUsername("Jhay");
       deleteNoteRequest.setTitle("Today's note");
       deleteNoteRequest.setId(response.getId());
       userServices.deleteNote(deleteNoteRequest);

       assertEquals(1L, userServices.getTotalNumberOfNotes());
       assertEquals(0, userServices.getNumberOfNotesFor("Jhay"));

   }

   @Test
    public void testThatUserCanUpdateNote(){
        userRepository.deleteAll();
       RegisterRequest registerRequest1 = new RegisterRequest();
       registerRequest1.setFirstName("Ayinla");
       registerRequest1.setLastName("Dayo");
       registerRequest1.setUsername("Moh");
       registerRequest1.setPassword("Tope");
       userServices.register(registerRequest1);

       LoginRequest loginRequest1 = new LoginRequest();
       loginRequest1.setUsername("Moh");
       loginRequest1.setPassword("Tope");
       userServices.login(loginRequest1);
       assertEquals(1, userServices.getNumberOfUser());

       CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
       createNoteRequest1.setUsername("Moh");
       createNoteRequest1.setTitle("Dayo And Beejay");
       createNoteRequest1.setNoteContent("Woooosh");
       CreateNoteResponse response = userServices.createNote(createNoteRequest1);
       assertEquals(1, userServices.getTotalNumberOfNotes());


       UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
       updateNoteRequest.setUsername("Moh");
       updateNoteRequest.setTitle("Tired");
       updateNoteRequest.setNoteContent("Hmmmmmm");
       updateNoteRequest.setId(response.getId());
       userServices.updateNote(updateNoteRequest);
       assertEquals(1, userServices.getNumberOfNotesFor("Moh"));
   }

   @Test
    public void testThatUserCanFindAndViewNote(){
       userRepository.deleteAll();

       register();

       login();

       CreateNoteRequest createNoteRequest = new CreateNoteRequest();
       createNoteRequest.setUsername("Jhay");
       createNoteRequest.setTitle("Today's note");
       createNoteRequest.setNoteContent("Today is such a great day!");
       CreateNoteResponse response = userServices.createNote(createNoteRequest);
       assertEquals(1, userServices.getNumberOfUser());

//       userServices.findNote(response.getId());
       ViewNoteResponse viewNoteResponse = userServices.viewNote(response.getId());
       assertEquals("Today's note", viewNoteResponse.getTitle());
       assertEquals("Today is such a great day!", viewNoteResponse.getNoteContent());

   }

   @Test
    public void testThatUserCannotLoginWithWrongPassword_ThrowsAnException(){
        userRepository.deleteAll();

       register();

       LoginRequest loginRequest = new LoginRequest();
       loginRequest.setUsername("Jhay");
       loginRequest.setPassword("Tope11");

       assertThrows(NoteManagementException.class, ()->userServices.login(loginRequest));

   }


   @Test
    public void testThatUserCannotLoginWithWrongUsername_ThrowsAnException(){
        userRepository.deleteAll();
        register();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dee");
        System.out.println(loginRequest.getUsername());
        loginRequest.setPassword("Jummy");
       System.out.println(loginRequest.getPassword());

       assertThrows(NoteManagementException.class, ()->userServices.login(loginRequest));
    }


    @Test
    public void testThatUserCannotCreateNoteWithoutATitle_ThrowsAnException(){
        userRepository.deleteAll();
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Ayinla");
        registerRequest1.setLastName("Dayo");
        registerRequest1.setUsername("Moh");
        registerRequest1.setPassword("Tope");
        userServices.register(registerRequest1);

        LoginRequest loginRequest1 = new LoginRequest();
        loginRequest1.setUsername("Moh");
        loginRequest1.setPassword("Tope");
        userServices.login(loginRequest1);

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("Moh");
        createNoteRequest1.setNoteContent("Woooosh");
        assertThrows(TitleCannotBeEmptyException.class, ()->userServices.createNote(createNoteRequest1));

    }

    @Test
    public void testThatUserCannotLoginWithoutRegistering_ThrowsAnException(){
        userRepository.deleteAll();
        LoginRequest loginRequest1 = new LoginRequest();
        loginRequest1.setUsername("Feyi");
        loginRequest1.setPassword("1111");
        assertThrows(NoteManagementException.class, ()->userServices.login(loginRequest1));

    }

    @Test
    public void testThat2UsersCannotHaveTheSameUsername_ThrowsAnException(){
        userRepository.deleteAll();

        register();

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Ayinla");
        registerRequest1.setLastName("Dayo");
        registerRequest1.setUsername("Jhay");
        registerRequest1.setPassword("Tope");

        assertThrows(UsernameExistsException.class, ()->userServices.register(registerRequest1));

    }

    @Test
    public void testThatUserCanShareNoteToAnotherUser(){
        userRepository.deleteAll();

        register();

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Ayinla");
        registerRequest1.setLastName("Dayo");
        registerRequest1.setUsername("Jumoke");
        registerRequest1.setPassword("Tope");
        userServices.register(registerRequest1);

        login();

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("Jhay");
        createNoteRequest1.setTitle("Today's note");
        createNoteRequest1.setNoteContent("Today's class was an interesting one...we had a conversation about what men wants with Mr Femi!");
        CreateNoteResponse  createNoteResponse= userServices.createNote(createNoteRequest1);
        assertEquals(1, userServices.getNumberOfNotesFor("Jhay"));

        ShareNoteRequest shareNoteRequest = new ShareNoteRequest();
        shareNoteRequest.setSenderUsername("Jhay");
        shareNoteRequest.setId(createNoteResponse.getId());


        shareNoteRequest.setReceiverUsername("Jumoke");
        userServices.shareNote(shareNoteRequest);


        assertEquals(1, userServices.getNumberOfNotesFor("Jumoke"));

    }

    @Test
    public void testThatUserCanUnShareNoteSharedToAnotherUser(){
        userRepository.deleteAll();

        register();

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstName("Ayinla");
        registerRequest1.setLastName("Dayo");
        registerRequest1.setUsername("Jumoke");
        registerRequest1.setPassword("Tope");
        userServices.register(registerRequest1);

        login();

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("Jhay");
        createNoteRequest1.setTitle("Today's note");
        createNoteRequest1.setNoteContent("Today's class was an interesting one...we had a conversation about what men wants with Mr Femi!");
        CreateNoteResponse  createNoteResponse= userServices.createNote(createNoteRequest1);
        assertEquals(1, userServices.getNumberOfNotesFor("Jhay"));


        ShareNoteRequest shareNoteRequest = new ShareNoteRequest();
        shareNoteRequest.setSenderUsername("Jhay");
        shareNoteRequest.setId(createNoteResponse.getId());
        shareNoteRequest.setReceiverUsername("Jumoke");
        userServices.shareNote(shareNoteRequest);

        UnShareNoteRequest unshareNoteRequest = new UnShareNoteRequest();
        unshareNoteRequest.setSenderUsername("Jhay");
        unshareNoteRequest.setReceiverUsername("Jumoke");
        unshareNoteRequest.setId(createNoteResponse.getId());
        userServices.unShareNote(unshareNoteRequest);

        assertEquals(0, userServices.getNumberOfNotesFor("Jumoke"));

    }


    @Test
    public void testThatUserHasToBeLoggedInToCreateNote_ThrowsAnException(){
        register();


        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("Jhay");
        createNoteRequest.setTitle("Today's note");
        createNoteRequest.setNoteContent("Today's class was an interesting one...we had a conversation about what men wants with Mr Femi!");
        assertThrows(LoginRequiredException.class, ()-> userServices.createNote(createNoteRequest));

    }
}