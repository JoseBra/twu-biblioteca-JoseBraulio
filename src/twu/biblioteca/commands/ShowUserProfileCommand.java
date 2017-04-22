package twu.biblioteca.commands;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.Book;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.UserProfile;
import twu.biblioteca.environment.UserRole;

public class ShowUserProfileCommand extends Command{

    public ShowUserProfileCommand() {
        super("/showProfile  -  Prints your profile details.", true, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        return LoginUserManager.getInstance().isUserLogged() ?
                formatUserProfileDetails(LoginUserManager.getInstance().getLoggedUser().getUserProfile())
                : "You must be logged to get your profile details.";
    }

    private String formatUserProfileDetails(UserProfile userProfile){
        String profileDetails = "";
        profileDetails += String.format("%-14s %-30s\n", "Name:", userProfile.getName()!=null ?  userProfile.getName() : "");
        profileDetails += String.format("%-14s %-30s\n", "Email:", userProfile.getEmail()!=null ?  userProfile.getEmail() : "");
        profileDetails += String.format("%-14s %-30s\n", "Address:", userProfile.getAddress()!=null ?  userProfile.getAddress() : "");
        profileDetails += String.format("%-14s %-30s\n", "Phone number:", userProfile.getPhoneNumber()!=null ?  userProfile.getPhoneNumber() : "");
        return profileDetails;
    }
}
