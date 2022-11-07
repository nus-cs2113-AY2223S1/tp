package seedu.duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Function to format the text before writing to file.
     * @param module module which has to be formatted.
     * @return Returns a text with formatted content from module.
     */
    public String formatText(Module module) {
        String course = module.getCourse();
        String semesterTaken = module.getSemesterTaken();
        String grade = module.getGrade();
        int mcs = module.getMcs();
        return "m/" + course + " s/" + semesterTaken + " mc/" + mcs + " g/" + grade + "\n";
    }

    /**
     * Function to load data from the file.
     * @return returns Scanner object with data loaded from file
     * @throws FileNotFoundException Exception thrown when the file does not exist
     */
    public Scanner load() throws FileNotFoundException {
        try {
            File data = new File(filePath);
            return new Scanner(data);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Function to save the entire list of modules into the data file.
     */
    public void save() {
        try {
            StringBuilder text = new StringBuilder();
            for (Module module : ModuleList.modules) {
                text.append(formatText(module));
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("The file does not exist yet!");
        }
    }
}
