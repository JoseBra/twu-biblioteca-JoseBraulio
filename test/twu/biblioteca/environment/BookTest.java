package twu.biblioteca.environment;

import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void initializeBook() throws Exception {
        Book book = new Book(123, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949"));
        assertEquals(Book.class, book.getClass());
        assertEquals(123, book.getISBN());
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getCreator());
    }

    @Test
    public void getReleaseYearFromBook() throws Exception {
        Book book = new Book(123, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949"));
        assertEquals("1949", book.getReseleaseYear());
    }
}
