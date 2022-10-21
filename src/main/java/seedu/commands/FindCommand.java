package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoCommandArgumentException;
import seedu.exception.UnneededArgumentsException;
import seedu.parser.Parser;

import java.util.HashMap;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String carparkID;

    public FindCommand(String carparkID) {
        this.carparkID = carparkID;
    }

    @Override
    public CommandResult execute() throws NoCarparkFoundException {
        final Carpark result = findCarpark(carparkID);
        return new CommandResult(result.toString());
    }


     public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        if (CarparkList.carparkHashMap.get(searchString.toLowerCase()) == null) {
            throw new NoCarparkFoundException();
        } else {
            return CarparkList.carparkHashMap.get(searchString.toLowerCase());
        }
    }
}
