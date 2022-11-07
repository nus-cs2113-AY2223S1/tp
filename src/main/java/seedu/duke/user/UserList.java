package seedu.duke.user;

import seedu.duke.exception.ContactNumberInvalidException;
import seedu.duke.exception.DuplicateException;
import seedu.duke.exception.InvalidUserException;
import seedu.duke.exception.ItemNotFoundException;
import seedu.duke.exception.UserNotFoundException;
import seedu.duke.item.ItemList;

import java.util.ArrayList;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_DUPLICATE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_FORMAT_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_CONTACT_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NAME_LENGTH_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_SELF_BORROWER;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USERNAME_TAKEN;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_AGE_OUT_OF_RANGE;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_NOT_FOUND;

// @@author chiewyx

/**
 * A representation of a list of User.
 */
public class UserList {
    protected ArrayList<User> userList;
    private static final int CONTACT_INDEX = 2;
    private static final int CONTACT_LENGTH = 8;
    private static final int AGE_LOWER_LIMIT = 10;
    private static final int AGE_UPPER_LIMIT = 100;
    private static final int NAME_LIMIT = 20;
    private static final int NUMBER_OF_ARGS = 3;
    private static final int NAME_INDEX = 0;
    private static final int AGE_INDEX = 1;

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

    public ArrayList<User> getUserList() {
        return this.userList;
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

    /**
     * Get users that contains the keyword in their name.
     *
     * @param keyword keyword to find users
     * @return a list of users with name containing the keyword
     * @throws UserNotFoundException if user is not found
     */
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

    /**
     * Overrides toString method of Object to get string representation of UserList.
     *
     * @return A string representation of UserList
     */
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
            listString.append('\n').append(index++).append(". ").append(user);
        }
        return String.valueOf(listString);
    }

    //@@author bdthanh
    private void checkValidContactNumber(String contactNumber)
            throws ContactNumberInvalidException {
        if (contactNumber.length() != CONTACT_LENGTH) {
            throw new ContactNumberInvalidException(MESSAGE_CONTACT_LENGTH_INVALID);
        }
        try {
            if (Integer.parseInt(contactNumber) < 0) {
                throw new ContactNumberInvalidException(MESSAGE_CONTACT_FORMAT_INVALID);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_CONTACT_FORMAT_INVALID);
        }
        for (User user : this.userList) {
            if (contactNumber.equals(user.getContactNumber())) {
                throw new ContactNumberInvalidException(MESSAGE_CONTACT_DUPLICATE);
            }
        }
    }

    private void checkValidAge(String age) throws InvalidUserException {
        try {
            if (Integer.parseInt(age) < AGE_LOWER_LIMIT || Integer.parseInt(age) > AGE_UPPER_LIMIT) {
                throw new InvalidUserException(MESSAGE_USER_AGE_OUT_OF_RANGE);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MESSAGE_USER_AGE_INVALID);
        }
    }

    private void checkValidName(String userName) throws DuplicateException, InvalidUserException {
        if (userName.length() > NAME_LIMIT) {
            throw new InvalidUserException(MESSAGE_NAME_LENGTH_INVALID);
        }
        try {
            this.getUserById(userName);
            throw new DuplicateException(MESSAGE_USERNAME_TAKEN);
        } catch (UserNotFoundException e) {
            return;
        }
    }

    /**
     * Checks if arguments for new user is valid or not.
     *
     * @param args The array containing arguments.
     * @throws ContactNumberInvalidException If contact is invalid
     * @throws DuplicateException            If there are duplicate names or contacts
     * @throws InvalidUserException          If user is invalid
     */
    public void checkValidArgsForUser(String[] args)
            throws ContactNumberInvalidException, DuplicateException, InvalidUserException {
        assert args.length == NUMBER_OF_ARGS : "Args length is invalid";
        checkValidName(args[NAME_INDEX].trim());
        checkValidAge(args[AGE_INDEX].trim());
        checkValidContactNumber(args[CONTACT_INDEX].trim());
    }

    /**
     * Checks if a borrower is valid or not.
     *
     * @param args     The array of input args
     * @param itemList The main item list
     * @throws InvalidUserException  If the user borrows him/herself
     * @throws ItemNotFoundException If the item cannot be found
     * @throws UserNotFoundException If the user cannot be found
     */
    public void checkValidBorrower(String[] args, ItemList itemList)
            throws InvalidUserException, ItemNotFoundException, UserNotFoundException {
        String itemOwnerName = itemList.getItemById(args[0]).getOwnerId();
        if (getUserById(args[1]).getName().equals(itemOwnerName)) {
            throw new InvalidUserException(MESSAGE_SELF_BORROWER);
        }
    }

    /**
     * Formats the userList to store in memory.
     *
     * @return A formatted string of user list information
     */
    public String convertUserListToFileFormat() {
        StringBuilder formattedString = new StringBuilder();
        for (User user : userList) {
            formattedString.append(user.convertItemToFileFormat()).append('\n');
        }
        return formattedString.toString();
    }
}
