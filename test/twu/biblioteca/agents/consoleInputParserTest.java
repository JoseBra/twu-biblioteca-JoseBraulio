package twu.biblioteca.agents;

import org.junit.Test;
import twu.biblioteca.commands.CheckOutLibraryItem;
import twu.biblioteca.commands.ListBookCommand;

import static org.junit.Assert.assertEquals;

public class consoleInputParserTest {
    @Test
    public void detectNullWhenParsingCommandNotValid() throws Exception {
        Class enumCommandClass = ConsoleInputParser.getCommandClassFromString("nonExistingCommand");
        assertEquals(null, enumCommandClass);
    }

    @Test
    public void detectListBooksCommandFromInput() throws Exception {
        Class enumCommandClass = ConsoleInputParser.getCommandClassFromString("/listBooks");
        assertEquals(ListBookCommand.class, enumCommandClass);
    }

    @Test
    public void detectCheckOutBookFromInput() throws Exception {
        Class enumCommandClass = ConsoleInputParser.getCommandClassFromString("/checkout");
        assertEquals(CheckOutLibraryItem.class, enumCommandClass);
    }
}
