package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.commands.nusmodsapi.Nusmods;

import java.io.IOException;
import java.util.Scanner;

public class CommandAddModule {
    public static String addModule(Timetable timetable) {
        //Scanner in = new Scanner(System.in);
        Nusmods mod = new Nusmods();
        String[] info = new String[3];

        System.out.println("What is the module code?");
        try {
            info = mod.getModuleInfo();
        } catch (IOException e) {
            System.out.println("Some IO errors has occurred, please try again");
        } catch (InterruptedException e) {
            System.out.println("The API request was interrupted, please try again.");
        }
        String moduleCode = info[0];
        String moduleName = info[1];
        String moduleDescription = info[2];
        timetable.addNewModule(moduleCode, moduleName, moduleDescription);

        return "Successfully added new module: " + moduleCode + " : " + moduleName;
    }
}
