package seedu.duke.user;

import seedu.duke.exception.UserNotFoundException;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_NOT_FOUND;

// @@author chiewyx
/**
 * A representation of a list of User.
 */
public class UserList {
    protected ArrayList<User> userList;

    /**
     * Constructor for UserList.
     *
     * @param userList the list of users from the stored file
     */
    public UserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    /**
     * Constructor for UserList.
     */
    public UserList() {
        this.userList = new ArrayList<>();
    }

    /**
     * Adds a new user to the list.
     *
     * @param toAdd the user to be added
     */
    public void addUser(User toAdd) {
        userList.add(toAdd);
    }

    /**
     * Removes a user from the list.
     *
     * @param userName name of the user to be removed
     * @throws UserNotFoundException if user is not found
     */
    public void deleteUser(String userName) throws UserNotFoundException {
        User user = getUserById(userName);
        userList.remove(user);
    }

    /**
     * Gets the size of user list.
     *
     * @return the size of user list
     */
    public int getSize() {
        return userList.size();
    }

    /**
     * Gets the user based on index specified.
     *
     * @param index of user
     * @return the user with the corresponding index
     */
    public User getUser(int index) {
        return userList.get(index - 1);
    }

    /**
     * Get user by name.
     *
     * @param userName name of the user
     * @return the user with the corresponding name
     * @throws UserNotFoundException if user is not found
     */
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
            if (user.getName().toLowerCase().contains(keyword.toLowerCase())) {
                assert user.getName().toLowerCase()
                        .contains(keyword.toLowerCase()) : "equals function not working";
                returnList.addUser(user);
            }
        }
        if (returnList.getSize() == 0) {
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

    public String convertUserListToFileFormat() {
        StringBuilder formattedString = new StringBuilder();
        for (User user : userList) {
            formattedString.append(user.convertItemToFileFormat()).append('\n');
        }
        return formattedString.toString();
    }
}
