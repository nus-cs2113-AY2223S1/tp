package seedu.duke.user;


import seedu.duke.item.Item;
import seedu.duke.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserList {

    private static final Logger logger = Logger.getLogger("Foo");
    protected ArrayList<User> userList;

    public UserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public UserList() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User toAdd) {
        userList.add(toAdd);
    }

    public void deleteUser(String userName) throws UserNotFoundException {
        User user = getUserById(userName);
        userList.remove(user);
    }

    public int getSize() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index - 1);
    }

    public User getUserById(String userName) throws UserNotFoundException {
        logger.log(Level.INFO, "getting user from user list");
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                assert userName.equals(user.getName()) : "equals function not working";
                logger.log(Level.INFO, "user found");
                return user;
            }
        }
        logger.log(Level.WARNING, "user not found error", 
                new UserNotFoundException("This user cannot be found in the list"));
        throw new UserNotFoundException("This user cannot be found in the list");
    }

    public String listUser() {
        StringBuilder listOfUsers = new StringBuilder();
        listOfUsers.append("Here are your list of users:").append(System.lineSeparator());
        for (User user : userList) {
            listOfUsers.append(user.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }
}
