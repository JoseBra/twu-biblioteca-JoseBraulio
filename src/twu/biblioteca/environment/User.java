package twu.biblioteca.environment;

public class User {

    private final String username;
    private final String password;
    private final UserProfile userProfile;

    public User(String username, String password, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
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
}
