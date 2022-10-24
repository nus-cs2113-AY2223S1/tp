package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = "help";
    public static final String COMMAND_DESCRIPTION = "List out all commands and "
            + " their respective usages in YAMOM";

    private static final String MESSAGE_COMMAND = "Here are all the commands "
            + "available in YAMOM!" + System.lineSeparator();

    private static final String MESSAGE_FORMAT_NOTE = System.lineSeparator() + "Note: "
            + "[ ] are required elements, "
            + "< > are optional elements";

    private static final String MESSAGE_USAGE = System.lineSeparator() + "Usage :";
    private static final String USER_GUIDE_LINK = "https://ay2223s1-cs2113-f11-3.github.io/tp/";
    private static final String MESSAGE_USER_GUIDE_REFERENCE = "For more "
            + "detailed guide, please visit " + USER_GUIDE_LINK;

    public HelpCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage(MESSAGE_COMMAND);
        ArrayList<String> commandDescriptions = getAllCommandDescription();
        ui.addMessage(commandDescriptions);

        ui.addMessage(MESSAGE_USAGE);
        ArrayList<String> commandUsages = getAllCommandUsage();
        ui.addMessage(commandUsages, true);

        ui.addMessage(MESSAGE_FORMAT_NOTE);
        ui.addMessage(MESSAGE_USER_GUIDE_REFERENCE);

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

    private ArrayList<String> getAllCommandDescription() {
        ArrayList<String> commandDescriptions = new ArrayList<>();

        commandDescriptions.add(AddModuleCommand.getCommandDescription());
        commandDescriptions.add(DeleteModuleCommand.getCommandDescription());
        commandDescriptions.add(DisplaySelectedModuleListCommand.getCommandDescription());
        commandDescriptions.add(ExitCommand.getCommandDescription());
        commandDescriptions.add(ExportCommand.getCommandDescription());
        commandDescriptions.add(GetModuleCommand.getCommandDescription());
        commandDescriptions.add(HelpCommand.getCommandDescription());
        commandDescriptions.add(ImportCommand.getCommandDescription());
        commandDescriptions.add(SearchModuleCommand.getCommandDescription());
        commandDescriptions.add(SelectSemesterCommand.getCommandDescription());
        commandDescriptions.add(SelectSlotCommand.getCommandDescription());
        commandDescriptions.add(ViewTimetableCommand.getCommandDescription());

        return commandDescriptions;
    }

    private ArrayList<String> getAllCommandUsage() {
        ArrayList<String> commandUsages = new ArrayList<>();

        commandUsages.add(AddModuleCommand.getUsage());
        commandUsages.add(DeleteModuleCommand.getUsage());
        commandUsages.add(DisplaySelectedModuleListCommand.getUsage());
        commandUsages.add(ExitCommand.getUsage());
        commandUsages.add(ExportCommand.getUsage());
        commandUsages.add(GetModuleCommand.getUsage());
        commandUsages.add(HelpCommand.getUsage());
        commandUsages.add(ImportCommand.getUsage());
        commandUsages.add(SearchModuleCommand.getUsage());
        commandUsages.add(SelectSemesterCommand.getUsage());
        commandUsages.add(SelectSlotCommand.getUsage());
        commandUsages.add(ViewTimetableCommand.getUsage());

        return commandUsages;
    }

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }
}
