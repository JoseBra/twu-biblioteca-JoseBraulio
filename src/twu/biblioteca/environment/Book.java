package twu.biblioteca.environment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int ISBN;
    private String title;
    private String author;
    private Date releaseDate;
    private boolean checkedOut;

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

    public String getReseleaseYear() {
        return new SimpleDateFormat("yyyy").format(releaseDate);
    }

    public boolean isCheckedOut() { return checkedOut; }

    public void setCheckedOut(boolean checkedOut) { this.checkedOut = checkedOut; }
}
