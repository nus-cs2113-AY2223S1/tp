
package seedu.duke;


import seedu.duke.commands.Add;
import seedu.duke.commands.Check;
import seedu.duke.commands.Clear;
import seedu.duke.commands.Command;
import seedu.duke.commands.Delete;
import seedu.duke.commands.Exit;
import seedu.duke.commands.Find;
import seedu.duke.commands.Help;
import seedu.duke.commands.Mcs;
import seedu.duke.commands.Overview;
import seedu.duke.commands.View;
import seedu.duke.exceptions.InvalidCommandWordException;
import seedu.duke.exceptions.InvalidGradeException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;
import seedu.duke.exceptions.InvalidMcException;
import seedu.duke.exceptions.InvalidOverallInputException;
import seedu.duke.exceptions.InvalidSemesterException;

public class Parser {

    /**
     * Function to parse the input given by user.
     * @param input input message given by user in Command Line Interface.
     * @return Command type based on parsed input.
     */
    public static Command parse(String input) {
        String[] splitText = input.split(" ");
        int length = splitText[0].length();
        String content = " ";
        if (input.length() > length) {
            content = input.substring(length + 1);
        }

        Command c = null;
        String message = "";
        try {
            c = specificCase(splitText[0], content);
        } catch (Exception e) {
            message += e.getMessage();
        } finally {
            System.out.print(message);
        }

        return c;
    }

    /**
     * Function to parse all input from file.
     * @param input input from the file.
     * @return returns an instance of class Module after parsing input from file.
     * @throws InvalidInputFormatException exception thrown if input format is wrong
     * @throws InvalidInputContentException exception thrown if input content is wrong
     */
    public static Module parseFileInput(String input) throws InvalidInputFormatException, InvalidInputContentException {
        String[] splitText = input.split(" ");
        String course = splitText[0].substring(2);
        String semesterTaken = splitText[1].substring(2);
        int mcs = Integer.parseInt(splitText[2].substring(3));
        String grade = splitText[3].substring(2);
        return new Module(course,semesterTaken,grade,mcs);
    }

    /**
     * Function to determine type of command entered by user.
     * @param commandWord The first word entered by user in CLI used to determine the type of command.
     * @param input The entire input entered by user in CLI to pass on to other functions for further actions.
     * @return Command type based on parsed input. Format: Either Add, Delete, View, Mcs, or Exit.
     * @throws InvalidCommandWordException Exception thrown when the command word is invalid
     * @throws InvalidInputFormatException Exception thrown when the input format is incorrect
     * @throws InvalidInputContentException Exception thrown when the input content is invalid.
     */
    public static Command specificCase(String commandWord, String input) throws InvalidCommandWordException,
            InvalidInputFormatException, InvalidInputContentException, InvalidOverallInputException {
        switch (commandWord) {
        case "add":
            return new Add(input);
        case "delete":
            return new Delete(input);
        case "view":
            return new View(input);
        case "clear":
            return new Clear(input);
        case "mcs":
            return new Mcs(input);
        case "find":
            return new Find(input);
        case "check":
            return new Check(input);
        case "help":
            return new Help();
        case "overview":
            return new Overview();
        case "exit":
            return new Exit();
        default:
            throw new InvalidCommandWordException();
        }
    }
}
