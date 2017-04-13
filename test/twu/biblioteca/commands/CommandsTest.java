package twu.biblioteca.commands;

import org.junit.Test;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandsTest {

    @Test
    public void ListBookCommandGetCorrectExplaniatoryUsage() throws Exception {
        ListBookCommand listBookCommand = new ListBookCommand();
        assertEquals("/listBooks  -  List all available books",listBookCommand.getUsageExplanation());
    }

    @Test
    public void ListBookCommandsExecute() throws Exception{
        ListBookCommand listBookCommand = new ListBookCommand();
        ArrayList<Book> bookLibraryList = new ArrayList<Book>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        bookLibraryList.add(new Book(2, "Animal Farm", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("17/08/1945")));
        assertEquals("1     1984                 George Orwell        1949\n" +
                     "2     Animal Farm          George Orwell        1945", listBookCommand.execute(new Library("LibraryMock", bookLibraryList)).trim());
    }
}
