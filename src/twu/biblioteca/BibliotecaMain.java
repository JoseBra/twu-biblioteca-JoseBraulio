package twu.biblioteca;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.agents.UserInteractionAgent;
import twu.biblioteca.commands.*;
import twu.biblioteca.environment.*;

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
        initializeLoginManager();
        userInteractionAgent.startSystem();
    }

    private static void keepContactWithUser(){
        while (true){
            userInteractionAgent.awaitUserInput();
        }
    }

    private static List<Command> createAvailableCommandsList(){
        List<Command> availableCommands = new ArrayList<Command>();
        availableCommands.add(new ListLibraryItemCommand());
        availableCommands.add(new CheckOutLibraryItemCommand());
        availableCommands.add(new ReturnLibraryItemCommand());
        availableCommands.add(new QuitCommand());
        availableCommands.add(new LoginUserCommand());
        availableCommands.add(new ShowUserProfileCommand());
        availableCommands.add(new WhoCheckedItemCommand());
        return availableCommands;
    }

    private static Library initializeLibrary() throws ParseException {
        ArrayList<LibraryItem> libraryItemsList = new ArrayList<>();
        libraryItemsList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        libraryItemsList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        libraryItemsList.add(new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null));
        libraryItemsList.add(new Movie(2, "The Last Samurai", new SimpleDateFormat("dd/MM/yyyy").parse("12/03/1994"), "Homer Simpson", 7.4));
        return new Library("Bangalore Public Library", libraryItemsList);
    }

    private static void initializeLoginManager(){
        ArrayList<UserRole> userRoles = new ArrayList<>();
        userRoles.add(UserRole.CUSTOMER);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("123-1234", "123", new UserProfile("Jose", "123@123.com", "Fake St. 123", "123452"), userRoles));

        ArrayList<UserRole> librarianRoles = new ArrayList<>();
        librarianRoles.add(UserRole.LIBRARIAN);
        users.add(new User("111-1111", "111", new UserProfile(),librarianRoles));

        LoginUserManager.getInstance().setRegisteredUsers(users);
    }
}
