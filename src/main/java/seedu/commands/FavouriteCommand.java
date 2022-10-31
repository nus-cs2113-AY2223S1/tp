package seedu.commands;

import java.io.IOException;
import java.util.ArrayList;

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
                StringBuilder content = new StringBuilder();
                ArrayList<String> validIDs = new ArrayList<>();
                String[] words = argument.trim().split("\\s+");
                boolean isValid = true;
                boolean hasDuplicate = false;
                for (String word : words) {
                    if (carparkList.isCarparkValid(word)) {
                        if (Favourite.getFavouriteList().stream().noneMatch(word::equalsIgnoreCase)
                                && validIDs.stream().noneMatch(word::equalsIgnoreCase)) {
                            Carpark result = carparkList.findCarpark(word);
                            String carparkId = result.getCarparkId();
                            validIDs.add(carparkId);
                            content.append(carparkId).append(" ");
                            result.setFavourite(true);
                        } else {
                            hasDuplicate = true;
                        }
                    } else {
                        isValid = false;
                    }
                }
                if (hasDuplicate) {
                    Ui.printError(new DuplicateCarparkException());
                }
                if (!isValid) {
                    Ui.print("Some values were invalid. Invalid values were skipped.\n");
                }
                try {
                    setFavourite(validIDs);
                } catch (DuplicateCarparkException e) {
                    Ui.printError(e);
                }
                if (!validIDs.isEmpty()) {
                    return new CommandResult("Added Carpark " + content + "to favourites!");
                } else {
                    return new CommandResult("Nothing to add to favourites!");
                }
            }
        } catch (NoCarparkFoundException | NoFileFoundException | InvalidFormatException | IOException e) {
            return new CommandResult(e.getMessage());
        } catch (FileWriteException fileWriteException) {
            return new CommandResult("Error in setting " + argument + " as favourite.");
        }
    }

    /**
     * Inserts carparks into the favourite list and writes it into the favouriteList.txt
     *
     * @param carparkId Carpark IDs to favourite.
     * @throws FileWriteException        If unable to write to favourite.txt file.
     * @throws DuplicateCarparkException If carpark ID is already in favourites.
     */
    public void setFavourite(ArrayList<String> carparkId) throws FileWriteException, DuplicateCarparkException {
        boolean hasDuplicate = false;
        for (String id : carparkId) {
            boolean containsSearchStr = Favourite.getFavouriteList().stream().anyMatch(id::equalsIgnoreCase);
            if (containsSearchStr) {
                hasDuplicate = true;
            }
            Favourite.getFavouriteList().add(id);
        }
        favourite.writeFavouriteList();
        if (hasDuplicate) {
            throw new DuplicateCarparkException();
        }
    }
}
