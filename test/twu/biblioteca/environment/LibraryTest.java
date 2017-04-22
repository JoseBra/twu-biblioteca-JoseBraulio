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

    @Test
    public void findLibraryItemTest() throws Exception {
        ArrayList<LibraryItem> libraryItemArrayList = new ArrayList<>();
        Movie trumanShow = new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null);
        libraryItemArrayList.add(trumanShow);
        libraryItemArrayList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("Bangalore Public Library",libraryItemArrayList);

        assertEquals(trumanShow, library.findLibraryItem(trumanShow.getMovieID(), trumanShow.getClass()));
    }
}
