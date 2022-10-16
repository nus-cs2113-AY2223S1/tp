package seedu.duke.user;

import seedu.duke.exception.UserNotFoundException;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_NOT_FOUND;

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
        throw new UserNotFoundException(MESSAGE_USER_NOT_FOUND);
    }

    public UserList getUsersByKeyword(String keyword) throws UserNotFoundException {
        UserList returnList = new UserList();
        for (User user : userList) {
            if (user.getName().contains(keyword)) {
                assert user.getName().contains(keyword) : "equals function not working";
                returnList.addUser(user);
            }
        }
        if(returnList.getSize() == 0){
            throw new UserNotFoundException(MESSAGE_USER_NOT_FOUND);
        }
        return returnList;
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
