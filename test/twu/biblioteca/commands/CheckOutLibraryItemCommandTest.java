package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CheckOutLibraryItemCommandTest {

    @Test
    public void checkOutBookCommandExplanatoryUsage() throws Exception {
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        assertEquals("/checkoutBook [ISBN code]  -  Checks out the book with it's ISBN Code.\n" +
                "\t/checkoutMovie [MovieID code]  -  Checks out the movie with it's ID Code.", checkOutLibraryItemCommand.getUsageExplanation());
    }

    @Test
    public void checkOutBookCommandSuccessfullyExecuted() throws Exception {
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        assertEquals("Thank you! Enjoy the book.", checkOutLibraryItemCommand.execute(library, Integer.toString(1), Book.class));
    }

    @Test
    public void checkOutBookCommandUnsuccessfullyExecuted() throws Exception{
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        Library library = new Library("LibraryMock", new ArrayList<>());

        assertEquals("That book is not available.", checkOutLibraryItemCommand.execute(library, Integer.toString(1), Book.class));
    }


    @Test
    public void checkOutMovieSuccess() throws Exception {
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> movieList = new ArrayList<>();
        movieList.add(new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null));
        Library library = new Library("LibraryMock", movieList);

        assertEquals("Thank you! Enjoy the movie.", checkOutLibraryItemCommand.execute(library, "1", Movie.class));
    }

    @Test
    public void checkOutMovieUnsuccessfully() throws Exception {
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> movieList = new ArrayList<>();
        movieList.add(new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null));
        Library library = new Library("LibraryMock", movieList);
        checkOutLibraryItemCommand.execute(library, "1", Movie.class);

        assertEquals("That movie is not available", checkOutLibraryItemCommand.execute(library, "1", Movie.class));

    }

    @Test
    public void registerCheckedOutItemOnUser() throws Exception {
        LoginUserManager loginUserManager = LoginUserManager.getInstance();

        ArrayList<User> registeredUsers = new ArrayList<>();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        userRoles.add(UserRole.CUSTOMER);
        registeredUsers.add(new User("123-1234", "123", new UserProfile(),userRoles));
        loginUserManager.setRegisteredUsers(registeredUsers);
        loginUserManager.login("123-1234", "123");

        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);
        loginUserManager.login("123-1234", "123");

        checkOutLibraryItemCommand.execute(library, "1", Book.class);
        assertEquals(loginUserManager.getLoggedUser().getCheckedOutItems().size(), 1);
    }
}
