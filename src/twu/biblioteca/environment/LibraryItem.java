package twu.biblioteca.environment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by josebraulio on 22/04/2017.
 */
public abstract class LibraryItem {
    protected String title;
    protected String creator;
    protected Date releaseDate;
    private boolean checkedOut;

    public LibraryItem(String title, Date releaseDate, String creator) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
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
