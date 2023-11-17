package seedu.duke.command;

import org.apache.commons.lang3.tuple.Pair;
import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.RawLesson;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Display the user's timetable for that semester.
 */
public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";

    public static final String FANCY_KEYWORD = "fancy";

    public static final String SIMPLE_KEYWORD = "simple";

    public static final String COMMAND_USAGE = "timetable < /" + FANCY_KEYWORD + " | /" + SIMPLE_KEYWORD + " >";

    public static final String COMMAND_DESCRIPTION = "Display current timetable.";

    private static final String EMPTY_TIMETABLE_ERROR_MESSAGE = "Your timetable is empty."
            + System.lineSeparator() + "Please select your modules first before viewing.";

    private static final String CONFLICTING_ERROR_MESSAGE = "Timetable cannot be both simple and fancy!";

    private static final String MISSING_BACKSLASH_ERROR_MESSAGE = "Unknown command. Maybe you forgot a \"/\".";

    private static final String UNKNOWN_PARAMETER_ERROR_MESSAGE = "Unknown command. Maybe you meant \""
            + COMMAND_USAGE + "\".";

    private boolean showFancy;

    private boolean showSimple;

    public TimetableCommand(String input) throws YamomException {
        super(input.split("\\s+"));
        var params = Parser.parseParams(input);
        showFancy = params.containsKey(FANCY_KEYWORD);
        showSimple = params.containsKey(SIMPLE_KEYWORD);
        boolean isConflictingCommand = showFancy && showSimple;
        boolean hasMissingBackslash = !input.contains("/") && (input.contains(FANCY_KEYWORD)
                || input.contains(SIMPLE_KEYWORD));
        boolean unknownParametersEntered = Parser.isMultiWordsCommand(input.split("\\s+"))
                || ((!showFancy && !showSimple) && Parser.isTwoWordsCommand(input.split("\\s+")));
        if (isConflictingCommand) {
            throw new YamomException(CONFLICTING_ERROR_MESSAGE);
        } else if (hasMissingBackslash) {
            throw new YamomException(MISSING_BACKSLASH_ERROR_MESSAGE);
        } else if (unknownParametersEntered) {
            throw new YamomException(UNKNOWN_PARAMETER_ERROR_MESSAGE);
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        int semester = state.getSemester();
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
        for (SelectedModule selectedModule: selectedModules) {
            addToLessonsList(semester, lessons, selectedModule);
        }
        if (lessons.isEmpty()) {
            ui.addMessage(EMPTY_TIMETABLE_ERROR_MESSAGE);
            ui.displayUi();
            return;
        }
        
        Timetable timetable;
        if (showFancy) {
            timetable = new Timetable(lessons, true, false);
        } else if (showSimple) {
            timetable = new Timetable(lessons, false, true);
        } else {
            timetable = new Timetable(lessons);
        }
        ui.addMessage(timetable.toString());
        ui.displayUi();
    }

    /**
     * Goes through the selected lessons for <code>SelectedModule</code> and gets the potential
     * lessons.
     *
     * @param semester       The current semester.
     * @param lessons        To store all the valid lessons of all modules selected.
     * @param selectedModule The current module that lessons are selected for.
     */
    private void addToLessonsList(int semester, List<Pair<Module, RawLesson>> lessons,
                                         SelectedModule selectedModule) {
        Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            Module module = selectedModule.getModule();
            List<RawLesson> potentialLesson = module.getSemesterData(semester)
                    .getLessonsByTypeAndNo(slot.getKey(), slot.getValue());
            addValidLesson(lessons, module, potentialLesson);
        }
    }

    /**
     * Adds all the <code>RawLesson</code> that previously matches the <code>LessonType</code> and <code>ClassNo</code>.
     *
     * @param lessons         To store all the valid lessons of all modules selected.
     * @param module          The current module that lessons are selected for.
     * @param potentialLesson <code>RawLesson</code> that previously matches the <code>LessonType</code>
     *                        and <code>ClassNo</code>.
     */
    private void addValidLesson(List<Pair<Module, RawLesson>> lessons, Module module,
                                       List<RawLesson> potentialLesson) {
        for (RawLesson lesson : potentialLesson) {
            lessons.add(Pair.of(module, lesson));
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
    
}
