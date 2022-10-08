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

/**
 * With this class, we can quickly get all the existing usernames
 * to check whether user can create a wallet with such a new username.
 */
public class UserNameFileWorkings {

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
            System.out.println("I found some usernames saved");

        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating one");
            createUserNames(path);
            existingUserNames = new ArrayList<>();
        }

        return existingUserNames;
    }

    public static List<String> getUserNames(Path path) throws FileNotFoundException {
        List<String> existingUserNames = new ArrayList<>();
        File f = new File(path + "/usernames.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line = s.nextLine();
            existingUserNames.add(line);
        }

        return existingUserNames;
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
