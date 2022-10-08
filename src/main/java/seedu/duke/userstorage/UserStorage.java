package seedu.duke.userstorage;
import seedu.duke.university.University;
import seedu.duke.user.UserModule;
import seedu.duke.user.UserModuleList;
import seedu.duke.user.UserUniversityList;
import seedu.duke.user.UserUniversityListManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * Clears content in the file and writes new text into the file.
     * @param filePath Specified file path.
     * @param textToAdd Text to write into the file.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public void saveFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads file that holds universities and modules information which
     * the user saves after exiting the app most recently
     * @return fileContent Content of the file
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public String loadFile() throws IOException {
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
            fileContent += s.nextLine();    //dollar sign to represent end of line
        }
        return fileContent;
    }

    /**
     * Function to convert UserUniversityListManager to a String
     * to be stored in a text file
     * Format:
     * /u <UniversityName>
     *
     * @param uniList user's list of interested universities
     * @return a String that contains all universities and modules information
     */
    public static String convertUniversityListIntoFileContent(UserUniversityListManager uniList) {
        String output = "";
        for (Map.Entry<String, UserUniversityList> pair : uniList.getMyManager().entrySet()) {
            UserUniversityList uni = pair.getValue();
            output += "/" + uni.getUniversityName() + "%" + "\n";
            ArrayList<UserModule> modules = uni.getMyModules().getModules();
            for (UserModule module : modules) {
                output += module.getPuCode() + ";";
                output += module.getPuTitle() + ";";
                output += module.getNusCode() + ";";
                output += module.getNusTitle() + ";";
                output += module.getNusCredit() + "%" + "\n";
            }
        }
        output = output.substring(1);   //remove first backslash
        return output;
    }

    public static HashMap<String, UserUniversityList> convertFileContentIntoUniversityList(String fileContent) {
        HashMap<String, UserUniversityList> myManager = new HashMap<String, UserUniversityList>();
        String[] unis = fileContent.split("/");
        for (String uni: unis) {
            String[] items = uni.split("%");
            String uniName = items[0];
            UserUniversityList uniList = new UserUniversityList(uniName);
            UserModuleList moduleList = new UserModuleList();
            for (int i = 1; i < items.length; ++i) {
                String[] details = items[i].split(";");
                UserModule userModule = new UserModule(details[0], details[1], details[2], details[3], details[4]);
                moduleList.addModule(userModule);
            }
            uniList.setMyModules(moduleList);
            myManager.put(uniName, uniList);
        }
        return myManager;
    }
}
