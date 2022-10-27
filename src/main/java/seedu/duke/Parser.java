
package seedu.duke;


import seedu.duke.commands.*;
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
    public static Module parseFileInput(String input) throws InvalidInputFormatException, InvalidInputContentException {
        String[] splitText = input.split(" ");
        String course = splitText[0].substring(2);
        String semesterTaken = splitText[1].substring(2);
        int mcs = Integer.parseInt(splitText[2].substring(3));
        String grade = splitText[3].substring(2);
        Module module = new Module(course,semesterTaken,grade,mcs);
        return module;
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
        case "find":
            return new Find(input);
        case "check":
            return new Check(input);
        case "help":
            return new Help();
        case "exit":
            return new Exit();
        default:
            throw new InvalidCommandWordException();
        }
    }
}
