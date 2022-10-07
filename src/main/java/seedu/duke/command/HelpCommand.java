package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents a help command object that will execute the operations for Help command.
 */
public class HelpCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "HELP";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To display a list of available commands "
            + "with their respective expected parameters.\n"
            + "Type \"help o/detailed\" for a detailed version of all parameters used.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: help [o/detailed]";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "(Optional) o/detailed - Detailed version of guide.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + "\n"
            + COMMAND_DESCRIPTION + "\n"
            + COMMAND_USAGE + "\n";
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + "\n";

    private String input;

    public HelpCommand() {
    }

    public HelpCommand(String input) {
        this.input = input;
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
        if (input.contains("o/detailed")) {
            helpMessage = generateDetailedHelp();
        } else {
            helpMessage = generateBasicHelp();
        }

        Ui.showHelp(helpMessage);
    }

    private String generateBasicHelp() {
        String helpMessage = HelpCommand.COMMAND_HELP + "\n"
                + AddCommand.COMMAND_HELP + "\n"
                + ListCommand.COMMAND_HELP + "\n"
                + FindCommand.COMMAND_HELP + "\n"
                + GetCommand.COMMAND_HELP + "\n"
                + EditCommand.COMMAND_HELP + "\n"
                + DeleteCommand.COMMAND_HELP + "\n"
                + PurgeCommand.COMMAND_HELP + "\n"
                + ByeCommand.COMMAND_HELP;

        return helpMessage;
    }

    private String generateDetailedHelp() {
        String helpMessage = HelpCommand.COMMAND_DETAILED_HELP + "\n"
                + AddCommand.COMMAND_DETAILED_HELP + "\n"
                + ListCommand.COMMAND_DETAILED_HELP + "\n"
                + FindCommand.COMMAND_DETAILED_HELP + "\n"
                + GetCommand.COMMAND_DETAILED_HELP + "\n"
                + EditCommand.COMMAND_DETAILED_HELP + "\n"
                + DeleteCommand.COMMAND_DETAILED_HELP + "\n"
                + PurgeCommand.COMMAND_DETAILED_HELP + "\n"
                + ByeCommand.COMMAND_DETAILED_HELP;

        return helpMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
