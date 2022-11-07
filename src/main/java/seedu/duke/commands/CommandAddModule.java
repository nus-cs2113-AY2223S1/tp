package seedu.duke.commands;

import seedu.duke.Exceptions;
import seedu.duke.UI;
import seedu.duke.commands.nusmodsapi.Nusmods;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.timetable.Timetable;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Command class for managing the process of adding new module to timetable.
 */
public class CommandAddModule {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Begins the process of adding module to the timetable.
     *
     * @param currentSemester The semester of the timetable indicated by the user at program start up.
     * @return A response string to be printed by UI class to the user.
     */
    public static  String addModule(String currentSemester) {
        String moduleCode = UI.getModuleCodeFromUser();
        return runAddModuleProcedure(currentSemester, moduleCode);
    }

    /**
     * Begins the process of adding module to the timetable after code has been parsed.
     *
     * @param currentSemester The semester of the timetable indicated by the user at program start up.
     * @param moduleCode The code of the module to be added.
     * @return A response string for the success or failure of the operation.
     */
    public static String runAddModuleProcedure(String currentSemester, String moduleCode) {
        Nusmods mod = new Nusmods();

        if (Timetable.listOfModules.size() >= 7) {
            return "Only up to 7 modules are allowed!";
        }

        List<Lesson> lessons;
        String[] info = new String[3];

        try {
            lessons = mod.addModuleInfo(currentSemester, info, moduleCode);
        } catch (IOException e) {
            return "Some IO errors has occurred, please try again";
        } catch (InterruptedException e) {
            return "The API request was interrupted, please try again.";
        } catch (Exceptions.InvalidSemException e) {
            return "Sorry, this module is not available in the current semester or this semester is invalid.";
        } catch (Exceptions.InvalidModuleCodeException e) {
            return "Module not found, please enter a valid module code next time!";
        } catch (Exceptions.InvalidDayException e) {
            return "Weekend modules are currently not supported!";
        }

        lgr.info("api call successful, attempting to add module to timetable");

        if (isAlreadyInTimetable(info[0])) {
            return "Module " + info[0] + " : " + info[1] + " is already in your timetable!\n";
        }

        Timetable.addNewModule(info[0], info[1], lessons);
        return "Successfully added new module: " + info[0] + " : " + info[1] + '\n';
    }

    /**
     * Checks if a module is already in the timetable.
     *
     * @param code Code of the module to be checked.
     * @return True if module already exists in timetable, false otherwise.
     */
    private static boolean isAlreadyInTimetable(String code) {
        List<Module> currentList = Timetable.getListOfModules();
        for (Module module : currentList) {
            if (Objects.equals(module.getModuleCode(), code)) {
                return true;
            }
        }
        return false;
    }
}