
package seedu.duke;

import seedu.duke.commands.*;
import seedu.duke.exceptions.InvalidCommandWordException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

public class Parser {

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
