package twu.biblioteca.agents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.commands.Command;
import twu.biblioteca.commands.ListBookCommand;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class userInteractionAgentTest {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    InputStream inputStream = System.in;

    UserInteractionAgent userInteractionAgent = new UserInteractionAgent();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void showWelcomeMessageToUser() throws Exception {
        userInteractionAgent.showWelcomeMessage();
        assertEquals("Welcome to the new brand Biblioteca System!", outputStream.toString().trim());
    }

    @Test
    public void landUserOnMainMenuWithoutAvailableCommands(){
        userInteractionAgent.showWelcomeMessage();
        userInteractionAgent.showMainMenu();
        assertEquals("Welcome to the new brand Biblioteca System!\n" +
                        "Here is a list of all the available commands:", outputStream.toString().trim());
    }

    @Test
    public void landUserOnMainMenuWithListBookCommand() throws Exception {
        List<Command> availableCommands = new ArrayList<Command>();
        availableCommands.add(new ListBookCommand());
        userInteractionAgent.setAvailableCommands(availableCommands);
        userInteractionAgent.showWelcomeMessage();
        userInteractionAgent.showMainMenu();
        assertEquals("Welcome to the new brand Biblioteca System!\n" +
                "Here is a list of all the available commands:\n" +
                "\t/listBooks  -  List all available books", outputStream.toString().trim());
    }

    @Test
    public void detectUserInputIncorrectCommand() throws Exception {
        String inputString = "none";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        userInteractionAgent.awaitUserInput();
        assertTrue(outputStream.toString().contains("Select a valid option!"));
    }

    @Test
    public void detectUserInputCorrectListBookCommand() throws Exception{
        List<Command> availableCommands = new ArrayList<Command>();
        availableCommands.add(new ListBookCommand());
        userInteractionAgent.setAvailableCommands(availableCommands);

        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        userInteractionAgent.setChosenLibrary(new Library("TestLibrary", bookLibraryList));

        String inputString = "/listBooks";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        userInteractionAgent.awaitUserInput();

        assertEquals("1     1984                 George Orwell        1949\n" +
                     "2     Animal Farm          George Orwell        1945", outputStream.toString().trim());
    }
}
