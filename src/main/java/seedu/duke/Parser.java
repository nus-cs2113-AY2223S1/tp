package seedu.duke;

import java.util.ArrayList;

public class Parser {
    static final boolean EXIT = false;
    static final boolean CONTINUE = true;


    /**
     * parses the user input and processes it with the transactions arraylist.
     *
     * @param inData       the user input .
     * @param transactions the array which would be operated on.
     * @return EXIT if input equals "bye", else return CONTINUE.
     */

    public static boolean processInput(String inData, ArrayList<Transaction> transactions) {

        if (inData.equals("help")) {
            Ui.showHelpList();
        } else if (inData.equals("list")) {
            //prints all transactions if input is equal to "list"

        } else if (inData.equals("purge")) {
            // show confirmation prompt before deleting all transactions
        } else if (inData.equals(("bye"))) {
            Ui.showExitMessage();
            return EXIT; //exit loop
        } else if (inData.isBlank() || inData.isEmpty()) {
            Ui.showWrongCommand();
        } else if (inData.contains(" ")) {
            //further parse the user input for user transaction commands
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
                // for invalid commands
                Ui.showWrongCommand();
            }
        } else {
            //for any single-word inData , which are not valid commands.
            Ui.showWrongCommand();
        }
        return CONTINUE;
    }
}
