package seedu.duke.user;

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
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                userList.remove(user);
            }
        }
    }

    public int getSize() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index - 1);
    }

    // find user using name
    public User findUser(String userName) {
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public String listUser() {
        StringBuilder listOfUsers = new StringBuilder();
        for (User user : userList) {
            listOfUsers.append(user.toString()).append(System.lineSeparator());
        }
        return listOfUsers.toString();
    }
}
