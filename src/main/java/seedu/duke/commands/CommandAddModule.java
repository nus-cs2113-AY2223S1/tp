package seedu.duke.commands;

import seedu.duke.Exceptions;
import seedu.duke.Timetable;
import seedu.duke.commands.nusmodsapi.Nusmods;
import seedu.duke.module.lessons.Lesson;
import seedu.duke.data.DataManager;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class CommandAddModule {
    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static String addModule(Timetable timetable, String currentSemester) {
        Nusmods mod = new Nusmods();
        List<Lesson> lessons;
        String[] info = new String[3];

        System.out.println("What is the module code?");
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
        Timetable.addNewModule(info[0], info[1], info[2], lessons);
        return "Successfully added new module: " + info[0] + " : " + info[1];
    }
}
