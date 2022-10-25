package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.exception.FinanceException;
import seedu.duke.exception.FinanceException.ExceptionCollection;

/**
 * With this class, we can quickly get all the existing usernames
 * to check whether user can create a wallet with such a new username.
 */
public class UserNameFileWorkings {

    final static Path FILE_PATH = Paths.get("src","main","data");

    public static List<String> userNameFile() throws IOException {

        List<String> existingUserNames = new ArrayList<>();
        Path path = Paths.get("src","main","data");


        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            existingUserNames = getUserNames(path);

        } catch (FinanceException e) {
            createUserNames(path);
            existingUserNames = new ArrayList<>();
        }

        return existingUserNames;
    }

    public static List<String> getUserNames(Path path) throws FinanceException {
        List<String> existingUserNames = new ArrayList<>();
        File f = new File(path + "/usernames.txt"); // create a File for the given file path
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new FinanceException(ExceptionCollection.USERNAME_FILE_NOT_FOUND_EXCEPTION);
        } 

        while (s.hasNext()) {
            String line = s.nextLine();
            existingUserNames.add(line);
        }

        s.close();
        return existingUserNames;
    }

    public static void deleteUserName(String username) throws FinanceException {
        List<String> existingUserNames = getUserNames(FILE_PATH);
        existingUserNames.remove(username);
        Path path = Paths.get(FILE_PATH.toString(), "usernames.txt");
        File f = new File(path.toString());
        try {
            FileWriter fileWriter = new FileWriter(f);
            for (String dataLine: existingUserNames){
                fileWriter.write(dataLine);
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FinanceException(ExceptionCollection.USERFILE_WRITE_EXCEPTION);
        }
        
    }

    protected static void createUserNames(Path filePath) throws IOException {
        File f = new File(filePath + "/usernames.txt");
        FileWriter fw = new FileWriter(f);
        fw.close();
    }

    public static void writeToUserNames(String userName) throws IOException {
        Path filePath = Paths.get("src","main","data");
        File f = new File(filePath + "/usernames.txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        fw.write(userName);
        fw.write(System.lineSeparator());
        fw.close();
    }
}
