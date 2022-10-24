package seedu.duke.storage;

import seedu.duke.exception.UserFileNotFoundException;
import seedu.duke.exception.StoreFailureException;
import seedu.duke.user.User;
import seedu.duke.user.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_FILE_NOT_FOUND;
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_STORE_INVALID;

//@@author bdthanh
public class UserStorage extends Storage {
    private static final String SEPARATOR = " \\| ";
    private final String userFilePath;

    /**
     * Constructor for Storage of Users.
     */
    public UserStorage(String userFilePath) {
        this.userFilePath = userFilePath;
    }

    /**
     * Read the users from a given file.
     *
     * @return The list of users stored in the file.
     * @throws UserFileNotFoundException If the file cannot be found.
     */
    public ArrayList<User> loadData() throws UserFileNotFoundException {
        try {
            File userFile = new File(userFilePath);
            ArrayList<User> userList = new ArrayList<>();
            Scanner scanner = new Scanner(userFile);
            while (scanner.hasNext()) {
                String userLine = scanner.nextLine();
                String[] splitUserLine = userLine.split(SEPARATOR);
                User user = handleUserLine(splitUserLine);
                userList.add(user);
            }
            return userList;
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
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

    public void makeUserDir(UserList userList) throws StoreFailureException {
        int startIndex = userFilePath.lastIndexOf("/");
        String fileDirectory =
                userFilePath.replace(userFilePath.substring(startIndex), "");
        File file = new File(fileDirectory);
        if (file.mkdir()) {
            writeData(userList);
        } else {
            throw new StoreFailureException(MESSAGE_STORE_INVALID);
        }
    }

    /**
     * Analyses the information the users stored in the file.
     *
     * @param splitUserLine The raw user information.
     * @return A User with full information.
     */
    public User handleUserLine(String[] splitUserLine) {
        String username = splitUserLine[0];
        int age = Integer.parseInt(splitUserLine[1]);
        String contactNumber = splitUserLine[2];
        return new User(username, age, contactNumber);
    }
}
