package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author bdthanh
class UserStorageTest {

    @Test
    void handleUserLine() throws StoreFailureException {
        User user = new User("bui", 19, "38475932");
        String userLine = "bui | 19 | 38475932 | 40";
        String[] splitUserLine = userLine.split(" \\| ");
        assertEquals(user.toString(),
                UserStorage.handleUserLine(splitUserLine).toString());
    }
}