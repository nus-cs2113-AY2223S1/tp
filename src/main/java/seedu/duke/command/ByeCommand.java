package seedu.duke.command;

//@@author paullowse
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.HelpMessages.BYE_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.BYE_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_EXIT;

/**
 * Represents a bye command object that will execute the operations for Bye command.
 */
public class ByeCommand extends Command {
    //@@author paullowse
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "BYE";

    //@@author paullowse
    public ByeCommand() {
    }

    /**
     * Executes the "bye" command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        Ui.showInfoMessage(INFO_EXIT.toString());
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return BYE_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return BYE_COMMAND_DETAILED_HELP;
    }

    //@@author paullowse

    /**
     * Enables the program to exit when the Bye command is issued.
     *
     * @return A boolean value that indicates whether the program shall exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}