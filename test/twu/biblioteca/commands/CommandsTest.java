package twu.biblioteca.commands;

import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandsTest {
    LoginUserManager loginUserManager;

    @Before
    public void setUp() throws Exception {
        loginUserManager = LoginUserManager.getInstance();

        ArrayList<User> registeredUsers = new ArrayList<>();
        registeredUsers.add(new User("123-1234", "123", new UserProfile()));
        loginUserManager.setRegisteredUsers(registeredUsers);
        loginUserManager.login("123-1234", "123");
    }

    @Test
    public void listItemCommandGetExplanatoryUsage() throws Exception {
        ListLibraryItemCommand listLibraryItemCommand = new ListLibraryItemCommand();
        assertEquals("/listBooks  -  List all available books.\n" +
                "\t/listMovies - List all available movies.", listLibraryItemCommand.getUsageExplanation());
    }

    @Test
    public void listBookCommandsExecute() throws Exception{
        ListLibraryItemCommand listLibraryItemCommand = new ListLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        assertEquals("1     1984                 George Orwell        1949\n" +
                     "2     Animal Farm          George Orwell        1945", listLibraryItemCommand.execute(new Library("LibraryMock", bookLibraryList), "", Book.class).trim());
    }

    @Test
    public void quitCommandGetExplanatoryUsage() throws Exception{
        QuitCommand quitCommand = new QuitCommand();
        assertEquals("/quit  -  Close Biblioteca system.", quitCommand.getUsageExplanation());
    }

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
    public void dontMixBooksAndMoviesWhenListing() throws Exception {
        ListLibraryItemCommand listLibraryItemCommand = new ListLibraryItemCommand();
        ArrayList<LibraryItem> libraryItemArrayList = new ArrayList<>();
        libraryItemArrayList.add(new Movie(2, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null));
        libraryItemArrayList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));

        assertEquals("2     The Truman Show      Peter Weir           1998     Unrated", listLibraryItemCommand.execute(new Library("LibraryMock", libraryItemArrayList), "", Movie.class).trim());
        assertEquals("1     1984                 George Orwell        1949", listLibraryItemCommand.execute(new Library("LibraryMock", libraryItemArrayList), "", Book.class).trim());
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
    public void loginUserCommandExecutesCorrectly() throws Exception {
        LoginUserCommand loginUserCommand = new LoginUserCommand();

        System.setIn(new ByteArrayInputStream("1234".getBytes()));
        assertEquals("Sorry, we don't recognize these credentials.", loginUserCommand.execute(null, "123-1234", null));

        System.setIn(new ByteArrayInputStream("123".getBytes()));
        assertEquals("Welcome to your Biblioteca!.", loginUserCommand.execute(null, "123-1234", null));
    }

    @Test
    public void registerCheckedOutItemOnUser() throws Exception {
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);
        loginUserManager.login("123-1234", "123");

        checkOutLibraryItemCommand.execute(library, "1", Book.class);
        assertEquals(loginUserManager.getLoggedUser().getCheckedOutItems().size(), 1);

    }

    @Test
    public void registerUserReturnItem() throws Exception {
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
