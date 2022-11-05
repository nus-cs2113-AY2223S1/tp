package seedu.duke.command;

//@author wcwy

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.HelpUnknownCommandWordException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_OPTION;
import static seedu.duke.command.CommandTag.COMMAND_TAG_HELP_QUERY;
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

    private static final Logger helpLogger = Logger.getLogger(HelpCommand.class.getName());

    private boolean isDetailed;
    private boolean hasSpecificCommand;
    private String queryCommand;

    /**
     * Instantiates the ListCommand class with required variables.
     */
    public HelpCommand() {
        this.isDetailed = false;
        this.hasSpecificCommand = false;
        this.queryCommand = "";
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
    public void isDetailed(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    /**
     * Sets a string storing the command word queried by user for the help message.
     *
     * @param queryCommand A command word queried by the user.
     */
    @Override
    public void setQueryCommand(String queryCommand) {
        this.queryCommand = queryCommand;
        this.hasSpecificCommand = true;
    }

    /**
     * Gets the optional tags of the command.
     *
     * @return A string array containing all optional tags.
     */
    @Override
    public String[] getOptionalTags() {
        String[] optionalTags = new String[]{
            COMMAND_TAG_HELP_OPTION,
            COMMAND_TAG_HELP_QUERY
        };
        return optionalTags;
    }

    /**
     * Executes the 'help' command. Help messages will be generated and displayed according to user choice.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws HelpUnknownCommandWordException If the command word queried is not a valid command.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws HelpUnknownCommandWordException {
        String helpMessage = "";
        helpLogger.setLevel(Level.SEVERE);

        // When the user provided a query tag
        if (hasSpecificCommand) {
            helpMessage = generateSpecificHelp();
            helpLogger.log(Level.INFO, "A specific help message has been retrieved for command: " + queryCommand);
        }

        // When the user does not provide a query tag
        if (!hasSpecificCommand && isDetailed) {
            helpMessage = generateDetailedHelp();
            helpLogger.log(Level.INFO, "Detailed help messages has been retrieved for all commands");
        }
        if (!hasSpecificCommand && !isDetailed) {
            helpMessage = generateBasicHelp();
            helpLogger.log(Level.INFO, "Basic help messages has been retrieved for all commands");
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
     * Retrieves the help message of the queried command and returns it.
     *
     * <p>A basic or detailed help message is returned based on the help option chosen.
     *
     * @return A string containing the help message of queried commands.
     * @throws HelpUnknownCommandWordException If the command word queried is not a valid command.
     */
    private String generateSpecificHelp() throws HelpUnknownCommandWordException {
        HelpMessages basicHelpMessage;
        HelpMessages detailedHelpMessage;
        switch (queryCommand.toUpperCase()) {
        case HelpCommand.COMMAND_WORD:
            basicHelpMessage = HelpCommand.getHelpMessage();
            detailedHelpMessage = HelpCommand.getDetailedHelpMessage();
            break;
        case AddCommand.COMMAND_WORD:
            basicHelpMessage = AddCommand.getHelpMessage();
            detailedHelpMessage = AddCommand.getDetailedHelpMessage();
            break;
        case EditCommand.COMMAND_WORD:
            basicHelpMessage = EditCommand.getHelpMessage();
            detailedHelpMessage = EditCommand.getDetailedHelpMessage();
            break;
        case ListCommand.COMMAND_WORD:
            basicHelpMessage = ListCommand.getHelpMessage();
            detailedHelpMessage = ListCommand.getDetailedHelpMessage();
            break;
        case FindCommand.COMMAND_WORD:
            basicHelpMessage = FindCommand.getHelpMessage();
            detailedHelpMessage = FindCommand.getDetailedHelpMessage();
            break;
        case StatsCommand.COMMAND_WORD:
            basicHelpMessage = StatsCommand.getHelpMessage();
            detailedHelpMessage = StatsCommand.getDetailedHelpMessage();
            break;
        case PurgeCommand.COMMAND_WORD:
            basicHelpMessage = PurgeCommand.getHelpMessage();
            detailedHelpMessage = PurgeCommand.getDetailedHelpMessage();
            break;
        case DeleteCommand.COMMAND_WORD:
            basicHelpMessage = DeleteCommand.getHelpMessage();
            detailedHelpMessage = DeleteCommand.getDetailedHelpMessage();
            break;
        case BudgetCommand.COMMAND_WORD:
            basicHelpMessage = BudgetCommand.getHelpMessage();
            detailedHelpMessage = BudgetCommand.getDetailedHelpMessage();
            break;
        case ByeCommand.COMMAND_WORD:
            basicHelpMessage = ByeCommand.getHelpMessage();
            detailedHelpMessage = ByeCommand.getDetailedHelpMessage();
            break;
        default:
            helpLogger.setLevel(Level.SEVERE);
            helpLogger.log(Level.WARNING, "GenerateSpecificHelp() is called on non-supported command word: "
                    + queryCommand);
            throw new HelpUnknownCommandWordException();
        }

        if (isDetailed) {
            return detailedHelpMessage.toString();
        } else {
            return  basicHelpMessage.toString();
        }
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
