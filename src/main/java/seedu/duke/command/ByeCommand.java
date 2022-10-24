package seedu.duke.command;

//@@author paullowse
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

import static seedu.duke.common.InfoMessages.INFO_EXIT;
import static seedu.duke.common.InfoMessages.LINE_SEPARATOR;


/**
 * Represents a bye command object that will execute the operations for Bye command.
 */
public class ByeCommand extends Command {
    //@@author paullowse
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "BYE";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To exit the application.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: bye";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: -NIL-";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

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