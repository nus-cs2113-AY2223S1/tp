package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.RawLesson;
import seedu.duke.model.Timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StringBuilder;

import org.apache.commons.lang3.tuple.Pair;

import seedu.duke.model.Module;

/**
 * Get all module details by module code. Display all tutorial and labs session in timetable format.
 */
public class GetModuleCommand extends Command {
    Module module;
    public static final String COMMAND_WORD = "get";
    public static final String COMMAND_USAGE = "get [EXACT_MODULE_CODE]";
    public static final String COMMAND_DESCRIPTION = "Show all details of a module.";
    public static final String MODULE_NOT_FOUND = "Module not found! Please enter a valid module code! Try searching "
            + "if you do not remember the exact module code.";
    private static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;
    private static final int DESCRIPTION_SIZE = 80;
    // private static final String DESCRIPTION_INDENTATION = System.lineSeparator() + "\t\t\t\t\t";
    private Logger logger;
    private static final String DESCRIPTION_INDENTATION = "\n" + "\t\t\t\t\t" + "  ";
    private static final String SUBSYSTEM_NAME = "GetModuleCommand";

    public GetModuleCommand(String[] input) throws YamomException {
        super(input);

        if (input.length < 2) {
            throw new YamomException(ERROR_WRONG_FORMAT);
        }

        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);

        if (!isModuleExist(module)) {
            throw new YamomException(MODULE_NOT_FOUND);
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.FINE, "Loading get module command, starting to get module details");

        // if field is empty, display null in ui
        ui.addMessage("Module               : " + (module.moduleCode.isEmpty() ? "Nil" : module.moduleCode));
        ui.addMessage("Module Name          : " + (module.title.isEmpty() ? "Nil" : module.title));
        ui.addMessage("Module Description   : " + (module.description.isEmpty() ? "Nil"
                : splitLongDescription(module.description)));
        ui.addMessage("Module Credit        : " + (module.moduleCredit == 0 ? "0" : module.moduleCredit));
        ui.addMessage("Department           : " + (module.department.isEmpty() ? "Nil" : module.department));
        ui.addMessage("Faculty              : " + (module.faculty.isEmpty() ? "Nil" : module.faculty));
        ui.addMessage("Workload             : " + (module.workload.toString().isEmpty() ? "Nil" : module.workload
                .toString()));
        ui.addMessage("Semester offering    : " + (module.getSemestersOffering(module).isEmpty() ? "Nil" : module
                .getSemestersOffering(module)));
        ui.addMessage("Prerequisite         : " + (module.prerequisite.toString().isEmpty() ? "Nil" : module
                .prerequisite.toString()));
        ui.addMessage("Preclusion           : " + (module.preclusion.isEmpty() ? "Nil" : module.preclusion));
        ui.addMessage("Corequisite          : " + (module.corequisite.isEmpty() ? "Nil" : module.corequisite));

        ui.displayUi();
        ui.displayDivider();
        if (isModuleOfferedInCurrentSem(module, state)) {
            logger.log(Level.FINE, "Module is offered in current semester, module timetable will be displayed");
            List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
            Pair<Module, RawLesson> lesson;
            List<RawLesson> tempLessons = module.getSemesterData(state.getSemester()).timetable;
            for (RawLesson rawLesson : tempLessons) {
                lesson = Pair.of(module, rawLesson);
                lessons.add(lesson);
            }

            Timetable timetable = new Timetable(lessons, true, false);
            ui.addMessage(timetable.toString());
        } else {
            logger.log(Level.FINE, "Module is not offered in current semester, module timetable will not be displayed");
            ui.addMessage("Module " + module.moduleCode + " is not offered in this semester"
                    + ", hence no timetable information is available due to unforseen circumstances");
        }

        ui.displayUi();
    }

    // check if module is offered in this semester
    public static boolean isModuleOfferedInCurrentSem(Module module, State state) {
        int sem = state.getSemester();
        return module.getSemestersOffering(module).contains(sem);
    }

    // Check if module input by user exists in module list. This is different from isValidModuleCode from Parser class.
    boolean isModuleExist(Module module) {
        // check if module exists in module list
        List<Module> moduleList = Module.getAll();
        return moduleList.contains(module);
    }

    //adapted from https://stackoverflow.com/questions/25853393
    private String splitLongDescription(String longDescription) {
        Pattern pattern = Pattern.compile("\\G\\s*(.{1," + DESCRIPTION_SIZE + "})(?=\\s|$)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(longDescription);

        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            stringBuilder.append(matcher.group());
            stringBuilder.append(DESCRIPTION_INDENTATION);
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return null;
    }

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }
}
