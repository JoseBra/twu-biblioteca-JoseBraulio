package twu.biblioteca.agents;

import twu.biblioteca.commands.*;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Movie;

import java.util.AbstractMap;
import java.util.Map;

public enum ConsoleInputParser {
    LIST_BOOKS("/listBooks", ListLibraryItemCommand.class, Book.class),
    LIST_MOVIES("/listMovies",ListLibraryItemCommand.class, Movie.class),
    CHECKOUT_BOOK("/checkoutBook", CheckOutLibraryItemCommand.class, Book.class),
    CHECKOUT_MOVIE("/checkoutMovie", CheckOutLibraryItemCommand.class, Movie.class),
    RETURN_BOOK("/returnBook", ReturnLibraryItemCommand.class, Book.class),
    QUIT("/quit", QuitCommand.class, null),
    LOGIN("/login", LoginUserCommand.class, null),
    WHO_CHECKED_BOOK("/whoCheckedBook", WhoCheckedItemCommand.class, Book.class),
    WHO_CHECKED_MOVIE("/whoCheckedMovie", WhoCheckedItemCommand.class, Movie.class),
    PROFILE_DETAILS("/showProfile", ShowUserProfileCommand.class, null);

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
