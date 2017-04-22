package twu.biblioteca.environment;

import java.util.List;

public class Library {

    private String name;
    private List<LibraryItem> libraryItems;

    public Library(String name, List<LibraryItem> libraryItems) {
        this.name = name;
        this.libraryItems = libraryItems;
    }

    public String getName() {
        return name;
    }

    public List<LibraryItem> getLibraryItems() {
        return libraryItems;
    }
}
