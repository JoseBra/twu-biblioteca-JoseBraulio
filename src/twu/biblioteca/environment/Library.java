package twu.biblioteca.environment;

import java.util.List;
import java.util.stream.Collectors;

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

    public LibraryItem findLibraryItem(int elementID, Class targetClass) {
        for (LibraryItem libraryItem : libraryItems.stream().filter(li-> li.getClass().equals(targetClass)).collect(Collectors.toList())){
            if (targetClass.equals(Book.class) && libraryItem instanceof Book && ((Book) libraryItem).getISBN() == elementID) return libraryItem;
            else if (targetClass.equals(Movie.class) && libraryItem instanceof Movie && ((Movie) libraryItem).getMovieID() == elementID) return libraryItem;
        }
        return null;
    }
}
