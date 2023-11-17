package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * List out all the modules that the user has in the timetable.
 */
public class ListCommand extends Command {
    private boolean successful;
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = "list";
    public static final String COMMAND_DESCRIPTION = 
            "List out all the selected modules and lesson slots.";
    public static final int HEADING_LENGTH = 9;
    public static final int DETAILS_INDENT = 10 + HEADING_LENGTH;

    public ListCommand(String[] input) throws YamomException {
        super(input);
        successful = false;
        Parser.singleWordCommandError(input);
    }

    private String formatWeeks(List<Integer> weeks) {
        if (weeks.size() == 0) {
            return "No info";
        }
        if (weeks.size() == 1) {
            return Integer.toString(weeks.get(0));
        }
        boolean isConsecutive = true;
        for (int i = 1; i < weeks.size(); i++) {
            if (weeks.get(i) != weeks.get(i - 1) + 1) {
                isConsecutive = false;
            }
        }
        if (isConsecutive) {
            return weeks.get(0) + " - " + weeks.get(weeks.size() - 1);
        } else {
            return weeks.stream().map(x -> Integer.toString(x)).collect(Collectors.joining(", "));
        }
    }

    private String formatSingleSlot(Module module, LessonType type, String classNo, int semester) {
        String classNoString = StringUtils.rightPad(Timetable.lessonTypeToShortString(type) + "[" + classNo + "]",
                DETAILS_INDENT - HEADING_LENGTH);
        return classNoString + module.getSemesterData(semester).getLessonsByTypeAndNo(type, classNo)
                .stream()
                .map(r -> {
                    String result = Timetable.dayToShortString(r.day);
                    result += " " + r.startTime + "-" + r.endTime;
                    if (!r.venue.isEmpty()) {
                        result += ", " + r.venue;
                    }
                    if (!r.weeks.isEmpty()) {
                        result += ", weeks: " + formatWeeks(r.weeks);
                    }
                    return result;
                })
                .collect(Collectors.joining("\n" + StringUtils.repeat(" ", DETAILS_INDENT)));
    }

    /**
     * Helper function to convert map of selected slots for each module to a list of key value pairs
     * representing available lesson types of the module and the selected slot by the user.
     * @param map of selected modules and their chosen slots
     */
    public String formatSelectedSlots(Module module, Map<LessonType, String> map, int semester) {
        String mapAsString = map.keySet().stream()
                .map(key -> formatSingleSlot(module, key, map.get(key), semester))
                .collect(Collectors.joining(
                        "\n" + StringUtils.repeat(" ", HEADING_LENGTH), 
                        StringUtils.repeat(" ", HEADING_LENGTH), 
                        "\n"));
        return mapAsString;
    }

    /**
     * Helper function to format each selected module in a list to an organised format to display to users.
     * @param module currently being processed
     * @param semester semester
     */
    public String formatSelectedModule(SelectedModule module, int semester) {
        String moduleCode = module.getModule().moduleCode;
        String moduleTitle = module.getModule().title;
        String selectedSlotsString = formatSelectedSlots(module.getModule(), module.getSelectedSlots(), semester);
        return StringUtils.rightPad(moduleCode, HEADING_LENGTH) + moduleTitle + "\n" + selectedSlotsString;
    }

    /**
     * Helper function to format list of selected modules to an organised format to display to users.
     * @param currentSelectedModules the list of all selectedModules and their selected slots for lessons
     */
    public String formatSelectedModuleList(List<SelectedModule> currentSelectedModules, int semester) {
        String result = "";
        for (var selectedModule : currentSelectedModules) {
            result += formatSelectedModule(selectedModule, semester) + System.lineSeparator();
        }
        return result;
    }

    /** This overridden execute method validates that the current list of selected module is not empty
     * and lets users know whether the list command was successful or not.
     * @param state current state of YAMOM
     * @param ui utility class of YAMOM
     * @param storage class that handles stores of Yamom
     */
    @Override
    public void execute(State state, Ui ui, Storage storage) {
        List<SelectedModule> currentSelectedModules = state.getSelectedModulesList();
        String formattedResult;
        if (!currentSelectedModules.isEmpty()) {
            formattedResult = formatSelectedModuleList(currentSelectedModules, state.getSemester());
            successful = true;
            ui.addMessage(formattedResult);
        }
        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        String outputMessage;
        if (successful) {
            outputMessage = "Here's a list of your current selected module(s)!";
        } else {
            outputMessage = "You currently have no selected module(s)!";
        }
        return outputMessage;
    }
}
