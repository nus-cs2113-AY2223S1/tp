package seedu.duke.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.UserNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author chiewyx
public class UserListTest {

    UserList userList;
    User user;

    @BeforeEach
    void initializeTest() {
        userList = new UserList();
        user = new User("yixiang", 22, "98126666");
    }

    @Test
    void constructorTest() {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(user);
        UserList userListNew = new UserList(userArrayList);
        assertEquals(1, userListNew.getSize());
    }

    @Test
    void add_addOneUser_expectSizeOne() {
        userList.addUser(user);
        assertEquals(1, userList.getSize());
    }

    @Test
    void getUser_getTheFirstUser_expectTheFirstUser() {
        userList.addUser(user);
        assertEquals(user, userList.getUser(1));
    }

    @Test
    void findUser_findUserUsingId_expectTheUserObject() throws UserNotFoundException {
        userList.addUser(user);
        assertEquals(user, userList.getUserById("yixiang"));
    }

    @Test
    void deleteUser_deleteUserUsingId_expectNoUser() throws UserNotFoundException {
        userList.addUser(user);
        userList.deleteUser("yixiang");
        assertEquals(0, userList.getSize());
    }

    @Test
    void getUsersByKeyword_expectUserList() throws UserNotFoundException {
        userList.addUser(user);
        User user2 = new User("John Doe", 22, "92343802");
        userList.addUser(user2);
        assertEquals(1, userList.getUsersByKeyword("John").getSize());
    }

    @Test
    void convertUserListToFileFormat() {
        userList.addUser(user);
        assertEquals("yixiang | 22 | 98126666\n", userList.convertUserListToFileFormat());
    }
}
