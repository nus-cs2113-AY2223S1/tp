package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.RawLesson;
import seedu.duke.model.Timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
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
    public static final String MISSING_MODULE_CODE = "Please enter a module code!";
    public static final String MODULE_NOT_FOUND = "Module not found! Please enter a valid module code! "
            + "Try searching if you do not remember the exact module code.";
    // private static final String ERROR_WRONG_FORMAT = "Wrong format, should be: " + COMMAND_USAGE;
    private static final int HEADING_LENGTH = 12;
    private static final int DESCRIPTION_SIZE = 65;
    private static final String DESCRIPTION_INDENTATION = System.lineSeparator() + Stream.<String>generate(() -> " ")
            .limit(HEADING_LENGTH + " : ".length() - 1)
            .reduce("", (a, b) -> a + b);

    public GetModuleCommand(String[] input) throws YamomException {
        super(input);

        if (input.length < 2) {
            throw new YamomException(MISSING_MODULE_CODE);
        }

        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);

        if (!isModuleExist(module)) {
            throw new YamomException(MODULE_NOT_FOUND);
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        // if field is empty, display null in ui
        addLine(ui, "Code", module.moduleCode);
        addLine(ui, "Name", module.title);
        addLine(ui, "Description", module.description);
        addLine(ui, "Credits", Integer.toString(module.moduleCredit));
        addLine(ui, "Department", module.department);
        addLine(ui, "Faculty", module.faculty);
        addLine(ui, "Workload", module.workload.toString());
        addLine(ui, "Semesters", module.getSemestersOffering().toString());
        addLine(ui, "Prerequisite", module.prerequisite);
        addLine(ui, "Preclusion", module.preclusion);
        addLine(ui, "Corequisite", module.corequisite);

        if (isModuleOfferedInCurrentSem(module, state)) {
            List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
            Pair<Module, RawLesson> lesson;
            List<RawLesson> tempLesson = module.getSemesterData(state.getSemester()).timetable;
            for (RawLesson rawLesson : tempLesson) {
                lesson = Pair.of(module, rawLesson);
                lessons.add(lesson);
            }
            addLine(ui, "Schedule", " ");
            Timetable timetable = new Timetable(lessons, false, true);
            ui.addMessage(timetable.toString());
        } else {
            addLine(ui, "Schedule", "Module " + module.moduleCode + " is not offered in this semester"
                    + ", hence no timetable information is available due to unforseen circumstances");
        }

        ui.displayUi();
    }

    private void addLine(Ui ui, String heading, String details) {
        String line = heading;
        line += Stream.<String>generate(() -> " ")
                .limit(HEADING_LENGTH - heading.length())
                .reduce("", (a, b) -> a + b);
        line += " : ";
        line += (details == null || details.isEmpty()) ? "Nil" : splitLongDescription(details); 
        ui.displayMessage(line);
    }

    // check if module is offered in this semester
    public static boolean isModuleOfferedInCurrentSem(Module module, State state) {
        int sem = state.getSemester();
        return module.getSemestersOffering().contains(sem);
    }

    // Check if module input by user exists in module list. This is different from isValidModuleCode from Parser class.
    public static boolean isModuleExist(Module module) {
        // check if module exists in module list
        List<Module> moduleList = Module.getAll();
        return moduleList.contains(module);
    }

    //adapted from https://stackoverflow.com/questions/25853393
    private String splitLongDescription(String longDescription) {
        Pattern pattern = Pattern.compile("\\G\\s*(.{1," + DESCRIPTION_SIZE + "})(?=\\s|$)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(longDescription);

        StringBuilder stringBuilder = new StringBuilder();
        if (matcher.find()) {
            stringBuilder.append(matcher.group());
        }

        while (matcher.find()) {
            stringBuilder.append(DESCRIPTION_INDENTATION);
            stringBuilder.append(matcher.group());
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
