package twu.biblioteca.environment;

import java.util.Date;

public class Book {
    private int ISBN;
    private String title;
    private String author;
    private Date releaseDate;

    public Book(int ISBN, String title, String author, Date releaseDate) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
