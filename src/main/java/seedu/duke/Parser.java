package seedu.duke;

import seedu.duke.data.transaction.Transaction;

import java.util.ArrayList;

public class Parser {
    static final boolean IS_EXIT = false;
    static final boolean IS_CONTINUE = true;

    /**
     * Parses the user input and processes it with the transactions arraylist.
     *
     * @param inData The user input.
     * @param transactions The array which would be operated on.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     */
    public static boolean processInput(String inData, ArrayList<Transaction> transactions) {
        if (inData.equals("help")) {
            Ui.showHelpList();
        } else if (inData.equals("list")) {
            // Prints all transactions if input is equal to "list"

        } else if (inData.equals("purge")) {
            // Shows confirmation prompt before deleting all transactions
        } else if (inData.equals(("bye"))) {
            Ui.showExitMessage();
            // Exits loop
            return IS_EXIT;
        } else if (inData.isBlank() || inData.isEmpty()) {
            Ui.showInvalidCommand();
        } else if (inData.contains(" ")) {
            // Further parses the user input for user transaction commands
            String[] userInput = inData.split(" ");
            String command = userInput[0];
            switch (command) {
            case "delete":
                /*
                Checks if userInput is in the correct format by further parsing(e.g. such as correct entry numbers)
                before deleting the entry
                */
                break;
            case "add":
                /*
                Checks if userInput is in the correct input format by further parsing,
                before adding entry to arraylist
                */

                break;
            case "edit":
                /*
                Checks if userInput is in the correct input format by further parsing,
                before the entry in the arraylist
                */
                break;
            default:
                // For invalid commands
                Ui.showInvalidCommand();
            }
        } else {
            // For any single-word inData , which are not valid commands
            Ui.showInvalidCommand();
        }
        return IS_CONTINUE;
    }
}
