package twu.biblioteca.environment;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class UserTest {
    @Test
    public void initializeUser() throws Exception {
        User user = new User("123", "123", new UserProfile("Jose", "123@123.com", "Fake St. 123", "123456789"), null);
        Assert.assertEquals("123", user.getUsername());
        Assert.assertEquals("Jose", user.getUserProfile().getName());
    }

    @Test
    public void addCheckedOutItemToUser() throws Exception {
        User user = new User("123", "123", new UserProfile("Jose", "123@123.com", "Fake St. 123", "123456789"), null);
        user.addCheckedOutLibraryItem((new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null)));
        Assert.assertEquals(user.getCheckedOutItems().size(), 1);
    }

    @Test
    public void userDidCheckOutItem() throws Exception {
        User user = new User("123", "123", new UserProfile("Jose", "123@123.com", "Fake St. 123", "123456789"), null);
        Movie trumanMovie = new Movie(1, "The Truman Show", new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1998"), "Peter Weir", null);
        user.addCheckedOutLibraryItem(trumanMovie);
        Assert.assertTrue(user.didCheckoutThisItem(trumanMovie));
    }
}
