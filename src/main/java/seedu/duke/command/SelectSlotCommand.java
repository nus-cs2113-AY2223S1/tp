package seedu.duke.command;

import java.util.List;
import java.util.Map;

import seedu.duke.model.LessonType;
import seedu.duke.model.SelectedModule;
import seedu.duke.parser.Parser;
import seedu.duke.parser.LessonTypeParser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class SelectSlotCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String FORMAT = "select /module MODULE_CODE /type LESSON_TYPE /code CLASS_NO";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": select slot for modules!\n"
            + "\tUsage:\t"
            + FORMAT
            + System.lineSeparator();
    private Map<String, String> params;
    private String moduleCode;
    private LessonType lessonType;
    private String classNo;
    private boolean successful;

    public SelectSlotCommand(String input) {
        super(input.split("\\s"));
        params = Parser.parseParams(input);
        successful = false;
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        moduleCode = params.get("module").toUpperCase();
        lessonType = LessonTypeParser.parse(params.get("type"));
        classNo = params.get("code").toUpperCase();
        // TODO validate params
        List<SelectedModule> modules = state.getSelectedModulesList();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getModule().moduleCode.equals(moduleCode)) {
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

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return null;
    }
}
