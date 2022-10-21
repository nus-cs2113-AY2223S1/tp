package seedu.duke.command;

import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DisplaySelectedModuleListCommand extends Command {
    private boolean successful;

    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = "list";
    public static final String HELP_DISPLAY = COMMAND_WORD
            + ": display list of all selected modules and slots!\n"
            + "Usage:\t"
            + FORMAT;

    public DisplaySelectedModuleListCommand(String[] input) {
        super(input);
        successful = false;
    }

    /**
     * Helper function to convert map of selected slots for each module to a list of key value pairs
     * representing available lesson types of the module and the selected slot by the user.
     * @param map of selected modules and their chosen slots
     */
    public String convertWithStream(Map<LessonType, String> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining("- ", "\t", "\n"));
        return mapAsString;
    }

    /**
     * Helper function to format each selected module in a list to an organised format to display to users.
     * @param module currently being processed
     * @param selectedSlots of module being processed
     */
    public String formatPrintSelectedSlots(Module module, Map<LessonType, String> selectedSlots) {
        String formattedSelectedSlots;

        String moduleCode = module.moduleCode;
        String moduleTitle = module.title;
        String selectedSlotsAsString = convertWithStream(selectedSlots);

        formattedSelectedSlots = moduleCode
                + "\t" + moduleTitle + "\n"
                + selectedSlotsAsString
        ;

        return formattedSelectedSlots;
    }

    /**
     * Helper function to format list of selected modules to an organised format to display to users.
     * @param currentSelectedModules the list of all selectedModules and their selected slots for lessons
     */
    public String formatPrintSelectedSlotsList(List<SelectedModule> currentSelectedModules) {
        String formattedSelectedSlotsList = null;

        for (var selectedModule : currentSelectedModules) {
            Module module = selectedModule.getModule();
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();

            String formattedSelectedSlots = formatPrintSelectedSlots(module, selectedSlots);
            formattedSelectedSlotsList += formattedSelectedSlots + System.lineSeparator();
        }
        return formattedSelectedSlotsList;
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
        String formattedSelectedSlotsList;

        if (!currentSelectedModules.isEmpty()) {
            formattedSelectedSlotsList = formatPrintSelectedSlotsList(currentSelectedModules);
            successful = true;
            ui.addMessage(formattedSelectedSlotsList);
            ui.displayUi();
        }

        ui.addMessage(getExecutionMessage());
        ui.displayUi();
    }

    /** This function does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /** This method indicates whether the list command was executed successfully.
     * @return outputMessage indicates to user through ui display on status of selected modules list
     */
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
