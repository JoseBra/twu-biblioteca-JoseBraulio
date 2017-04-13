package twu.biblioteca.commands;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class ListBookCommand implements Command{

    @Override
    public String getUsageExplanation() {
        return "/listBooks  -  List all available books";
    }

    @Override
    public String execute(Library library) {
        String printableString = "";
        for (Book book : library.getStoredBooks()){
            printableString += formatBookDetailsColumns(book);
        }
        return printableString;
    }

    private String formatBookDetailsColumns(Book book){
        return String.format("%-5s %-20s %-20s %-4s\n", book.getISBN(), book.getTitle(), book.getAuthor(), book.getReseleaseYear());
    }
}
