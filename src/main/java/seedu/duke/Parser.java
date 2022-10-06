package seedu.duke;

import seedu.duke.command.*;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.UnknownInputException;

public class Parser {

    /**
     * Parses the user input and deal with any input error returned.
     *
     * @param inData       The user input.
     * @return IS_EXIT If input equals "bye", else return IS_CONTINUE.
     * @throws MoolahException Any command input exceptions captured by Moolah Manager.
     */
    public static Command parse(String inData) throws MoolahException{
        Command command = null;
        String[] splitInput = inData.split(" ", 2);;

        // list commands duke to list all the tasks stored and their completion status
        // try at the start cos of the errors possibly
        switch (splitInput[0]) {
            case "help":
                command = new HelpCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "purge":
                command = new PurgeCommand();
                break;
            case "delete":
                command = new DeleteCommand(splitInput[1]);
                break;
            case "add":
                command = new AddCommand(splitInput[1]);
                break;
            case "edit":
                command = new EditCommand(splitInput[1]);
                break;
            case "bye":
                command = new ByeCommand();
                break;
            default:
                Ui.showInvalidCommand(); // if u still want this
                throw new UnknownInputException();
        }
        return command;
    }
}
