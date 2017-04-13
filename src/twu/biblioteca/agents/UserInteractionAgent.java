package twu.biblioteca.agents;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class UserInteractionAgent {
    Library chosenLibrary;

    public void showWelcomeMessage() {
        System.out.println("Welcome to the new brand Biblioteca System!");
    }

    public void listAllAvailableBooks(){
        chosenLibrary.getStoredBooks().forEach(book -> printBookDetailsColumns(book));
    }

    private void printBookDetailsColumns(Book book){
        System.out.printf("%-5s %-20s %-20s %-4s\n", book.getISBN(), book.getTitle(), book.getAuthor(), book.getReseleaseYear());
    }

    public void setChosenLibrary(Library library){
        this.chosenLibrary = library;
    }
}
