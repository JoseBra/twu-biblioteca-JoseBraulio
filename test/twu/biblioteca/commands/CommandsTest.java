package twu.biblioteca.commands;

import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.agents.LoginUserManager;
import twu.biblioteca.environment.*;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandsTest {
    LoginUserManager loginUserManager;

    @Before
    public void setUp() throws Exception {
        loginUserManager = LoginUserManager.getInstance();

        ArrayList<User> registeredUsers = new ArrayList<>();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        userRoles.add(UserRole.CUSTOMER);
        registeredUsers.add(new User("123-1234", "123", new UserProfile(),userRoles));
        loginUserManager.setRegisteredUsers(registeredUsers);
        loginUserManager.login("123-1234", "123");
    }

    @Test
    public void quitCommandGetExplanatoryUsage() throws Exception{
        QuitCommand quitCommand = new QuitCommand();
        assertEquals("/quit  -  Close Biblioteca system.", quitCommand.getUsageExplanation());
    }

    @Test
    public void loginUserCommandExecutesCorrectly() throws Exception {
        LoginUserCommand loginUserCommand = new LoginUserCommand();

        System.setIn(new ByteArrayInputStream("1234".getBytes()));
        assertEquals("Sorry, we don't recognize these credentials.", loginUserCommand.execute(null, "123-1234", null));

        System.setIn(new ByteArrayInputStream("123".getBytes()));
        assertEquals("Welcome to your Biblioteca!.", loginUserCommand.execute(null, "123-1234", null));
    }


    @Test
    public void findWhoCheckedOutAnItem() throws Exception {
        WhoCheckedItemCommand whoCheckedItemCommand = new WhoCheckedItemCommand();
        CheckOutLibraryItemCommand checkOutLibraryItemCommand = new CheckOutLibraryItemCommand();
        ArrayList<LibraryItem> bookLibraryList = new ArrayList<>();
        bookLibraryList.add(new Book(1, "1984", "George Orwell", new SimpleDateFormat("dd/MM/yyyy").parse("08/06/1949")));
        Library library = new Library("LibraryMock", bookLibraryList);
        loginUserManager.login("123-1234", "123");

        loginUserManager.getLoggedUser().getUserRoles().add(UserRole.LIBRARIAN);
        checkOutLibraryItemCommand.execute(library, "0", Book.class);
        assertEquals("No user has checked out this item.", whoCheckedItemCommand.execute(library, "1", Book.class));

        checkOutLibraryItemCommand.execute(library, "1", Book.class);
        assertEquals("123-1234", whoCheckedItemCommand.execute(library, "1", Book.class));
    }
}
