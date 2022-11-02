package seedu.duke.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author chiewyx
public class UserTest {

    User user;

    @BeforeEach
    void initializeTest() {
        user = new User("yixiang", 22, "98126666");
    }

    @Test
    void testStatus() {
        assertEquals("Username: yixiang Age: 22 Contact: 98126666 ", user.toString());
    }

    @Test
    void getNameTest() {
        assertEquals("yixiang", user.getName());
    }

    @Test
    void convertUserToFileFormatTest() {
        assertEquals("yixiang | 22 | 98126666", user.convertItemToFileFormat());
    }
}
