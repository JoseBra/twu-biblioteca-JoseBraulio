package twu.biblioteca.agents;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import twu.biblioteca.environment.User;
import twu.biblioteca.environment.UserProfile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginUserManagerTest {
    LoginUserManager loginUserManager;

    @Before
    public void setUp() throws Exception {
        loginUserManager = LoginUserManager.getInstance();

        ArrayList<User> registeredUsers = new ArrayList<>();
        registeredUsers.add(new User("123", "123", new UserProfile()));
        loginUserManager.setRegisteredUsers(registeredUsers);

    }

    @Test
    public void successfullyLoginUser() throws Exception {
        assertTrue(loginUserManager.login("123", "123"));
        assertEquals("123", loginUserManager.getLoggedUser().getUsername());
    }

    @Test
    public void unsuccessfullyLoginUser() throws Exception {
        LoginUserManager loginUserManager = LoginUserManager.getInstance();
        assertFalse(loginUserManager.login("1223","123"));
    }

    @Test
    public void logout() throws Exception {
        loginUserManager.login("123", "123");
        loginUserManager.logout();
        assertEquals(null, loginUserManager.getLoggedUser());
    }
}
