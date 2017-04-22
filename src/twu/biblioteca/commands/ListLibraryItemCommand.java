package twu.biblioteca.commands;

import twu.biblioteca.environment.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListLibraryItemCommand extends Command{

    public ListLibraryItemCommand() {
        super("/listBooks  -  List all available books.\n" +
                "\t/listMovies - List all available movies.", false, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        String printableString = "";
        for (LibraryItem libraryItem : library.getLibraryItems()
                .stream()
                .filter(li -> li.getClass().equals(targetClass) && !li.isCheckedOut())
                .collect(Collectors.toList())){
            printableString += formatItemDetailsColumns(libraryItem);
        }
        return printableString;
    }

    private String formatItemDetailsColumns(LibraryItem libraryItem){
        if (libraryItem instanceof Book)
            return String.format("%-5s %-20s %-20s %-4s\n", ((Book)libraryItem).getISBN(), libraryItem.getTitle(), libraryItem.getCreator(), libraryItem.getReseleaseYear());
        if (libraryItem instanceof Movie)
            return String.format("%-5s %-20s %-20s %-8s %-8s\n", ((Movie)libraryItem).getMovieID(), libraryItem.getTitle(), libraryItem.getCreator(), libraryItem.getReseleaseYear(),
                ((Movie)libraryItem).getMovieRating() == null ? "Unrated" : ((Movie)libraryItem).getMovieRating());
        return "";
    }
}
