package twu.biblioteca.agents;

import twu.biblioteca.commands.Command;
import twu.biblioteca.commands.ListBookCommand;

public enum ConsoleInputParser {
    LISTBOOKS("/listBooks", ListBookCommand.class);

    private final String value;
    private final Class commandClass;

    private ConsoleInputParser(String value, Class commandClass){
        this.value = value;
        this.commandClass = commandClass;
    }

    public static Class getCommandClassFromString(String value){
        for (ConsoleInputParser parser : ConsoleInputParser.values()){
            if (parser.value.equalsIgnoreCase(value)) return parser.commandClass;
        }
        return null;
    }
}
