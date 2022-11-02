package seedu.duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    static String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Function to format the text before writing to file
     * @param module module which has to be formatted.
     * @return Returns a text with formatted content from module. Format: String
     */
    public static String formatText(Module module) {
        String course = module.getCourse();
        String semesterTaken = module.getSemesterTaken();
        String grade = module.getGrade();
        int mcs = module.getMcs();
        String text = "m/" + course + " s/" + semesterTaken + " mc/" + mcs + " g/" + grade + "\n";
        return text;
    }

    /**
     * Function to load data from the file
     * @return returns Scanner object with data loaded from file
     * @throws FileNotFoundException Exception thrown when the file does not exist
     */
    public Scanner load() throws FileNotFoundException {
        try {
            File data = new File(filePath);
            Scanner fileReader = new Scanner(data);
            return fileReader;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Function to save the entire list of modules into a file
     * @param modules list of modules to be saved
     */
    public static void save(ModuleList modules) {
        try {
            String text = "";
            for (Module module : ModuleList.modules) {
                text += formatText(module);
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("The file does not exist yet!");
        }
    }
}
