package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MoolahException;

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
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD
            + LINE_SEPARATOR
            + COMMAND_DESCRIPTION
            + LINE_SEPARATOR
            + COMMAND_USAGE
            + LINE_SEPARATOR;
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO
            + LINE_SEPARATOR;


    // The optional tags that may exist in the user input


    private int entryNumber;

    /**
     * Initialises the variables of the DeleteCommand class.
     */
    public DeleteCommand() {
    }

    /**
     * Gets the mandatory tags of the command.
     *
     * @return A string array containing all mandatory tags
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

    /**
     * Executes the operations related to the command.
     *
     * @param ui           An instance of the Ui class.
     * @param transactions An instance of the TransactionList class.
     * @param storage      An instance of the Storage class.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoolahException {
        /*
        Checks if userInput is in the correct input format by further parsing,
        before adding entry to arraylist
        */
        boolean isInputValid = true;
        int index = entryNumber;
        int numberOfTransactions;
        numberOfTransactions = transactions.size();
        if ((index > numberOfTransactions) || (index <= 0)) {
            isInputValid = false;
        }
        if (isInputValid) {
            String transaction = TransactionList.deleteTransaction(transactions, index);
            Ui.showTransactionAction(INFO_DELETE.toString(), transaction);
        } else {
            throw new InvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
