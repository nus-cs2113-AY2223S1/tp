package seedu.commands;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidFormatException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.Favourite;



/**
 * Represents the unfavourite command.
 */
public class UnfavouriteCommand extends Command {
    public static final String COMMAND_WORD = "unfavourite";
    public static final String COMMAND_WORD_SHORT = "ufav";
    private final String argument;
    private final Favourite favourite;

    /**
     * Constructor for UnfavouriteCommand
     *
     * @param argument argument for the UnfavouriteCommand
     * @param favourite favourite class
     */
    public UnfavouriteCommand(String argument, Favourite favourite, CarparkList carparkList) {
        this.argument = argument;
        this.favourite = favourite;
        this.carparkList = carparkList;
    }

    /**
     * Executes the UnfovouriteCommand
     *
     * @return Command result of UnfavouriteCommand
     */
    public CommandResult execute() {
        try {
            favourite.updateFavouriteList(carparkList);
            favourite.writeFavouriteList();
            Carpark result = carparkList.findCarpark(argument);
            setUnfavourite(result.getCarparkId());
            result.setFavourite(false);
            return new CommandResult("Removed Carpark " + argument + " from favourites!");
        } catch (NoFileFoundException e) {
            return new CommandResult(e.getMessage());
        } catch (NoCarparkFoundException e) {
            return new CommandResult("Carpark not found in favourite list!");
        } catch (FileWriteException e) {
            return new CommandResult("Error removing " + argument + " as favourite.");
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
        boolean containsSearchStr = Favourite.getFavouriteList().stream().anyMatch(carparkId::equalsIgnoreCase);
        if (!containsSearchStr) {
            throw new NoCarparkFoundException();
        }
        Favourite.getFavouriteList().removeIf(value->value.equalsIgnoreCase(carparkId));
        favourite.writeFavouriteList();
    }
}
