package seedu.commands;

import java.io.IOException;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.DuplicateCarparkException;
import seedu.exception.FileWriteException;
import seedu.exception.InvalidFormatException;
import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.Favourite;
import seedu.ui.Ui;

/**
 * Represents the 'favourite' command to save carparks in a list.
 */
public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    public static final String COMMAND_WORD_SHORT = "fav";
    public static final int NUMBER_OF_ARGUMENTS = 1;
    private static final String SEPARATOR_STRING = "===========================================";
    private final String argument;
    private final Favourite favourite;
    private final Ui ui;

    /**
     * Constructor for FavouriteCommand
     *
     * @param argument argument for the FavouriteCommand
     * @param favourite favourite class
     */
    public FavouriteCommand(String argument, Favourite favourite, CarparkList carparkList) {
        this.argument = argument;
        this.favourite = favourite;
        this.carparkList = carparkList;
        this.ui = new Ui();
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
                favourite.updateFavouriteList(carparkList);
                favourite.writeFavouriteList();
                String content = favourite.getFavouriteListString(carparkList);
                if (content.isEmpty()) {
                    return new CommandResult("There are no favourites in the list!");
                }
                return new CommandResult(content.trim());
            } else {
                Carpark result = carparkList.findCarpark(argument);
                setFavourite(result.getCarparkId());
                result.setFavourite(true);
                return new CommandResult("Added Carpark " + argument + " to favourites!");
            }
        } catch (NoCarparkFoundException | IOException | DuplicateCarparkException | NoFileFoundException
                 | InvalidFormatException e) {
            return new CommandResult(e.getMessage());
        } catch (FileWriteException fileWriteException) {
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
        boolean containsSearchStr = Favourite.getFavouriteList().stream().anyMatch(carparkId::equalsIgnoreCase);
        if (containsSearchStr) {
            throw new DuplicateCarparkException();
        }
        Favourite.getFavouriteList().add(carparkId);
        favourite.writeFavouriteList();
    }
}
