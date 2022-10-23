package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.DuplicateCarparkException;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.files.Favourite;

/**
 * Represents the 'favourite' command to save carparks in a list.
 */
public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    private final String argument;
    private Favourite favourite;

    /**
     * Constructor for FavouriteCommand
     *
     * @param argument argument for the FavouriteCommand
     * @param favourite favourite class
     */
    public FavouriteCommand(String argument, Favourite favourite) {
        this.argument = argument;
        this.favourite = favourite;
    }

    /**
     * Executes FavouriteCommand
     *
     * @return CommandResult of FavouriteCommand
     */
    @Override
    public CommandResult execute() {
        try {
            if (argument.equalsIgnoreCase("list")) {
                return new CommandResult(favourite.showList());
            } else {
                Carpark result = findThisCarpark(argument);
                setFavourite(result.getCarparkId());
                return new CommandResult("Added Carpark " + argument + " to favourites!");
            }
        } catch (NoCarparkFoundException e) {
            return new CommandResult(e.getMessage());
        } catch (DuplicateCarparkException f) {
            return new CommandResult(f.getMessage());
        } catch (FileWriteException e) {
            return new CommandResult("Error in setting " + argument + " as favourite.");
        }

    }

    /**
     * Inserts a carpark into the favourite list and writes it into the favouriteList.txt
     *
     * @param carparkId Carpark ID to favourite.
     * @throws FileWriteException        If unable to write to favourite.txt file.
     * @throws DuplicateCarparkException If carpark ID is already in favourites.
     */
    public void setFavourite(String carparkId) throws FileWriteException, DuplicateCarparkException {
        boolean containsSearchStr = favourite.favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (containsSearchStr) {
            throw new DuplicateCarparkException();
        }
        favourite.favouriteList.add(carparkId);
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
