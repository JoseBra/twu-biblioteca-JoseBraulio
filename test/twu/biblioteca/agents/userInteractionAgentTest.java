package twu.biblioteca.agents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void landUserOnMainMenuWithoutAvailableCommands(){
        userInteractionAgent.showWelcomeMessage();
        userInteractionAgent.showMainMenu();
        assertEquals("Welcome to the new brand Biblioteca System!\n" +
                        "Here is a list of all the available commands:", outputStream.toString().trim());
    }
}
