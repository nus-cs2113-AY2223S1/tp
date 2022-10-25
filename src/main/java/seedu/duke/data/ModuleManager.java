package seedu.duke.data;

import seedu.duke.Exceptions;
import seedu.duke.Timetable;
import seedu.duke.commands.CommandAddModule;
import seedu.duke.module.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModuleManager {
    static ArrayList<String> modules = new ArrayList<>();
    static String dataDirectoryPath;
    static String currSemester;

    public static void initModuleDataFile() {
        currSemester = DataManager.getCurrentSem();
        dataDirectoryPath = DataManager.getDirPath();

        try {
            File moduleDataFile = new File(dataDirectoryPath + "/ModuleData.txt");
            if (!moduleDataFile.exists()) {
                moduleDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating ModuleData.txt");
        }
    }

    public static void saveModules() {
        StringBuilder data = new StringBuilder();
        for (Module mods : Timetable.listOfModules) {
            data.append(mods.getModuleCode()).append("\n");
        }
        try {
            FileWriter myWriter = new FileWriter(dataDirectoryPath + "/ModuleData.txt");
            myWriter.write(data.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, failed to save to ModuleData.txt");
        }
    }

    public static void loadModules() throws FileNotFoundException, Exceptions.FileLoadException {
        File data = new File(dataDirectoryPath + "/ModuleData.txt");
        Scanner scanner = new Scanner(data);

        while (scanner.hasNextLine()) {
            String moduleCode = scanner.nextLine();
            String response = CommandAddModule.runAddModuleProcedure(currSemester, moduleCode);
            checkForErrors(response);
        }
    }

    private static void checkForErrors(String response) throws Exceptions.FileLoadException {
        switch (response) {
        case "Some IO errors has occurred, please try again":
        case "Sorry, this module is not available in the current semester.":
        case "The API request was interrupted, please try again.":
        case "Module not found, please enter a valid module code next time!":
            throw new Exceptions.FileLoadException();
        default:
            break;
        }
    }
}
