package twu.biblioteca.agents;

import org.junit.Test;
import twu.biblioteca.commands.ListBookCommand;

import static org.junit.Assert.assertEquals;

public class consoleInputParserTest {
    @Test
    public void detectCommandFromInput() throws Exception {
        Class enumCommandClass = ConsoleInputParser.getCommandClassFromString("/listBooks");
        assertEquals(ListBookCommand.class, enumCommandClass);
    }

    @Test
    public void detectNullWhenParsingComandNotValid() throws Exception {
        Class enumCommandClass = ConsoleInputParser.getCommandClassFromString("nonExistingCommand");
        assertEquals(null, enumCommandClass);
    }
}
