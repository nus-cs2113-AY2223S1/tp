package seedu.duke.command;

import seedu.duke.exceptions.YamomException;
import seedu.duke.parser.Parser;
import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * Provide a concise help guide to inform user of commands available and how to use them.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = "help";
    public static final String COMMAND_DESCRIPTION =
            "List out all commands and their respective usages in YAMOM.";
    public static final String MESSAGE_COMMAND = 
            "Here are all the commands available in YAMOM!" + System.lineSeparator();
    public static final String MESSAGE_FORMAT_NOTE = System.lineSeparator() + "Note: "
            + "[ ] are required elements,\n"
            + "      < > are optional elements,\n"
            + "       |  denotes either of the arguments can be used.";

    public static final String MESSAGE_USAGE = System.lineSeparator() + "Usage :";
    public static final String USER_GUIDE_LINK = "https://ay2223s1-cs2113-f11-3.github.io/tp/";
    public static final String MESSAGE_USER_GUIDE_REFERENCE = 
            "For more detailed guide, please visit " + USER_GUIDE_LINK;

    public static final int HEADING_INDENT = 8;

    public HelpCommand(String[] input) throws YamomException {
        super(input);
        Parser.singleWordCommandError(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage(MESSAGE_COMMAND);
        ui.addMessage(getAllCommandDescriptions());
        ui.addMessage(MESSAGE_USAGE);
        ui.addMessage(getAllCommandUsage(), true);
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

    private String formatCommandDescription(String keyword, String description) {
        return StringUtils.rightPad(keyword, HEADING_INDENT) + " : " + description;
    }

    private ArrayList<String> getAllCommandDescriptions() {
        ArrayList<String> list = new ArrayList<>();

        list.add(formatCommandDescription(
                AddModuleCommand.COMMAND_WORD, AddModuleCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                ByeCommand.COMMAND_WORD, ByeCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                ExportCommand.COMMAND_WORD, ExportCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                HelpCommand.COMMAND_WORD, HelpCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                ImportCommand.COMMAND_WORD, ImportCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                InfoCommand.COMMAND_WORD, InfoCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                ListCommand.COMMAND_WORD, ListCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                RemoveModuleCommand.COMMAND_WORD, RemoveModuleCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                SearchModuleCommand.COMMAND_WORD, SearchModuleCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                SelectSlotCommand.COMMAND_WORD, SelectSlotCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                SelectSemesterCommand.COMMAND_WORD, SelectSemesterCommand.COMMAND_DESCRIPTION));
        list.add(formatCommandDescription(
                TimetableCommand.COMMAND_WORD, TimetableCommand.COMMAND_DESCRIPTION));

        return list;
    }

    private ArrayList<String> getAllCommandUsage() {
        ArrayList<String> commandUsages = new ArrayList<>();

        commandUsages.add(AddModuleCommand.COMMAND_USAGE);
        commandUsages.add(ByeCommand.COMMAND_USAGE);
        commandUsages.add(ExportCommand.COMMAND_USAGE);
        commandUsages.add(HelpCommand.COMMAND_USAGE);
        commandUsages.add(ImportCommand.COMMAND_USAGE);
        commandUsages.add(InfoCommand.COMMAND_USAGE);
        commandUsages.add(ListCommand.COMMAND_USAGE);
        commandUsages.add(RemoveModuleCommand.COMMAND_USAGE);
        commandUsages.add(SearchModuleCommand.COMMAND_USAGE);
        commandUsages.add(SelectSlotCommand.COMMAND_USAGE);
        commandUsages.add(SelectSemesterCommand.COMMAND_USAGE);
        commandUsages.add(TimetableCommand.COMMAND_USAGE);

        return commandUsages;
    }
}
