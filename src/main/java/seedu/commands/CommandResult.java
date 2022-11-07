package seedu.commands;

import seedu.data.CarparkList;

/**
 * Represents the result of a command execution
 */
public class CommandResult {

    /**
     * The message to be shown to the user.
     */
    public final String showToUser;
    public final CommandStatus status;

    /**
     * Result object to be run by the Ui class.
     *
     * @param showToUser the CarparkList class object to output.
     * @param status the status of the output.
     */
    public CommandResult(CarparkList showToUser, CommandStatus status) {
        this.showToUser = showToUser.toString();
        this.status = status;
    }

    /**
     * Result object to be run by the Ui class.
     *
     * @param showToUser the String to output.
     * @param status the status of the output.
     */
    public CommandResult(String showToUser, CommandStatus status) {
        this.showToUser = showToUser;
        this.status = status;
    }

    /**
     * Getter method for status.
     *
     * @return Status.
     */
    public final CommandStatus getStatus() {
        return status;
    }
}

