package twu.biblioteca.environment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book extends LibraryItem {
    private int ISBN;

    public Book(int ISBN, String title, String creator, Date releaseDate) {
        super(title, releaseDate, creator);
        this.ISBN = ISBN;
    }

    public int getISBN() {
        return ISBN;
    }

}
