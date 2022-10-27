package seedu.duke.command;

import java.util.List;
import java.util.Map;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.SelectedModule;
import seedu.duke.parser.Parser;
import seedu.duke.parser.LessonTypeParser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class SelectSlotCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String COMMAND_USAGE = "select [ /module [EXACT_MODULE_CODE] "
            + "/type [LESSON_TYPE] /code [CLASS_NO] ]";
    public static final String COMMAND_DESCRIPTION = "Select a module slot";
    public static final String ERROR_WRONG_FORMAT = "Wrong format given, should be "
            + System.lineSeparator() + "\t" + COMMAND_USAGE;

    public static final String MISSING_PARAMS_KEY_OR_VALUE = "You might have missed out the Module Code, Lesson Type or"
            + " Class No.";
    private Map<String, String> params;
    private String moduleCode;
    private LessonType lessonType;
    private String classNo;
    private boolean successful;

    public SelectSlotCommand(String input) throws YamomException {
        super(input.split("\\s+"));
        params = Parser.parseParams(input);
        successful = false;

        //validate params
        processParams(params);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        moduleCode = params.get("module").toUpperCase();
        lessonType = LessonTypeParser.parse(params.get("type"));
        classNo = params.get("code").toUpperCase();

        List<SelectedModule> modules = state.getSelectedModulesList();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getModule().moduleCode.equals(moduleCode)) {
                // TODO check if lesson type and class code exists in module
                modules.get(i).selectSlot(lessonType, classNo);
                successful = true;
            }
        }
        if (successful) {
            ui.addMessage("Slot selected successfully");
        } else {
            ui.addMessage("Slot selection unsuccessful");
        }
        ui.displayUi();
    }

    /**
     * Process the parameters given by the user.
     *
     * @param params the parameters given by the user for module, type and code.
     * @throws YamomException if the parameters are invalid or missing.
     */
    private void processParams(Map<String, String> params) throws YamomException {
        if (!params.containsKey("module") || !params.containsKey("type") || !params.containsKey("code")) {
            throw new YamomException(ERROR_WRONG_FORMAT);
        }

        if (params.get("module").isEmpty() || params.get("type").isEmpty() || params.get("code").isEmpty()) {
            throw new YamomException(ERROR_WRONG_FORMAT + System.lineSeparator() + System.lineSeparator()
                    + MISSING_PARAMS_KEY_OR_VALUE);
        }
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
