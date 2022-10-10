package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_OPTION;

/**
 * Represents a help command object that will execute the operations for Help command.
 */
public class HelpCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "HELP";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To display a list of available commands "
            + "with their respective expected parameters."
            + LINE_SEPARATOR
            + "Type \"help o/detailed\" for a detailed version of all parameters used.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: help [o/detailed]";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "(Optional) o/detailed - Detailed version of guide.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD
            + LINE_SEPARATOR
            + COMMAND_DESCRIPTION
            + LINE_SEPARATOR
            + COMMAND_USAGE
            + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + "\n";

    private boolean isDetailed;

    public HelpCommand() {
        this.isDetailed = false;
    }

    @Override
    public void setIsDetailedOption(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    /**
     * Gets the optional tags of the command.
     *
     * @return A string array containing all optional tags
     */
    @Override
    public String[] getOptionalTags() {
        String[] optionalTags = new String[]{
            COMMAND_TAG_HELP_OPTION
        };
        return optionalTags;
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        String helpMessage = "";
        if (isDetailed) {
            helpMessage = generateDetailedHelp();
        } else {
            helpMessage = generateBasicHelp();
        }

        Ui.showHelp(helpMessage);
    }

    private String generateBasicHelp() {
        String helpMessage = HelpCommand.COMMAND_HELP + LINE_SEPARATOR
                + AddCommand.COMMAND_HELP + LINE_SEPARATOR
                + ListCommand.COMMAND_HELP + LINE_SEPARATOR
                + FindCommand.COMMAND_HELP + LINE_SEPARATOR
                + StatsCommand.COMMAND_HELP + LINE_SEPARATOR
                + EditCommand.COMMAND_HELP + LINE_SEPARATOR
                + DeleteCommand.COMMAND_HELP + LINE_SEPARATOR
                + PurgeCommand.COMMAND_HELP + LINE_SEPARATOR
                + ByeCommand.COMMAND_HELP;

        return helpMessage;
    }

    private String generateDetailedHelp() {
        String helpMessage = HelpCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + AddCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + ListCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + FindCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + StatsCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + EditCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + DeleteCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + PurgeCommand.COMMAND_DETAILED_HELP + LINE_SEPARATOR
                + ByeCommand.COMMAND_DETAILED_HELP;

        return helpMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
