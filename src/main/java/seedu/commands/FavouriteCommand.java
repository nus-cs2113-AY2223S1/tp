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
    private static final String SEPARATOR_STRING = "===========================================";
    private final String argument;
    private final Favourite favourite;

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
                StringBuilder content = new StringBuilder();
                boolean isEmpty = true;
                for (String id : Favourite.getFavouriteList()) {
                    if (id.isEmpty()) {
                        continue;
                    }
                    if (isEmpty) {
                        isEmpty = false;
                    } else {
                        content.append("\n" + SEPARATOR_STRING + "\n");
                    }
                    assert carparkList.findCarpark(id) != null : "Could not find carpark!";
                    content.append(carparkList.findCarpark(id).toString());
                }
                if (isEmpty) {
                    return new CommandResult("There is no favourites in the list!");
                }
                return new CommandResult(content.toString().trim());
            } else {
                Carpark result = carparkList.findCarpark(argument);
                setFavourite(result.getCarparkId());
                result.setFavourite(true);
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
        boolean containsSearchStr = Favourite.getFavouriteList().stream().anyMatch(carparkId::equalsIgnoreCase);
        if (containsSearchStr) {
            throw new DuplicateCarparkException();
        }
        Favourite.getFavouriteList().add(carparkId);
        favourite.writeFavouriteList();
    }
}
