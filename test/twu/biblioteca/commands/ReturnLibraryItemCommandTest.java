package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReturnLibraryItemCommandTest {
    @Test
    public void returnBookCommandExplanatoryUsage() throws Exception {
        ReturnLibraryItemCommand returnLibraryItemCommand = new ReturnLibraryItemCommand();
        assertEquals("/return [ISBN code]  -  Returns the book with it's ISBN Code so it's available again.\n" +
                "\t/return [MovieID code]  -  Returns the movie with it's Movie ID Code so it's available again.", returnLibraryItemCommand.getUsageExplanation());
    }

    @Test
    public void returnBookCommandSuccessfullyExecuted() throws Exception {
        ReturnLibraryItemCommand returnLibraryItemCommand = new ReturnLibraryItemCommand();
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        checkOutLibraryItemCommand.execute(library, "1", Book.class);

        assertEquals("Thank you for returning the book.", returnLibraryItemCommand.execute(library, "1", Book.class));
    }

    @Test
    public void returnBookCommandUnsuccessfullyExecuted() throws Exception {
        ReturnLibraryItemCommand returnLibraryItemCommand = new ReturnLibraryItemCommand();
        Library library = new Library("LibraryMock",  new ArrayList<>());

        assertEquals("That is not a valid book to return.", returnLibraryItemCommand.execute(library, "1", Book.class));
    }

    @Test
    public void registerUserReturnItem() throws Exception {
        LoginUserManager loginUserManager = LoginUserManager.getInstance();

        ArrayList<User> registeredUsers = new ArrayList<>();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        userRoles.add(UserRole.CUSTOMER);
        registeredUsers.add(new User("123-1234", "123", new UserProfile(),userRoles));
        loginUserManager.setRegisteredUsers(registeredUsers);
        loginUserManager.login("123-1234", "123");

        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ReturnLibraryItemCommand returnLibraryItemCommand = new ReturnLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);
        loginUserManager.login("123-1234", "123");

        checkOutLibraryItemCommand.execute(library, "1", Book.class);
        checkOutLibraryItemCommand.execute(library, "2", Book.class);
        returnLibraryItemCommand.execute(library, "1", Book.class);
        assertEquals(loginUserManager.getLoggedUser().getCheckedOutItems().size(), 1);
    }
}
