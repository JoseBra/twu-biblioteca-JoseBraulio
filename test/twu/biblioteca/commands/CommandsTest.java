package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;
import twu.biblioteca.environment.Movie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandsTest {

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
        CheckOutLibraryItem checkOutLibraryItem = new CheckOutLibraryItem();
        assertEquals("/checkoutBook [ISBN code]  -  Checks out the book with it's ISBN Code.\n" +
                "\t/checkoutMovie [MovieID code]  -  Checks out the movie with it's ID Code.", checkOutLibraryItem.getUsageExplanation());
    }

    @Test
    public void checkOutBookCommandSuccessfullyExecuted() throws Exception {
        CheckOutLibraryItem checkOutLibraryItem = new CheckOutLibraryItem();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        assertEquals("Thank you! Enjoy the book.", checkOutLibraryItem.execute(library, Integer.toString(1), Book.class));
    }

    @Test
    public void checkOutBookCommandUnsuccessfullyExecuted() throws Exception{
        CheckOutLibraryItem checkOutLibraryItem = new CheckOutLibraryItem();
        Library library = new Library("LibraryMock", new ArrayList<>());

        assertEquals("That book is not available.", checkOutLibraryItem.execute(library, Integer.toString(1), Book.class));
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
        CheckOutLibraryItem checkOutLibraryItem = new CheckOutLibraryItem();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        checkOutLibraryItem.execute(library, "1", Book.class);

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
}
