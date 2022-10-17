package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.StorageWriteErrorException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.command.CommandTag.COMMAND_TAG_LIST_ENTRY_NUMBER;
import static seedu.duke.common.InfoMessages.INFO_DELETE;

/**
 * Represents a delete command object that will execute the operations for Delete command.
 */
public class DeleteCommand extends Command {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    // The command word used to trigger the execution of Moolah Manager's operations
    public static final String COMMAND_WORD = "DELETE";
    // The description for the usage of command
    public static final String COMMAND_DESCRIPTION = "To delete a specific entry in the list of transactions.";
    // The guiding information for the usage of command
    public static final String COMMAND_USAGE = "Usage: delete e/ENTRY";
    // The formatting information for the parameters used by the command
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information:"
            + LINE_SEPARATOR
            + "ENTRY: The entry number of the transaction. "
            + "Type \"list\" to list all the entry numbers of transaction.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + LINE_SEPARATOR
            + COMMAND_DESCRIPTION + LINE_SEPARATOR + COMMAND_USAGE + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;

    //@@author brian-vb
    // The optional tags that may exist in the user input
    private static final Logger deleteLogger = Logger.getLogger(DeleteCommand.class.getName());

    private int entryNumber;

    //@@author paullowse
    public DeleteCommand() {
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags.
     */
    @Override
    public String[] getMandatoryTags() {
        // The mandatory tags that must exist in the user input
        String[] mandatoryTags = new String[]{COMMAND_TAG_LIST_ENTRY_NUMBER};

        return mandatoryTags;
    }

    @Override
    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    //@@author brian-vb

    /**
     * Executes the "delete" command. Checks and parses the necessary parameters before deleting transaction.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     * @throws InvalidIndexException If the index inputted is invalid.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before adding entry to arraylist
        */
        try {
            deleteLogger.setLevel(Level.WARNING);
            deleteLogger.log(Level.INFO, "Delete Command checks whether the index is valid "
                    + "before executing the command.");
            boolean isInputValid = true;
            int index = entryNumber;
            int numberOfTransactions;
            numberOfTransactions = transactions.size();
            if ((index > numberOfTransactions) || (index <= 0)) {
                isInputValid = false;
            }
            assert index > 0;
            if (isInputValid) {
                String transaction = TransactionList.deleteTransaction(index);
                Ui.showTransactionAction(INFO_DELETE.toString(), transaction);
                deleteLogger.log(Level.INFO, "The requested transaction has been deleted "
                        + "and the UI should display the confirmation message respectively.");
                storage.writeToFile(transactions.getTransactions());
            } else {
                deleteLogger.log(Level.WARNING, "InvalidIndexException thrown when the index "
                        + "is invalid.");
                throw new InvalidIndexException();
            }
        } catch (IOException e) {
            throw new StorageWriteErrorException();
        }
        deleteLogger.log(Level.INFO, "This is the end of the delete command.");
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
