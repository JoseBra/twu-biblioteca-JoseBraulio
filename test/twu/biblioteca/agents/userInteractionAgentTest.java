package twu.biblioteca.agents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class userInteractionAgentTest {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
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
    public void listAllLibraryBooksToUser() throws Exception {
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        userInteractionAgent.setChosenLibrary(new Library("LibraryMock", bookLibraryList));
        userInteractionAgent.listAllAvailableBooks();
        assertEquals("1     1984                 George Orwell        1949\n" +
                     "2     Animal Farm          George Orwell        1945", outputStream.toString().trim());
    }
}
