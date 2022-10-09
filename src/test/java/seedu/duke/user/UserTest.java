package seedu.duke.user;

import org.junit.jupiter.api.Test;
import seedu.duke.user.User;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testStatus() {
        User user = new User("John Doe", 45, "93746378");
        assertEquals((user.getUserId() + " | John Doe | 45 | 93746378"), user.toString());
    }
}
