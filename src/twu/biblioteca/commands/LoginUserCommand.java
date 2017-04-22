package twu.biblioteca.commands;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.UserRole;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginUserCommand extends Command{
    public LoginUserCommand() {
        super("/login [Username] - Begins the process for login into the system.", false, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        LoginUserManager loginUserManager = LoginUserManager.getInstance();
        if (loginUserManager.checkIsValidUserName(arguments)){
            String password = requestPassword();
            return messageUserLogged(loginUserManager.login(arguments, password));
        }
        return messageWrongUsernameFormat();
    }

    private String requestPassword(){
        if (System.out != null ) System.out.println("Please introduce your password: ");
        return new Scanner(System.in).nextLine();
    }

    private String messageUserLogged(Boolean correctLogin){
        return correctLogin ? "Welcome to your Biblioteca!." : "Sorry, we don't recognize these credentials.";
    }

    private String messageWrongUsernameFormat(){
        return "Incorrect Username. The username format should be xxx-xxxx.";
    }
}
