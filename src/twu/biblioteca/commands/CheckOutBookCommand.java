package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class CheckOutBookCommand extends Command{

    public CheckOutBookCommand() {
        super("/checkout [ISBN code]  -  Checks out the book with it's ISBN Code.");
    }

    @Override
    public String execute(Library library, String arguments) {
        for (Book book : library.getStoredBooks()){
            if (book.getISBN() == Integer.valueOf(arguments)){
                return checkOutBook(book) ? "Thank you! Enjoy the book." : "That book is not available";
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
