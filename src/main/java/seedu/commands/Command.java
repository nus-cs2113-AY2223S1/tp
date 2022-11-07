package seedu.commands;

import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidCommandException;
import seedu.exception.NoCarparkFoundException;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected CarparkList carparkList;

    /**
     * Constructor for Command class.
     */
    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     * @return Result of the command.
     * @throws InvalidCommandException If there is no such command.
     * @throws NoCarparkFoundException If no such carpark is found based on carparkId input.
     * @throws FileWriteException If unable to authenticate API.
     */
    public CommandResult execute() throws InvalidCommandException, NoCarparkFoundException, FileWriteException {
        throw new InvalidCommandException();
    }
}
