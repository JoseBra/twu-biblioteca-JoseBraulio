package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;
import twu.biblioteca.environment.Movie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ListLibraryItemsCommandTest {
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
    public void noToMixBooksAndMoviesWhenListing() throws Exception {
        ListLibraryItemCommand listLibraryItemCommand = new ListLibraryItemCommand();
        ArrayList<LibraryItem> libraryItemArrayList = new ArrayList<>();
        libraryItemArrayList.add(new Movie(2, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null));
        libraryItemArrayList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));

        assertEquals("2     The Truman Show      Peter Weir           1998     Unrated", listLibraryItemCommand.execute(new Library("LibraryMock", libraryItemArrayList), "", Movie.class).trim());
        assertEquals("1     1984                 George Orwell        1949", listLibraryItemCommand.execute(new Library("LibraryMock", libraryItemArrayList), "", Book.class).trim());
    }
}
