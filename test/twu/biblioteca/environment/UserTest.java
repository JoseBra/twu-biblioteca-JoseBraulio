package twu.biblioteca.environment;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void initializeUser() throws Exception {
        User user = new User("123", "123", new UserProfile("Jose", "123@123.com", "Fake St. 123", "123456789"));
        Assert.assertEquals("123", user.getUsername());
        Assert.assertEquals("Jose", user.getUserProfile().getName());
    }
}
