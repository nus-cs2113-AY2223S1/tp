package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.data.TransactionList;

/**
 * Represents an edit command object that will execute the operations for Edit command.
 */
public class EditCommand extends Command {

    // The command word used to trigger the execution of Moolah Manager's operations.
    public static final String COMMAND_WORD = "EDIT";
    // The description for the usage of command.
    public static final String COMMAND_DESCRIPTION = "To edit a specific entry in the list of transactions.";
    // The guiding information for the usage of command.
    public static final String COMMAND_USAGE = "Usage: "
            + "edit e/ENTRY [t/TYPE] [c/CATEGORY] [a/AMOUNT] [d/DATE] [i/DESCRIPTION]";
    // The formatting information for the parameters used by the command.
    public static final String COMMAND_PARAMETERS_INFO = "Parameters information: \n"
            + "ENTRY: The entry number of the transaction. "
            + "Type \"list\" to list all the entry numbers of transaction.\n"
            + "(Optional) TYPE: The type of transaction. Only \"income\" or \"expense\" is accepted.\n"
            + "(Optional) CATEGORY: A category for the transaction. Only string containing alphabets is accepted.\n"
            + "(Optional) AMOUNT: Value of the transaction in numerical form. "
            + "Only integer within 0 and 10000000 is accepted.\n"
            + "(Optional) DATE: Date of the transaction. The format must be in \"yyyyMMdd\".\n"
            + "(Optional) DESCRIPTION: More information regarding the transaction, written without any space.";

    // Basic help description
    public static final String COMMAND_HELP = "Command Word: " + COMMAND_WORD + "\n"
            + COMMAND_DESCRIPTION + "\n"
            + COMMAND_USAGE + "\n";
    // Detailed help description
    public static final String COMMAND_DETAILED_HELP = COMMAND_HELP + COMMAND_PARAMETERS_INFO + "\n";

    private String input;

    public EditCommand() {
    }

    public EditCommand(String input) {
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
        /*
        Checks if userInput is in the correct input format by further parsing,
        before the entry in the arraylist
        */
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
