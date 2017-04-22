package twu.biblioteca.environment;

public class UserProfile {

    private final String name;
    private final String email;
    private final String address;
    private final String phoneNumber;

    public UserProfile() {
        this("","","","");
    }

    public UserProfile(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
