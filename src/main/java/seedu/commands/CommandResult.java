package seedu.commands;

import seedu.data.CarparkList;

public class CommandResult {

    public final String showToUser;

    public CommandResult(String showToUser) {
        this.showToUser = showToUser;
    }

    public CommandResult(CarparkList showToUser) {
        this.showToUser = showToUser.toString();
    }


}

