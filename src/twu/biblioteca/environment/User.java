package twu.biblioteca.environment;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private UserProfile userProfile;
    private ArrayList<LibraryItem> checkedOutItems;

    public User(String username, String password, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
        checkedOutItems = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void addCheckedOutLibraryItem(LibraryItem libraryItem) {
        checkedOutItems.add(libraryItem);
    }

    public ArrayList<LibraryItem> getCheckedOutItems() {
        return checkedOutItems;
    }

    public void removeCheckedOutLibraryItem(LibraryItem libraryItem) {
        checkedOutItems.remove(libraryItem);
    }
}
