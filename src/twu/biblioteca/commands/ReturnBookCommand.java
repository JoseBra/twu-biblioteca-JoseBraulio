package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class ReturnBookCommand extends Command{

    public ReturnBookCommand() {
        super("/return [ISBN code]  -  Returns the book with it's ISBN Code so it's available again.");
    }

    @Override
    public String execute(Library library, String arguments) {
        for (Book book : library.getStoredBooks()){
            if (book.getISBN() == Integer.valueOf(arguments)){
                return returnBook(book) ? "Thank you for returning the book." : "That is not a valid book to return.";
            }
        }
        return "That is not a valid book to return.";
    }

    private boolean returnBook(Book book) {
        if (!book.isCheckedOut()) return false;
        book.setCheckedOut(false);
        return true;
    }
}
