package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;

public class CheckOutLibraryItem extends Command{

    public CheckOutLibraryItem() {
        super("/checkout [ISBN code]  -  Checks out the book with it's ISBN Code.");
    }

    @Override
    public String execute(Library library, String arguments) {
        for (LibraryItem libraryItem : library.getLibraryItems()){
            if (libraryItem instanceof Book && libraryItem.getISBN() == Integer.valueOf(arguments)){

            }
            if (libraryItem.getISBN() == Integer.valueOf(arguments)){
                return checkOutBook(libraryItem) ? "Thank you! Enjoy the book." : "That book is not available";
            }
        }
        return "That book is not available.";
    }

    private boolean checkOutBook(Book book) {
        if (book.isCheckedOut()) return false;
        book.setCheckedOut(true);
        return true;
    }
}
