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

public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String COMMAND_USAGE = "timetable < /fancy | /simple >";
    public static final String COMMAND_DESCRIPTION = "Display current timetable.";

    private static final String ERROR_MESSAGE_EMPTY_TIMETABLE = "Your timetable is empty."
            + System.lineSeparator() + "Please select your modules first before viewing.";

    private boolean showFancy;
    private boolean showSimple;

    public TimetableCommand(String input) throws YamomException {
        super(input.split("\\s+"));
        var params = Parser.parseParams(input);
        showFancy = params.containsKey("fancy");
        showSimple = params.containsKey("simple");
        boolean isConflictingCommand = showFancy && showSimple;
        boolean hasMissingBackslash = !input.contains("/") && (input.contains("fancy") || input.contains("simple"));
        boolean unknownParametersEntered = input.split("\\s+").length > 2 || ((!showFancy && !showSimple)
                && !Parser.isOneWordCommand(input.split("\\s+")));
        if (isConflictingCommand) {
            throw new YamomException("Timetable cannot be both simple and fancy!");
        } else if (hasMissingBackslash) {
            throw new YamomException("Unknown command. Maybe you forgot a \"/\".");
        } else if (unknownParametersEntered) {
            throw new YamomException("Unknown command. Maybe you meant \"view\".");
        }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
        for (SelectedModule selectedModule: selectedModules) {
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            addToLessonsList(state, lessons, selectedModule, selectedSlots);
        }
        if (lessons.isEmpty()) {
            ui.addMessage(ERROR_MESSAGE_EMPTY_TIMETABLE);
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

    private void addToLessonsList(State state, List<Pair<Module, RawLesson>> lessons,
                                         SelectedModule selectedModule, Map<LessonType, String> selectedSlots) {
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            Module module = selectedModule.getModule();
            int semester = state.getSemester();
            List<RawLesson> potentialLesson = module.getSemesterData(semester)
                    .getLessonsByTypeAndNo(slot.getKey(), slot.getValue());
            addValidLesson(lessons, module, potentialLesson);
        }
    }

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
