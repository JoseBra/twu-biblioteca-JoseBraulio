package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;
import twu.biblioteca.environment.Movie;

import java.util.stream.Collectors;

public class ReturnLibraryItemCommand extends Command{

    public ReturnLibraryItemCommand() {
        super("/return [ISBN code]  -  Returns the book with it's ISBN Code so it's available again.\n" +
                "\t/return [MovieID code]  -  Returns the movie with it's Movie ID Code so it's available again.");
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        for (LibraryItem libraryItem : library.getLibraryItems()
                                        .stream()
                                        .filter(li -> li.getClass().equals(targetClass) && li.isCheckedOut())
                                        .collect(Collectors.toList())){
            return returnLibraryItem(libraryItem);
        }
        return messageItemNotReturned(targetClass);
    }

    private String returnLibraryItem(LibraryItem libraryItem) {
        if (!libraryItem.isCheckedOut()) return messageItemNotReturned(libraryItem.getClass());
        libraryItem.setCheckedOut(false);
        return messageItemReturned(libraryItem.getClass());
    }

    private String messageItemNotReturned(Class libraryItemClass){
        if (Book.class.equals(libraryItemClass)) return "That is not a valid book to return.";
        else if (Movie.class.equals(libraryItemClass)) return "That is not a valid movie to return.";
        return "That is not a valid item to return.";
    }

    private String messageItemReturned(Class libraryItemClass){
        if (Book.class.equals(libraryItemClass)) return "Thank you for returning the book.";
        else if (Movie.class.equals(libraryItemClass)) return "Thank you for returning the movie.";
        return "Thank you for returning the item.";
    }
}
