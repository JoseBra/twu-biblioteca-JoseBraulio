package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;

/**
 * Created by josebraulio on 22/04/2017.
 */
public class ListMovieCommand extends Command{
    public ListMovieCommand() {
        super("/listMovies  -  List all available movies.");
    }

    @Override
    public String execute(Library library, String arguments) {
        return "";
    }
}
