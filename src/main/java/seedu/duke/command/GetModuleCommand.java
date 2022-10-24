package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.RawLesson;
import seedu.duke.model.Timetable;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import seedu.duke.exceptions.YamomException;
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
        // if field is empty, display null in ui
        ui.addMessage("Module               : " + (module.moduleCode.isEmpty() ? "Nil" : module.moduleCode));
        ui.addMessage("Module Name          : " + (module.title.isEmpty() ? "Nil" : module.title));
        ui.addMessage("Module Description   : " + (module.description.isEmpty() ? "Nil" : module.description));
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

        ui.displayDivider();
        if (isModuleOfferedInCurrentSem(module, state)) {
            List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
            Pair<Module, RawLesson> lesson;
            List<RawLesson> tempLesson = module.getSemesterData(state.getSemester()).timetable;
            for (RawLesson rawLesson : tempLesson) {
                lesson = Pair.of(module, rawLesson);
                lessons.add(lesson);
            }

            Timetable timetable = new Timetable(lessons, true, false);
            ui.addMessage(timetable.toString());
        } else {
            ui.addMessage("Module " + module.moduleCode + " is not offered in this semester"
                    + ", hence no timetable information is available due to unforseen circumstances");
        }

        ui.displayUi();
    }

    // check if module is offered in this semester
    boolean isModuleOfferedInCurrentSem(Module module, State state) {
        int sem = state.getSemester();
        return module.getSemestersOffering(module).contains(sem);
    }

    // Check if module input by user exists in module list. This is different from isValidModuleCode from Parser class.
    boolean isModuleExist(Module module) {
        // check if module exists in module list
        List<Module> moduleList = Module.getAll();
        return moduleList.contains(module);
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
