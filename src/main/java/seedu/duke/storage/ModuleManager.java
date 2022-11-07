package seedu.duke.storage;

import seedu.duke.Exceptions;
import seedu.duke.commands.CommandAddModule;
import seedu.duke.module.Module;
import seedu.duke.timetable.Timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for managing the ModuleData data file.
 */
public class ModuleManager {
    private static String dataDirectoryPath;
    private static String currSemester;

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
            System.out.println("Sorry, ave to ModuleData.txt");
        }
    }

    /**
     * Loads up the timetable from past module save by reading and adding through API calls.
     *
     * @throws FileNotFoundException If file is not found.
     * @throws Exceptions.FileLoadException If there was an error in adding modules.
     */
    public static void loadModules() throws FileNotFoundException, Exceptions.FileLoadException {
        File data = new File(dataDirectoryPath + "/ModuleData.txt");
        Scanner scanner = new Scanner(data);

        while (scanner.hasNextLine()) {
            String moduleCode = scanner.nextLine();
            String response = CommandAddModule.runAddModuleProcedure(currSemester, moduleCode);
            checkForErrors(response);
        }

        scanner.close();
    }

    private static void checkForErrors(String response) throws Exceptions.FileLoadException {
        switch (response) {
        case "Some IO errors has occurred, please try again":
        case "Sorry, this module is not available in the current semester.":
        case "The API request was interrupted, please try again.":
        case "Module not found, please enter a valid module code next time!":
        case "Only up to 7 modules are allowed!":
        case "Weekend modules are currently not supported!":
            throw new Exceptions.FileLoadException();
        default:
            break;
        }
    }

    /**
     * Deletes all data in module save file.
     *
     * @param dataDirectoryPath Directory path to the file to be cleared.
     */
    public static void deleteDataFile(String dataDirectoryPath) {
        File data = new File(dataDirectoryPath + " \"/ModuleData.txt\"");
        data.delete();
    }
}
