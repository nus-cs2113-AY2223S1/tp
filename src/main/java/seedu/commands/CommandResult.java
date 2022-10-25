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

    public CommandResult(String showToUser) {
        this.showToUser = showToUser;
    }

    public CommandResult(CarparkList showToUser) {
        this.showToUser = showToUser.toString();
    }


}

