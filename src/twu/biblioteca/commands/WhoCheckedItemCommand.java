package twu.biblioteca.commands;

import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.Library;
import twu.biblioteca.environment.LibraryItem;
import twu.biblioteca.environment.User;
import twu.biblioteca.environment.UserRole;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WhoCheckedItemCommand extends Command{
    public WhoCheckedItemCommand() {
        super("/whoCheckedBook [ISBN]  -  Finds the user who checked out that book.\n" +
                "\t/whoCheckedMovie [MovieID]  -  Finds the user who checked out that movie.", true, UserRole.LIBRARIAN);
    }

    @Override
    public String execute(Library library, String arguments, Class targetClass) {
        if (!LoginUserManager.getInstance().isUserLogged() ||  LoginUserManager.getInstance().isUserLogged() && !LoginUserManager.getInstance().getLoggedUser().getUserRoles().contains(this.requiredRole))
            return "You must be logged and match the permission requirements in order to execute this command.";
        for (User user : LoginUserManager.getInstance().getRegisteredUsers()
                            .stream()
                            .filter(userRegistered-> userRegistered.getCheckedOutItems().size() > 0)
                            .collect(Collectors.toList())){
            if (user.didCheckoutThisItem(library.findLibraryItem(Integer.valueOf(arguments), targetClass))) return user.getUsername();
        }
        return "No user has checked out this item.";
    }
}
