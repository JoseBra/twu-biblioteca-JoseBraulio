package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class ListBookCommand extends Command{

    public ListBookCommand() {
        super("/listBooks  -  List all available books");
    }

    @Override
    public String execute(Library library, String arguments) {
        String printableString = "";
        for (Book book : library.getStoredBooks()){
            if (!book.isCheckedOut()) printableString += formatBookDetailsColumns(book);
        }
        return printableString;
    }

    private String formatBookDetailsColumns(Book book){
        return String.format("%-5s %-20s %-20s %-4s\n", book.getISBN(), book.getTitle(), book.getAuthor(), book.getReseleaseYear());
    }
}
