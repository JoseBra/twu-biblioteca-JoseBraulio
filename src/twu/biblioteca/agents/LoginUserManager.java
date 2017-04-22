package twu.biblioteca.agents;

import twu.biblioteca.environment.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginUserManager {
    private List<User> registeredUsers;
    private User loggedUser;
    private final Pattern USERNAME_PATTERN = Pattern.compile("\\d{3}\\-\\d{4}");

    private static LoginUserManager ourInstance = new LoginUserManager();

    public static LoginUserManager getInstance() {
        return ourInstance;
    }

    private LoginUserManager() {
        registeredUsers = new ArrayList<>();
    }


    public boolean login(String username, String password) {
        for (User user : registeredUsers){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                loggedUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout(){
        loggedUser = null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setRegisteredUsers(ArrayList<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public boolean checkIsValidUserName(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public boolean isUserLogged() {
        return !(this.loggedUser == null);
    }
}
