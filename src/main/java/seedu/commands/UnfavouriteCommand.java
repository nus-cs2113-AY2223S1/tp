package seedu.commands;

import java.util.ArrayList;

import seedu.data.Carpark;
import seedu.data.CarparkList;
import seedu.exception.FileWriteException;
import seedu.exception.NoCarparkFoundException;
import seedu.files.Favourite;
import seedu.ui.Ui;

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
            StringBuilder content = new StringBuilder();
            ArrayList<String> validIDs = new ArrayList<>();
            String[] words = argument.trim().split("\\s+");
            boolean isValid = true;
            boolean hasMatch = true;
            for (String word : words) {
                if (carparkList.isCarparkValid(word)) {
                    if (Favourite.getFavouriteList().stream().anyMatch(word::equalsIgnoreCase)) {
                        if (validIDs.stream().noneMatch(word::equalsIgnoreCase)) {
                            Carpark result = carparkList.findCarpark(word);
                            String carparkId = result.getCarparkId();
                            validIDs.add(carparkId);
                            content.append(carparkId).append(" ");
                            result.setFavourite(false);
                        }
                    } else {
                        hasMatch = false;
                    }
                } else {
                    isValid = false;
                }
            }
            if (!hasMatch) {
                Ui.printRed("Some carparks not found in favourite list! Skipping...\n", true);
            }
            if (!isValid) {
                Ui.printRed("Some values were invalid. Invalid values were skipped.\n", true);
            }
            setUnfavourite(validIDs);
            if (!validIDs.isEmpty()) {
                return new CommandResult("Removed Carpark " + content + "from favourites!", CommandStatus.SUCCESS);
            } else {
                return new CommandResult("Nothing to remove from favourites!", CommandStatus.FAIL);
            }
        } catch (FileWriteException e) {
            return new CommandResult(e.getMessage(), CommandStatus.FAIL);
        } catch (NoCarparkFoundException e) {
            return new CommandResult("Carpark not found in favourite list!", CommandStatus.FAIL);
        }
    }

    /**
     * Remove carparks from the favourite list.
     *
     * @param carparkId Carpark IDs to remove.
     * @throws FileWriteException If unable to write to favourite.txt file.
     * @throws NoCarparkFoundException If carpark ID is not in favourites.
     */
    public void setUnfavourite(ArrayList<String> carparkId) throws FileWriteException, NoCarparkFoundException {
        boolean hasNoCarpark = false;
        for (String id : carparkId) {
            boolean containsSearchStr = Favourite.getFavouriteList().stream().anyMatch(id::equalsIgnoreCase);
            if (!containsSearchStr) {
                hasNoCarpark = true;
            } else {
                Favourite.getFavouriteList().removeIf(value -> value.equalsIgnoreCase(id));
            }
        }
        favourite.writeFavouriteList();
        if (hasNoCarpark) {
            throw new NoCarparkFoundException();
        }
    }
}
