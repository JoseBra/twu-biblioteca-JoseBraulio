package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandsTest {

    @Test
    public void listBookCommandGetExplanatoryUsage() throws Exception {
        ListBookCommand listBookCommand = new ListBookCommand();
        assertEquals("/listBooks  -  List all available books",listBookCommand.getUsageExplanation());
    }

    @Test
    public void listBookCommandsExecute() throws Exception{
        ListBookCommand listBookCommand = new ListBookCommand();
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        assertEquals("1     1984                 George Orwell        1949\n" +
                     "2     Animal Farm          George Orwell        1945", listBookCommand.execute(new Library("LibraryMock", bookLibraryList), "").trim());
    }

    @Test
    public void quitCommandGetExplanatoryUsage() throws Exception{
        QuitCommand quitCommand = new QuitCommand();
        assertEquals("/quit  -  Close Biblioteca system.", quitCommand.getUsageExplanation());
    }

    @Test
    public void checkOutBookCommandExplanatoryUsage() throws Exception {
        CheckOutBookCommand checkOutBookCommand = new CheckOutBookCommand();
        assertEquals("/checkout [ISBN code]  -  Checks out the book with it's ISBN Code.", checkOutBookCommand.getUsageExplanation());
    }

    @Test
    public void checkOutBookCommandSuccessfullyExecuted() throws Exception {
        CheckOutBookCommand checkOutBookCommand = new CheckOutBookCommand();
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        assertEquals("Thank you! Enjoy the book.", checkOutBookCommand.execute(library, Integer.toString(1)));
    }

    @Test
    public void checkOutBookCommandUnsuccessfullyExecuted() throws Exception{
        CheckOutBookCommand checkOutBookCommand = new CheckOutBookCommand();
        Library library = new Library("LibraryMock", new ArrayList<>());

        assertEquals("That book is not available.", checkOutBookCommand.execute(library, Integer.toString(1)));
    }

    @Test
    public void returnBookCommandExplanatoryUsage() throws Exception {
        ReturnBookCommand returnBookCommand = new ReturnBookCommand();
        assertEquals("/return [ISBN code]  -  Returns the book with it's ISBN Code so it's available again.", returnBookCommand.getUsageExplanation());
    }

    @Test
    public void returnBookCommandSuccessfullyExecuted() throws Exception {
        ReturnBookCommand returnBookCommand = new ReturnBookCommand();
        CheckOutBookCommand checkOutBookCommand = new CheckOutBookCommand();
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);

        checkOutBookCommand.execute(library, "1");

        assertEquals("Thank you for returning the book.", returnBookCommand.execute(library, "1"));
    }

    @Test
    public void returnBookCommandUnsuccessfullyExecuted() throws Exception {
        ReturnBookCommand returnBookCommand = new ReturnBookCommand();
        Library library = new Library("LibraryMock",  new ArrayList<Book>());

        assertEquals("That is not a valid book to return.", returnBookCommand.execute(library, "1"));
    }
}
