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
import static seedu.duke.exception.message.ExceptionMessages.MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED;
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
    public ArrayList<User> loadData() throws UserFileNotFoundException, StoreFailureException {
        try {
            File userFile = new File(userFilePath);
            ArrayList<User> userList = new ArrayList<>();
            Scanner scanner = new Scanner(userFile);
            int checkSum = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNext()) {
                String userLine = scanner.nextLine();
                String[] splitUserLine = userLine.split(SEPARATOR);
                User user = handleUserLine(splitUserLine);
                userList.add(user);
            }
            checkCheckSumWhole(userList, checkSum);
            return userList;
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException(MESSAGE_FILE_NOT_FOUND);
        } catch (Exception e) {
            throw new StoreFailureException(MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED);
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
    public static User handleUserLine(String[] splitUserLine) throws StoreFailureException {
        User user = getUserFromUserLine(splitUserLine);
        checkCheckSumLine(user, Integer.parseInt(splitUserLine[3]));
        return user;
    }

    private static User getUserFromUserLine(String[] splitUserLine) {
        assert splitUserLine.length == 4 : "Invalid User Line";
        String username = splitUserLine[0];
        int age = Integer.parseInt(splitUserLine[1]);
        String contactNumber = splitUserLine[2];
        return new User(username, age, contactNumber);
    }

    private static void checkCheckSumLine(User user, int checkSum) throws StoreFailureException {
        if (user.toString().length() != checkSum) {
            throw new StoreFailureException(MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED);
        }
    }

    private static void checkCheckSumWhole(ArrayList<User> userList, int checkSum) throws StoreFailureException {
        if (userList.size() != checkSum / 3) {
            throw new StoreFailureException(MESSAGE_USER_STORAGE_ILLEGALLY_MODIFIED);
        }
    }
}
