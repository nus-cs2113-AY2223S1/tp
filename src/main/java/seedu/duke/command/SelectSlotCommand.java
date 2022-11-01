package seedu.duke.command;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.parser.Parser;
import seedu.duke.parser.LessonTypeParser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class SelectSlotCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String COMMAND_USAGE = "select [ /module MODULE_CODE ] "
            + "[ /type LESSON_TYPE ] [ /code CLASS_NO ]";
    public static final String COMMAND_DESCRIPTION = "Select a module lesson slot.";
    public static final String ERROR_WRONG_FORMAT = "Wrong format given, should be "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;

    public static final String MISSING_PARAMS_KEY_OR_VALUE = "You might have missed out the Module Code, Lesson Type or"
            + " Class No.";

    public static final String SELECTION_DATA_VALIDATION_FAILED = "You might have entered the wrong module, "
            + "lesson type or class no.";
    public static final String ERROR_MODULE_DOES_NOT_EXIST = "Module does not exist!";
    public static final String ERROR_MODULE_NOT_SELECTED = 
            "You need to add the module to your timetable first. Use the add command.";
    public static final String ERROR_UNEXPECTED_PARAMETER = "Unexpected parameter: ";

    public static final String SUCCESSFUL_MESSAGE = "Slot selected successfully!";
    private Map<String, String> params;
    private String moduleCode;
    private LessonType lessonType;
    private String classNo;

    public SelectSlotCommand(String input) throws YamomException {
        super(input.split("\\s+"));
        params = Parser.parseParams(input);
        // validate params
        processParams(params);
        moduleCode = params.get("module").toUpperCase();
        if (Module.get(moduleCode) == null) {
            throw new YamomException(moduleCode + " " + ERROR_MODULE_DOES_NOT_EXIST);
        }
        try {
            lessonType = LessonTypeParser.parse(params.get("type"));
        } catch (IllegalArgumentException e) {
            throw new YamomException(e.getMessage());
        }
        classNo = params.get("code").toUpperCase();
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) throws YamomException {
        List<SelectedModule> modules = state.getSelectedModulesList();
        // check if module is not selected
        boolean moduleInSelectedList = false;
        for (SelectedModule selectedModule : modules) {
            if (selectedModule.getModule().moduleCode.equals(moduleCode)) {
                moduleInSelectedList = true;
            }
        }
        if (!moduleInSelectedList) {
            throw new YamomException(ERROR_MODULE_NOT_SELECTED + "\n\te.g. add " + moduleCode);
        }
        // check if lesson type and code are valid
        Module module = Module.get(moduleCode);
        if (!module.getSemesterData(state.getSemester()).getLessonTypes().contains(lessonType)) {
            throw new YamomException("Module " + moduleCode + " does not have a "
                    + lessonType + " component in semester " + state.getSemester());
        }
        if (module.getSemesterData(state.getSemester()).getLessonsByTypeAndNo(lessonType, classNo).isEmpty()) {
            throw new YamomException("Class code " + classNo + " does not exist for "
                    + lessonType + " for " + moduleCode + " in semester " + state.getSemester());
        }
        // find module in State
        SelectedModule selectedModule = modules
                .stream()
                .filter(m -> m.getModule().moduleCode.equals(moduleCode))
                .collect(Collectors.toList())
                .get(0);
        assert selectedModule != null : ERROR_MODULE_NOT_SELECTED;
        selectedModule.selectSlot(lessonType, classNo);
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    /**
     * Process the parameters given by the user.
     *
     * @param params the parameters given by the user for module, type and code.
     * @throws YamomException if the parameters are invalid or missing.
     */
    private void processParams(Map<String, String> params) throws YamomException {
        if (!params.containsKey("module") || params.get("module").isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT + "\n\tMissing parameter /module");
        } 
        if (!params.containsKey("type") || params.get("type").isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT + "\n\tMissing parameter /type");
        } 
        if (!params.containsKey("code") || params.get("code").isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT + "\n\tMissing parameter /code");
        }
        List<String> extraParams = params.keySet()
            .stream()
            .filter(k -> !List.of("module", "type", "code").contains(k))
            .collect(Collectors.toList());
        for (String p : extraParams) {
            throw new YamomException(ERROR_UNEXPECTED_PARAMETER + p);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return SUCCESSFUL_MESSAGE;
    }
}
