package twu.biblioteca.commands;

import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.UserRole;

public class QuitCommand extends Command{

    public QuitCommand() {
        super("/quit  -  Close Biblioteca system.", false, UserRole.CUSTOMER);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        System.exit(0);
        return "Goodbye!";
    }
}
