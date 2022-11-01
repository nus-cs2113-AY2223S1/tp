package seedu.duke.storage;

import seedu.duke.exception.UserFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_NUM_OF_ARGS_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORAGE_REASON;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_TO_FIX_FILES;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_VALUE_OF_ARGS_INVALID;

// @@author bdthanh
public class UserStorage extends Storage {
    private static final String SEPARATOR = "\\|";
    private static final int USERNAME_INDEX = 0;
    private static final int AGE_INDEX = 1;
    private static final int CONTACT_INDEX = 2;
    private static final int NUM_OF_ARGS = 3;
    private final String userFilePath;
    private final UserList userList;

    /**
     * Constructor for Storage of Users.
     */
    public UserStorage(String userFilePath) {
        this.userFilePath = userFilePath;
        this.userList = new UserList();
    }

    /**
     * Read the users from a given file.
     *
     * @return The list of users stored in the file
     * @throws UserFileNotFoundException If the file cannot be found
     * @throws StoreFailureException     If there is a failure loading
     */
    public UserList loadData() throws UserFileNotFoundException, StoreFailureException {
        int lineNo = 0;
        try {
            File userFile = new File(userFilePath);
            Scanner scanner = new Scanner(userFile);
            while (scanner.hasNext()) {
                lineNo++;
                String userLine = scanner.nextLine();
                String[] splitUserLine = userLine.split(SEPARATOR);
                trimArrayValues(splitUserLine);
                User user = handleUserLine(splitUserLine);
                userList.addUser(user);
            }
            return userList;
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED + lineNo
                    + MESSAGE_STORAGE_REASON + e.getMessage() + "\n" + MESSAGE_TO_FIX_FILES);
        }
    }

    /**
     * Writes the current users to a file when exiting Duke.
     *
     * @param userList The list of users to be stored.
     * @throws StoreFailureException If there is an exception occurs.
     */
    public void writeData(UserList userList) throws StoreFailureException {
        try {
            FileWriter fileWriter = new FileWriter(userFilePath);
            String formattedUserList = userList.convertUserListToFileFormat();
            fileWriter.write(formattedUserList);
            fileWriter.close();
        } catch (IOException e) {
            makeUserDir(userList);
        }
    }

    private void makeUserDir(UserList userList) throws StoreFailureException {
        int startIndex = userFilePath.lastIndexOf("/");
        String fileDirectory = userFilePath.replace(userFilePath.substring(startIndex), "");
        File file = new File(fileDirectory);
        if (file.mkdir()) {
            writeData(userList);
        } else {
            throw new StoreFailureException(MESSAGE_STORE_INVALID);
        }
    }

    /**
     * Analyses the information the users stored in the file
     * and checks if valid or not.
     *
     * @param splitUserLine The raw user information.
     * @return A User with full information.
     */
    public User handleUserLine(String[] splitUserLine) throws Exception {
        checkIfArgsEmpty(splitUserLine, NUM_OF_ARGS,
                MESSAGE_NUM_OF_ARGS_INVALID, MESSAGE_VALUE_OF_ARGS_INVALID);
        this.userList.checkValidArgsForUser(splitUserLine);
        return this.getUserFromUserLine(splitUserLine);
    }

    private User getUserFromUserLine(String[] splitUserLine) {
        String username = splitUserLine[USERNAME_INDEX];
        int age = Integer.parseInt(splitUserLine[AGE_INDEX]);
        String contactNumber = splitUserLine[CONTACT_INDEX];
        return new User(username, age, contactNumber);
    }
}
