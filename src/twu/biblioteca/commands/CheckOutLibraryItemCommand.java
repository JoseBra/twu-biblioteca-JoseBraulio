package twu.biblioteca.commands;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CheckOutLibraryItemCommand extends Command{

    public CheckOutLibraryItemCommand() {
        super("/checkoutBook [ISBN code]  -  Checks out the book with it's ISBN Code.\n" +
                "\t/checkoutMovie [MovieID code]  -  Checks out the movie with it's ID Code.",
                true, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        for (LibraryItem libraryItem : library.getLibraryItems()
                                        .stream()
                                        .filter(li -> li.getClass().equals(targetClass) && !li.isCheckedOut())
                                        .collect(Collectors.toList())){
                if (libraryItem.getClass().equals(Book.class) && ((Book)libraryItem).getISBN() == Integer.valueOf(arguments)
                    || libraryItem.getClass().equals(Movie.class) && ((Movie)libraryItem).getMovieID() == Integer.valueOf(arguments)) return checkOutLibraryItem(libraryItem);
        }
        return messageItemNotAvailable(targetClass);
    }

    private String checkOutLibraryItem(LibraryItem libraryItem) {
        if (libraryItem.isCheckedOut()) return messageItemNotAvailable(libraryItem.getClass());
        libraryItem.setCheckedOut(true);
        try {
            LoginUserManager.getInstance().getLoggedUser().addCheckedOutLibraryItem(libraryItem);
        } catch (Exception e) {
            return "You must be logged in order to check out items.";
        }
        return messageItemCheckedOut(libraryItem.getClass());
    }

    private String messageItemNotAvailable(Class libraryItemClass){
        if (Book.class.equals(libraryItemClass)) return "That book is not available.";
        else if (Movie.class.equals(libraryItemClass)) return "That movie is not available";
        return "That item is not available";
    }

    private String messageItemCheckedOut(Class libraryItemClass){
        if (Book.class.equals(libraryItemClass)) return "Thank you! Enjoy the book.";
        else if (Movie.class.equals(libraryItemClass)) return "Thank you! Enjoy the movie.";
        return "Thank you! Enjoy it.";
    }
}
