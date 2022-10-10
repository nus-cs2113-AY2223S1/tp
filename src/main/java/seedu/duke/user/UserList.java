package seedu.duke.user;

import seedu.duke.item.Item;

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

    public void deleteUser(String userName) {
        boolean isFound = false;
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                isFound = true;
                userList.remove(user);
            }
        }
        if (!isFound) {
            System.out.println("cannot find user");
        }
    }

    public int getSize() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index - 1);
    }
    public User getUserById(String id) {
        for (User user : this.userList) {
            if (id.equals(user.getUserId())) {
                return user;
            }
        }
        return null;
    }
    // find user using name
    public User findUser(String userName) throws NullPointerException {
        logger.log(Level.INFO, "getting user from user list");
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                assert userName.equals(user.getName()) : "equals function not working";
                logger.log(Level.INFO, "user found");
                return user;
            }
        }
        logger.log(Level.WARNING, "user not found error", new NullPointerException());
        return null;
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
