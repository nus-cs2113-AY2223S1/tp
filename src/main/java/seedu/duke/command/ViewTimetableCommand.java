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

public class ViewTimetableCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String FORMAT = "view";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": displays current timetable!\n"
            + "Usage:\t"
            + FORMAT;
    private static final String ERROR_MESSAGE_EMPTY_TIMETABLE = "Your timetable is empty."
            + System.lineSeparator() + "Please select your modules first before viewing.";

    private boolean showFancy;
    private boolean showSimple;

    public ViewTimetableCommand(String input) {
        super(input.split("\\s+"));
        var params = Parser.parseParams(input);
        showFancy = params.containsKey("fancy");
        showSimple = params.containsKey("simple");
        // if (showFancy && showSimple) {
        //     throw new Exception("Timetable cannot be both simple and fancy!");
        // }
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        int numOfNotSelectedModules = 0;
        List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
        for (SelectedModule selectedModule: selectedModules) {
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            numOfNotSelectedModules = getNumOfNotSelectedModules(numOfNotSelectedModules, selectedSlots);
            addToLessonsList(state, lessons, selectedModule, selectedSlots);
        }
        try {
            checkForEmptyTimetable(numOfNotSelectedModules, selectedModules);
        } catch (YamomException e) {
            ui.addMessage(e.getMessage());
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

    private static int getNumOfNotSelectedModules(int numOfNotSelectedModules, Map<LessonType, String> selectedSlots) {
        if (selectedSlots.isEmpty()) {
            numOfNotSelectedModules++;
        }
        return numOfNotSelectedModules;
    }

    private static void checkForEmptyTimetable(int numOfNotSelectedModules, List<SelectedModule> selectedModules)
            throws YamomException {
        if (numOfNotSelectedModules == selectedModules.size()) {
            throw new YamomException(ERROR_MESSAGE_EMPTY_TIMETABLE);
        }
    }

    private static void addToLessonsList(State state, List<Pair<Module, RawLesson>> lessons,
                                         SelectedModule selectedModule, Map<LessonType, String> selectedSlots) {
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            Module module = selectedModule.getModule();
            int semester = state.getSemester();
            List<RawLesson> potentialLesson = module.getSemesterData(semester)
                    .getLessonsByTypeAndNo(slot.getKey(), slot.getValue());
            addValidLesson(lessons, module, potentialLesson);
        }
    }

    private static void addValidLesson(List<Pair<Module, RawLesson>> lessons, Module module,
                                       List<RawLesson> potentialLesson) {
        if (!potentialLesson.isEmpty()) {
            RawLesson lesson = potentialLesson.get(0);
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
