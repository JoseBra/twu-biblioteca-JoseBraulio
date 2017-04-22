package twu.biblioteca.environment;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private UserProfile userProfile;
    private ArrayList<UserRole> userRoles;
    private ArrayList<LibraryItem> checkedOutItems;

    public User(String username, String password, UserProfile userProfile, ArrayList<UserRole> userRoles) {
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
        this.userRoles = userRoles;
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

    public boolean didCheckoutThisItem(LibraryItem libraryItem) {
        for (LibraryItem checkedItem : checkedOutItems){
            if (libraryItem instanceof Book && checkedItem instanceof Book && ((Book)libraryItem).getISBN() ==  ((Book)checkedItem).getISBN()) return true;
            else if (libraryItem instanceof Movie && checkedItem instanceof Movie && ((Movie)libraryItem).getMovieID() ==  ((Movie)checkedItem).getMovieID()) return true;
        }
        return false;
    }

    public ArrayList<UserRole> getUserRoles() {
        return userRoles;
    }
}
