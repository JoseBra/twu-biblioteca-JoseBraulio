package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;

public class ListBookCommand extends Command{

    public ListBookCommand() {
        super("/listBooks  -  List all available books");
    }

    @Override
    public String execute(Library library, String arguments) {
        String printableString = "";
        for (LibraryItem book : library.getLibraryItems()){
            if (!book.isCheckedOut()) printableString += formatBookDetailsColumns(book);
        }
        return printableString;
    }

    private String formatBookDetailsColumns(Book book){
        return String.format("%-5s %-20s %-20s %-4s\n", book.getISBN(), book.getTitle(), book.getCreator(), book.getReseleaseYear());
    }
}
