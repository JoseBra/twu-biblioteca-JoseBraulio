package twu.biblioteca.environment;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LibraryTest {
    @Test
    public void initializeLibrary() throws Exception {
        Library library = new Library("Bangalore Public Library", new ArrayList<LibraryItem>());
        assertEquals(Library.class, library.getClass());
        assertEquals("Bangalore Public Library", library.getName());
    }
}
