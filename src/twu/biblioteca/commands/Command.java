package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;

public abstract class Command {
    protected String usageExplanation;
    protected boolean requireLogin;

    public Command(String usageExplanation, boolean requireLogin) {
        this.usageExplanation = usageExplanation;
        this.requireLogin = requireLogin;
    }

    public String getUsageExplanation(){
        return usageExplanation;
    };

    public boolean isRequireLogin() {return requireLogin;}

    public abstract String execute(Library library, String arguments, Class targetClass);
}
