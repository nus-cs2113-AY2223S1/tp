package seedu.duke.command;

//@@author brian-vb
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.common.HelpMessages;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.common.HelpMessages.PURGE_COMMAND_BASIC_HELP;
import static seedu.duke.common.HelpMessages.PURGE_COMMAND_DETAILED_HELP;
import static seedu.duke.common.InfoMessages.INFO_PURGE;
import static seedu.duke.common.InfoMessages.INFO_PURGE_ABORT;
import static seedu.duke.common.InfoMessages.INFO_PURGE_EMPTY;
import static seedu.duke.common.InfoMessages.INFO_PURGE_WARNING;

/**
 * Represents a purge command object that will execute the operations for Purge command.
 */
public class PurgeCommand extends Command {
    //@@author brian-vb
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "PURGE";

    //@@author brian-vb
    private static final Logger purgeLogger = Logger.getLogger(AddCommand.class.getName());

    //@@author paullowse
    public PurgeCommand() {
    }

    //@@author brian-vb

    /**
     * Executes the "purge" command. Checks and parses the necessary parameters before deleting transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        purgeLogger.setLevel(Level.SEVERE);
        purgeLogger.log(Level.INFO, "Purge Command checks if there are no transactions"
                + " to be purged. If so, the command is aborted.");
        boolean check = isEmpty(transactions);
        if (check) {
            Ui.showInfoMessage(INFO_PURGE_EMPTY.toString());
            purgeLogger.log(Level.INFO, "The transactions list is found to be empty"
                    + " and the UI should display that information to the user respectively.");
            return;
        }
        Ui.showInfoMessage(INFO_PURGE_WARNING.toString());
        purgeLogger.log(Level.INFO, "The UI should display a confirmation prompt"
                + " for which the User would need to respond.");
        String input = ui.readCommand();

        if (input.equals("Y")) {
            try {
                transactions.purgeTransactions();
                assert PurgeCommand.isEmpty(transactions);
                Ui.showInfoMessage(INFO_PURGE.toString());
                purgeLogger.log(Level.INFO, "The transactions list is now empty"
                        + " and the UI should display that information to the user respectively.");
                storage.writeToFile(transactions.getTransactions());
                purgeLogger.log(Level.INFO, "The end of the Purge Command.");
            } catch (IOException e) {
                throw new StorageWriteErrorException();
            }
            return;
        }

        Ui.showInfoMessage(INFO_PURGE_ABORT.toString());
        purgeLogger.log(Level.INFO, "The user would have responded to not proceed with the command"
                + " and the UI should display that information to abort the command respectively.");
        purgeLogger.log(Level.INFO, "The end of the Purge Command.");
    }

    public static boolean isEmpty(TransactionList transactions) {
        int size = transactions.size();
        return size == 0;
    }

    //@@author wcwy

    /**
     * Retrieves the basic help message of the command.
     *
     * @return A string containing the basic help description of the command.
     */
    public static HelpMessages getHelpMessage() {
        return PURGE_COMMAND_BASIC_HELP;
    }

    /**
     * Retrieves the detailed help message of the command.
     *
     * @return A string containing the detailed help description of the command.
     */
    public static HelpMessages getDetailedHelpMessage() {
        return PURGE_COMMAND_DETAILED_HELP;
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