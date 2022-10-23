package seedu.commands;

import java.util.ArrayList;

import seedu.data.Carpark;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.files.Favourite;



/**
 * Represents the unfavourite command.
 */
public class UnfavouriteCommand extends Command {
    public static final String COMMAND_WORD = "unfavourite";
    private final String argument;
    private ArrayList<String> favouriteList;
    private Favourite favourite;

    /**
     * Constructor for UnfavouriteCommand
     *
     * @param argument argument for the UnfavouriteCommand
     * @param favourite favourite class
     */
    public UnfavouriteCommand(String argument, Favourite favourite) {
        this.argument = argument;
        this.favourite = favourite;
        this.favouriteList = favourite.favouriteList;
    }

    /**
     * Executes the UnfovouriteCommand
     *
     * @return Command result of UnfavouriteCommand
     */
    public CommandResult execute() {
        try {
            Carpark result = carparkList.findCarpark(argument);
            setUnfavourite(result.getCarparkId());
            return new CommandResult("Removed Carpark " + argument + " to favourites!");
        } catch (FileWriteException | NoCarparkFoundException e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * Remove a carpark from the favourite list.
     *
     * @param carparkId Carpark ID to remove.
     * @throws FileWriteException If unable to write to favourite.txt file.
     * @throws NoCarparkFoundException If carpark ID is not in favourites.
     */
    public void setUnfavourite(String carparkId) throws FileWriteException, NoCarparkFoundException {
        boolean containsSearchStr = favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (!containsSearchStr) {
            throw new NoCarparkFoundException();
        }
        favouriteList.removeIf(value->value.equalsIgnoreCase(carparkId));
        favourite.writeFavouriteList();
    }
}


