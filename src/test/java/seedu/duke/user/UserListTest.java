package seedu.duke.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ContactNumberInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.UserNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void add_duplicateUserName_exceptionThrown() {
        userList.addUser(user);
        String duplicateName = "yixiang";
        String age = "22";
        String contactNumber = "98126666";
        String[] args = {duplicateName, age, contactNumber};
        assertThrows(DuplicateException.class, () -> userList.checkValidArgsForUser(args));
    }

    @Test
    void addUser_invalidAge_exceptionThrown() {
        String name = "john";
        String age = "-1";
        String contactNumber = "98126666";
        String[] args = {name, age, contactNumber};
        assertThrows(InvalidUserException.class, () -> userList.checkValidArgsForUser(args));
    }

    @Test
    void addUser_invalidContactNumber_exceptionThrown() {
        String name = "john";
        String age = "22";
        String contactNumber = "1";
        String[] args = {name, age, contactNumber};
        assertThrows(ContactNumberInvalidException.class, () -> userList.checkValidArgsForUser(args));
    }

    @Test
    void to_String_test() {
        userList.addUser(user);
        int size = userList.getSize();
        assertEquals("Here are 1 user(s) in your list:\n"
                + "1. Username: yixiang Age: 22 Contact: 98126666 ", userList.toString());
    }

    @Test
    void convertUserListToFileFormat() {
        userList.addUser(user);
        assertEquals("yixiang | 22 | 98126666\n", userList.convertUserListToFileFormat());
    }
}
