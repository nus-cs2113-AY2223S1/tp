package seedu.duke.user;

import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.Item;

import java.util.ArrayList;

public class UserList {
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

    // find user using name
    public User getUserById(String userName) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        throw new UserNotFoundException("This user cannot be found in the list");
    }

    public String listUser() {
        StringBuilder listOfUsers = new StringBuilder();
        for (User user : userList) {
            listOfUsers.append(user.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }
}
