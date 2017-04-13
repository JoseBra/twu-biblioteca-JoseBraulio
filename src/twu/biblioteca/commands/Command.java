package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;

public interface Command {
    String getUsageExplanation();
    String execute(Library library);
}
