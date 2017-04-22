package twu.biblioteca.agents;

import org.junit.Test;
import twu.biblioteca.commands.CheckOutLibraryItemCommand;
import twu.biblioteca.commands.ListLibraryItemCommand;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class consoleInputParserTest {
    @Test
    public void detectNullWhenParsingCommandNotValid() throws Exception {
        Map.Entry<Class, Class> enumCommandClass = ConsoleInputParser.getCommandAndTargetClassFromString("nonExistingCommand");
        assertEquals(null, enumCommandClass);
    }

    @Test
    public void detectListBooksCommandFromInput() throws Exception {
        Map.Entry<Class, Class> enumCommandClass = ConsoleInputParser.getCommandAndTargetClassFromString("/listBooks");
        assertEquals(ListLibraryItemCommand.class, enumCommandClass.getKey());
    }

    @Test
    public void detectCheckOutBookFromInput() throws Exception {
        Map.Entry<Class, Class> enumCommandClass = ConsoleInputParser.getCommandAndTargetClassFromString("/checkoutBook");
        assertEquals(CheckOutLibraryItemCommand.class, enumCommandClass.getKey());
    }

    @Test
    public void detectListMoviesFromInput() throws Exception {
        Map.Entry<Class, Class> enumCommandClass = ConsoleInputParser.getCommandAndTargetClassFromString("/listMovies");
        assertEquals(ListLibraryItemCommand.class, enumCommandClass.getKey());
    }

    @Test
    public void detectCheckOutMovieFromInput() throws Exception {
        Map.Entry<Class, Class> enumCommandClass = ConsoleInputParser.getCommandAndTargetClassFromString("/checkoutMovie");
        assertEquals(CheckOutLibraryItemCommand.class, enumCommandClass.getKey());
    }
}
