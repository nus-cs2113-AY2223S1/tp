package seedu.duke.commands;

import seedu.duke.Exceptions;
import seedu.duke.Timetable;
import seedu.duke.UI;
import seedu.duke.commands.nusmodsapi.Nusmods;
import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class CommandAddModule {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static String addModule(String currentSemester) {
        Nusmods mod = new Nusmods();
        List<Lesson> lessons;
        String[] info = new String[3];

        UI.printResponse("Please enter module code");

        try {
            lessons = mod.addModuleInfo(currentSemester, info);
        } catch (IOException e) {
            return "Some IO errors has occurred, please try again";
        } catch (InterruptedException e) {
            return "The API request was interrupted, please try again.";
        } catch (Exceptions.InvalidSemException e) {
            return "Sorry, this module is not available in the current semester.";
        } catch (Exceptions.InvalidModuleCode e) {
            return "Module not found, please enter a valid module code next time!";
        }

        lgr.info("api call successful, attempting to add module to timetable");

        if (isAlreadyInTimetable(info[0])) {
            return "Module " + info[0] + " : " + info[1] + " is already in your timetable!\n";
        }
        Timetable.addNewModule(info[0], info[1], lessons);
        return "Successfully added new module: " + info[0] + " : " + info[1] + '\n';
    }

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