package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;

public abstract class Command {
    protected String usageExplanation;

    public Command(String usageExplanation) {
        this.usageExplanation = usageExplanation;
    }

    public String getUsageExplanation(){
        return usageExplanation;
    };

    public abstract String execute(Library library, String arguments);
}
