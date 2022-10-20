
package seedu.duke;


import seedu.duke.commands.Add;
import seedu.duke.commands.Delete;
import seedu.duke.commands.View;
import seedu.duke.commands.Exit;
import seedu.duke.commands.Mcs;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidCommandWordException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Parser {

    /**
     * Function to parse the input given by user
     * @param input input message given by user in Command Line Interface. Format: String
     * @return Command type based on parsed input. Format: Command
     */
    public static Command parse(String input) {
        String[] splitText = input.split(" ");
        int length = splitText[0].length();
        String content = " ";
        if (input.length() > length) {
            content = input.substring(length + 1);
        }

        Command c = null;
        try {
            c = specificCase(splitText[0], content);
        } catch (InvalidCommandWordException e) {
            UI.invalidCommandWordMessage();
        } catch (InvalidInputFormatException e) {
            UI.invalidFormatMessage();
        } catch (InvalidInputContentException e) {
            UI.invalidContentMessage();
        }

        return c;
    }

    /**
     * Function to determine type of command entered by user
     * @param commandWord The first word entered by user in CLI used to determine the type of command. Format: String
     * @param input The entire input entered by user in CLI to pass on to other functions for further actions. Format: String
     * @return Command type based on parsed input. Format: Either Add, Delete, View, Mcs, or Exit.
     * @throws InvalidCommandWordException Exception thrown when the command word is invalid
     * @throws InvalidInputFormatException Exception thrown when the input format is incorrect
     * @throws InvalidInputContentException Exception thrown when the input content is invalid.
     */
    public static Command specificCase(String commandWord, String input) throws InvalidCommandWordException,
            InvalidInputFormatException, InvalidInputContentException {
        switch (commandWord) {
        case "add":
            return new Add(input);
        case "delete":
            return new Delete(input);
        case "view":
            return new View(input);
        case "mcs":
            return new Mcs(input);
        case "exit":
            return new Exit();
        default:
            throw new InvalidCommandWordException();
        }
    }
}
