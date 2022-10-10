package seedu.duke.user;

import seedu.duke.exception.UserNotFoundException;

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
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                assert userName.equals(user.getName()) : "equals function not working";
                return user;
            }
        }
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

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        if (userList.size() == 0) {
            listString.append("There is no user in your list right now");
        } else {
            listString.append("Here are ").append(userList.size()).append(" user(s) in your list:");
        }
        int index = 1;
        for (User user : userList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(user);
        }
        return String.valueOf(listString);
    }
}
