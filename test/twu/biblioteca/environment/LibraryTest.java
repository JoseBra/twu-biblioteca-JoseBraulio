package twu.biblioteca.environment;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class LibraryTest {
    @Test
    public void initializeLibrary() throws Exception {
        Library library = new Library("Bangalore Public Library", new ArrayList<Book>());
        assertEquals(Library.class, library.getClass());
        assertEquals("Bangalore Public Library", library.getName());
    }
}
