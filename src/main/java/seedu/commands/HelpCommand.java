package seedu.commands;

import static seedu.common.CommonData.AUTH_FORMAT;
import static seedu.common.CommonData.EXIT_FORMAT;
import static seedu.common.CommonData.FAVOURITE_FORMAT;
import static seedu.common.CommonData.FILTER_FORMAT;
import static seedu.common.CommonData.FIND_FORMAT;
import static seedu.common.CommonData.HELP_FORMAT;
import static seedu.common.CommonData.LIST_FORMAT;
import static seedu.common.CommonData.UNFAVOURITE_FORMAT;
import static seedu.common.CommonData.UPDATE_FORMAT;

/**
 * Represents a command to display the possible commands in parKING.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute() {
        String message = "Here are the list of available commands to use! "
                + "Refer to the user guide at https://ay2223s1-cs2113-t17-4.github.io/tp/UserGuide.html for more "
                + "information.\n";
        message += HELP_FORMAT + "\n";
        message += EXIT_FORMAT + "\n";
        message += LIST_FORMAT + "\n";
        message += AUTH_FORMAT + "\n";
        message += UPDATE_FORMAT + "\n";
        message += FILTER_FORMAT + "\n";
        message += FIND_FORMAT + "\n";
        message += FAVOURITE_FORMAT + "\n";
        message += UNFAVOURITE_FORMAT;

        return new CommandResult(message);
    }
}
