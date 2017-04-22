package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.UserRole;

import java.util.ArrayList;

public abstract class Command {
    protected String usageExplanation;
    protected boolean requireLogin;
    protected UserRole requiredRole;

    public Command(String usageExplanation, boolean requireLogin, UserRole requiredRole) {
        this.usageExplanation = usageExplanation;
        this.requireLogin = requireLogin;
        this.requiredRole = requiredRole;
    }

    public String getUsageExplanation(){
        return usageExplanation;
    };

    public boolean isRequireLogin() {return requireLogin;}

    public abstract String execute(Library library, String arguments, Class targetClass);

    public UserRole getRequiredRole() {
        return requiredRole;
    }
}
