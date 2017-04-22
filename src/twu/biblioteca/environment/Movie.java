package twu.biblioteca.environment;


import java.util.Date;

public class Movie extends LibraryItem{
    private int movieID;
    private Double movieRating;

    public Movie(Integer movieID, String title, Date releaseDate, String creator, Double movieRating) {
        super(title, releaseDate, creator);
        this.movieID = movieID;
        this.movieRating = movieRating;
    }

    public int getMovieID() {
        return movieID;
    }

    public Double getMovieRating() {
        return movieRating;
    }
}
