package twu.biblioteca.environment;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<Book> storedBooks;

    public Library(String name, List<Book> storedBooks) {
        this.name = name;
        this.storedBooks = storedBooks;
    }

    public String getName() {
        return name;
    }

    public List<Book> getStoredBooks() {
        return storedBooks;
    }
}
