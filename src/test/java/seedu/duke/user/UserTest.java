package seedu.duke.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testStatus() {
        User user = new User("John Doe", 45, "93746378");
        assertEquals("Username: John Doe Age: 45 Contact: 93746378 ", user.toString());
    }
}
