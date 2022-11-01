package seedu.duke.command;

//@author wcwy

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;

import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_OPTION;
import static seedu.duke.common.HelpMessages.HELP_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.HELP_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;

//@@author wcwy
/**
 * Represents a help command object that will execute the operations for Help command.
 */
public class HelpCommand extends Command {
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "HELP";

    private boolean isDetailed;

    /**
     * Instantiates the ListCommand class with required variables.
     */
    public HelpCommand() {
        this.isDetailed = false;
    }

    /**
     * Sets a boolean value to indicate if the user chooses to display the help message in detail.
     *
     * <p>When the boolean isDetailed is set to true, the help command will display the detailed help messages
     * to the user.
     *
     * @param isDetailed A boolean indicating if the user chooses to display the help message in detail.
     */
    @Override
    public void setIsDetailedOption(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    /**
     * Gets the optional tags of the command.
     *
     * @return A string array containing all optional tags.
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

    /**
     * Consolidates all the basic help messages from the available commands and returns it.
     *
     * @return A string containing all basic help messages of valid commands.
     */
    private String generateBasicHelp() {
        String helpMessage = getHelpMessage().toString() + LINE_SEPARATOR
                + BudgetCommand.getHelpMessage() + LINE_SEPARATOR
                + AddCommand.getHelpMessage() + LINE_SEPARATOR
                + ListCommand.getHelpMessage() + LINE_SEPARATOR
                + FindCommand.getHelpMessage() + LINE_SEPARATOR
                + StatsCommand.getHelpMessage() + LINE_SEPARATOR
                + EditCommand.getHelpMessage() + LINE_SEPARATOR
                + DeleteCommand.getHelpMessage() + LINE_SEPARATOR
                + PurgeCommand.getHelpMessage() + LINE_SEPARATOR
                + ByeCommand.getHelpMessage();

        return helpMessage;
    }

    /**
     * Consolidates all the detailed help messages from the available commands and returns it.
     *
     * @return A string containing all detailed help messages of valid commands.
     */
    private String generateDetailedHelp() {

        String helpMessage = getDetailedHelpMessage().toString() + LINE_SEPARATOR
                + BudgetCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + AddCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + ListCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + FindCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + StatsCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + EditCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + DeleteCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + PurgeCommand.getDetailedHelpMessage() + LINE_SEPARATOR
                + ByeCommand.getDetailedHelpMessage();

        return helpMessage;
    }

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return HELP_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return HELP_COMMAND_DETAILED_HELP;
    }

    //@@author paullowse

    /**
     * Enables the program to exit when the Bye command is issued.
     *
     * @return A boolean value that indicates whether the program shall exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
