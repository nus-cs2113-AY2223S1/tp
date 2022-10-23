package seedu.commands;

import seedu.data.Carpark;
import seedu.exception.*;
import seedu.files.Favourite;
import seedu.files.FileReader;
import seedu.files.FileStorage;
import seedu.parser.Parser;
import seedu.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static seedu.common.CommonFiles.FAVOURITE_DIRECTORY;
import static seedu.common.CommonFiles.FAVOURITE_FILE;

public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    private final String argument;
    private ArrayList<String> favouriteList;
    private Favourite favourite;

    public FavouriteCommand(String argument, Favourite favourite, ArrayList<String> favouriteList) {
        this.argument = argument;
        this.favourite =  favourite;
        this.favouriteList = favouriteList;
    }

    @Override
    public CommandResult execute() {
        try {
            if (argument.equalsIgnoreCase("list")) {
                return new CommandResult(favourite.showList());
            } else {
                Carpark result = carparkList.findCarpark(argument);
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
     * Inserts a carpark into the favourite list.
     *
     * @param carparkId Carpark ID to favourite.
     * @throws FileWriteException If unable to write to favourite.txt file.
     * @throws DuplicateCarparkException If carpark ID is already in favourites.
     */
    public void setFavourite(String carparkId) throws FileWriteException, DuplicateCarparkException {
        boolean containsSearchStr = favouriteList.stream().anyMatch(carparkId::equalsIgnoreCase);
        if (containsSearchStr) {
            throw new DuplicateCarparkException();
        }
        favouriteList.add(carparkId);
        favourite.writeFavouriteList();
    }


}