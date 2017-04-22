package twu.biblioteca.commands;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReturnLibraryItemCommand extends Command{

    public ReturnLibraryItemCommand() {
        super("/return [ISBN code]  -  Returns the book with it's ISBN Code so it's available again.\n" +
                "\t/return [MovieID code]  -  Returns the movie with it's Movie ID Code so it's available again.", true, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        for (LibraryItem libraryItem : library.getLibraryItems()
                                        .stream()
                                        .filter(li -> li.getClass().equals(targetClass) && li.isCheckedOut())
                                        .collect(Collectors.toList())){
            if (libraryItem.getClass().equals(Book.class) && ((Book)libraryItem).getISBN() == Integer.valueOf(arguments))  return returnLibraryItem(libraryItem);
            else if (libraryItem.getClass().equals(Movie.class) && ((Movie)libraryItem).getMovieID() == Integer.valueOf(arguments)) return returnLibraryItem(libraryItem);
        }
        return messageItemNotReturned(targetClass);
    }

    private String returnLibraryItem(LibraryItem libraryItem) {
        if (!libraryItem.isCheckedOut()) return messageItemNotReturned(libraryItem.getClass());
        libraryItem.setCheckedOut(false);
        try {
            LoginUserManager.getInstance().getLoggedUser().removeCheckedOutLibraryItem(libraryItem);
        } catch (Exception e) {
            return "You must be logged in order to return items.";
        }
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
