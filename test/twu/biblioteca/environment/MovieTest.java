package twu.biblioteca.environment;


import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void initializeMovie() throws Exception {
        Movie movie = new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null);

        assertEquals(Movie.class, movie.getClass());
        assertEquals(1, movie.getMovieID());
        assertEquals("The Truman Show", movie.getTitle());
        assertEquals("1998", movie.getReseleaseYear());
    }
}
