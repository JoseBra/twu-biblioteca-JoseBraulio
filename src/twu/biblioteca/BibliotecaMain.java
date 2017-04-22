package twu.biblioteca;

import twu.biblioteca.agents.UserInteractionAgent;
import twu.biblioteca.commands.*;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaMain {
    private static UserInteractionAgent userInteractionAgent;

    public static void main(String[] args) {
        try {
            startBibliotecaSystem();
            keepContactWithUser();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void startBibliotecaSystem() throws ParseException {
        userInteractionAgent = new UserInteractionAgent(initializeLibrary(), createAvailableCommandsList());
        userInteractionAgent.startSystem();
    }

    private static void keepContactWithUser(){
        while (true){
            userInteractionAgent.awaitUserInput();
        }
    }

    private static List<Command> createAvailableCommandsList(){
        List<Command> availableCommands = new ArrayList<Command>();
        availableCommands.add(new ListBookCommand());
        availableCommands.add(new CheckOutLibraryItem());
        availableCommands.add(new ReturnBookCommand());
        availableCommands.add(new QuitCommand());
        return availableCommands;
    }

    private static Library initializeLibrary() throws ParseException {
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        return new Library("Bangalore Public Library", bookLibraryList);
    }
}
