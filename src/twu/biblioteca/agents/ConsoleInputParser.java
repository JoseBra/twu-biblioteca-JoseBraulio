package twu.biblioteca.agents;

import twu.biblioteca.commands.*;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Movie;

import java.util.AbstractMap;
import java.util.Map;

public enum ConsoleInputParser {
    LIST_BOOKS("/listBooks", ListLibraryItemCommand.class, Book.class),
    LIST_MOVIES("/listMovies",ListLibraryItemCommand.class, Movie.class),
    CHECKOUT_BOOK("/checkoutBook", CheckOutLibraryItem.class, Book.class),
    QUIT("/quit", QuitCommand.class, null),
    RETURN_BOOK("/returnBook", ReturnLibraryItemCommand.class, Book.class);

    private final String value;
    private final Map.Entry<Class, Class> commandAndTargetClass;

    private ConsoleInputParser(String value, Class commandClass, Class targetClass){
        this.value = value;
        commandAndTargetClass = new AbstractMap.SimpleImmutableEntry<Class, Class>(commandClass, targetClass);
    }

    public static Map.Entry<Class, Class> getCommandAndTargetClassFromString(String value){
        for (ConsoleInputParser parser : ConsoleInputParser.values()){
            if (parser.value.equalsIgnoreCase(value)) return parser.commandAndTargetClass;
        }
        return null;
    }
}
