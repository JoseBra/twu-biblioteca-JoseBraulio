package twu.biblioteca.agents;

import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;

public class UserInteractionAgent {
    Library chosenLibrary;

    public void showWelcomeMessage() {
        System.out.print("Welcome to the new brand Biblioteca System!\n");
    }

    private void printBookDetailsColumns(Book book){
        System.out.printf("%-5s %-20s %-20s %-4s\n", book.getISBN(), book.getTitle(), book.getAuthor(), book.getReseleaseYear());
    }

    public void setChosenLibrary(Library library){
        this.chosenLibrary = library;
    }

    public void showMainMenu() {
        System.out.print("Here is a list of all the available commands:\n");
    }
}
