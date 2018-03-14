package simternship.simternship;

/**
 * Created by matthewbrown on 3/13/18.
 */

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestUser {

    @Test
    public void TestSetFirstName() {
        User u = new User("john", "smith", "jsmith@gmail.com");
        u.setFirstName("joe");
        String result = u.getFirstName();
        assertEquals(result, "joe");
    }

    @Test
    public void TestSetLastName() {
        User u = new User("john", "smith", "jsmith@gmail.com");
        u.setLastName("jones");
        String result = u.getLastName();
        assertEquals(result, "jones");
    }
}
