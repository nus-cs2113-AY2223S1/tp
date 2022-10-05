package seedu.duke.userstorage;
import seedu.duke.university.University;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the specified file and saving tasks in the specified file
 * File name should be specified in Duke.java: "data/duke.txt"
 */
public class UserStorage {
    private static String filePath;

    public UserStorage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void saveFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }

    public static String loadFile() throws IOException {
        File f = new File(filePath);
        String[] words = filePath.split("/");
        String dirName = words[0] + '/';
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        String fileContent = "";
        while(s.hasNext()) {
            fileContent += s.nextLine() + "$";    //dollar sign to represent end of line
        }
        return fileContent;
    }

}
