package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.files.Favourite;



/**
 * Represents the unfavourite command.
 */
public class UnfavouriteCommand extends Command {
    public static final String COMMAND_WORD = "unfavourite";
    private final String argument;
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
    }

    /**
     * Executes the UnfovouriteCommand
     *
     * @return Command result of UnfavouriteCommand
     */
    public CommandResult execute() {
        try {
            Carpark result = findThisCarpark(argument);
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
        boolean containsSearchStr = favourite.favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (!containsSearchStr) {
            throw new NoCarparkFoundException();
        }
        favourite.favouriteList.removeIf(value->value.equalsIgnoreCase(carparkId));
        favourite.writeFavouriteList();
    }

    /**
     * Finds the carpark with the associated carparkId
     *
     * @param searchString String of the carparkId that user has inputted.
     * @return Carpark with associated carparkId
     * @throws NoCarparkFoundException No such carparkId exists in the API.
     */
    public static Carpark findThisCarpark(String searchString) throws NoCarparkFoundException {
        if (CarparkList.CARPARK_HASH_MAP.get(searchString.toLowerCase()) == null) {
            throw new NoCarparkFoundException();
        } else {
            return CarparkList.CARPARK_HASH_MAP.get(searchString.toLowerCase());
        }
    }
}


