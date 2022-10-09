package seedu.duke.user;

import org.junit.jupiter.api.Test;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserListTest {

    @Test
    void add_addOneUser_expectSizeOne() {
        UserList userList = new UserList();
        User user = new User("John Doe", 45, "93746378");
        userList.addUser(user);
        assertEquals(1, userList.getSize());
    }

    @Test
    void getUser_getTheFirstUser_expectTheFirstUser() {
        UserList userList = new UserList();
        User user = new User("John Doe", 45, "93746378");
        userList.addUser(user);
        assertEquals(user, userList.getUser(0));
    }

    @Test
    void findUser_findUserUsingId_expectTheUserIndex() {
        UserList userList = new UserList();
        User user = new User("John Doe", 45, "93746378");
        userList.addUser(user);
        assertEquals(0, userList.findUser(user.getUserId()));
    }

}
