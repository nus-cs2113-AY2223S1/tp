package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Exceptions;
import seedu.duke.Timetable;
import seedu.duke.commands.nusmodsapi.Nusmods;
import seedu.duke.module.lessons.Lesson;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandAddModule {
    public static String addModule(Timetable timetable, String currentSemester) {
        Nusmods mod = new Nusmods();
        List<Lesson> lessons;
        String[] info = new String[3];
        Logger logger = Logger.getLogger(Duke.class.getName());

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
            logger.log(Level.INFO, "due to unsuccessful api call");
            return "Module not found, please enter a valid module code next time!";
        }

        assert info[0] != null : "Module code must be filled and cannot be null";
        assert info[1] != null : "Module name must be filled and cannot be null";
        assert info[2] != null : "Module details must be filled and cannot be null";

        timetable.addNewModule(info[0], info[1], info[2], lessons);

        return "Successfully added new module: " + info[0] + " : " + info[1];
    }
}
