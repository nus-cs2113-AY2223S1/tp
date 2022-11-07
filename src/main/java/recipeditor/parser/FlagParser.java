package recipeditor.parser;

import recipeditor.command.EditCommand;
import recipeditor.exception.ExcessFlagsException;

import static recipeditor.parser.FlagType.NULL;
import static recipeditor.parser.FlagType.INDEX;
import static recipeditor.parser.FlagType.ADD;
import static recipeditor.parser.FlagType.DELETE;
import static recipeditor.parser.FlagType.SWAP;
import static recipeditor.parser.FlagType.CHANGE;
import static recipeditor.parser.FlagType.INGREDIENT;
import static recipeditor.parser.FlagType.STEP;
import static recipeditor.parser.FlagType.TITLE;
import static recipeditor.parser.FlagType.DESCRIPTION;

import recipeditor.command.CommandResult;
import recipeditor.command.FindCommand;
import recipeditor.exception.InvalidFlagException;
import recipeditor.ui.Ui;

public class FlagParser {

    private static final String FLAG_INITIAL = "-";
    private static final String DASH_DIVIDER = "-";
    private static final String ADD_FLAG = FLAG_INITIAL + "add";
    private static final String DELETE_FLAG = FLAG_INITIAL + "del";
    private static final String SWAP_FLAG = FLAG_INITIAL + "swp";
    private static final String CHANGE_FLAG = FLAG_INITIAL + "chg";
    private static final String INGREDIENT_FLAG = FLAG_INITIAL + "i";
    private static final String STEP_FLAG = FLAG_INITIAL + "s";
    private static final String TITLE_FLAG = FLAG_INITIAL + "t";
    private static final String DESCRIPTION_FLAG = FLAG_INITIAL + "d";
    private static final String INDEX_FLAG = FLAG_INITIAL + "id";
    private static final String COMMAND_TYPE = "/edit";
    private static final int STRINGS_UNTIL_FLAG_EDIT_COMMAND = 4;
    private static final int STRINGS_BEFORE_FLAG_EDIT_COMMAND = 2;
    private static final int FLAG_POSITION_OTHER_COMMAND = 2;
    private static final int STARTING_COUNT = 0;
    private static final int COMMAND_FLAG_INDEX = 0;
    private static final int RECIPE_FLAG_INDEX = 1;
    private static final int MAX_NUMBER_OF_COMMAND_FLAGS = 1;
    private static final int MAX_NUMBER_OF_RECIPE_FLAGS = 1;


    /**
     * Fina all the flags in a given command.
     *
     * @param parsedCommand the parsed command which to find flags from
     * @return the list of flags found in command
     */
    public static FlagType[] getFlags(String[] parsedCommand) throws ExcessFlagsException, InvalidFlagException {
        FlagType[] flags = {NULL, NULL};
        String commandWord = parsedCommand[0];
        int recipeFlagCount = STARTING_COUNT;
        int commandFlagCount = STARTING_COUNT;
        int index = 0;
        for (String s : parsedCommand) {
            index++;

            if (commandWord.equals(COMMAND_TYPE)) {
                if (index > STRINGS_UNTIL_FLAG_EDIT_COMMAND) {
                    break;
                }
                if (index <= STRINGS_BEFORE_FLAG_EDIT_COMMAND) {
                    continue;
                }
            } else {
                if (index != FLAG_POSITION_OTHER_COMMAND) {
                    continue;
                }
            }

            if (s.contains(DASH_DIVIDER)) {
                switch (s) {
                case ADD_FLAG:
                    flags[COMMAND_FLAG_INDEX] = ADD;
                    commandFlagCount++;
                    break;
                case DELETE_FLAG:
                    flags[COMMAND_FLAG_INDEX] = DELETE;
                    commandFlagCount++;
                    break;
                case SWAP_FLAG:
                    flags[COMMAND_FLAG_INDEX] = SWAP;
                    commandFlagCount++;
                    break;
                case CHANGE_FLAG:
                    flags[COMMAND_FLAG_INDEX] = CHANGE;
                    commandFlagCount++;
                    break;
                case INDEX_FLAG:
                    flags[RECIPE_FLAG_INDEX] = INDEX;
                    recipeFlagCount++;
                    break;
                case INGREDIENT_FLAG:
                    flags[RECIPE_FLAG_INDEX] = INGREDIENT;
                    recipeFlagCount++;
                    break;
                case STEP_FLAG:
                    flags[RECIPE_FLAG_INDEX] = STEP;
                    recipeFlagCount++;
                    break;
                case TITLE_FLAG:
                    flags[RECIPE_FLAG_INDEX] = TITLE;
                    recipeFlagCount++;
                    break;
                case DESCRIPTION_FLAG:
                    flags[RECIPE_FLAG_INDEX] = DESCRIPTION;
                    recipeFlagCount++;
                    break;
                default:
                    throw new InvalidFlagException();
                }
            }
        }

        if (recipeFlagCount > MAX_NUMBER_OF_RECIPE_FLAGS) {
            throw new ExcessFlagsException("recipe");
        }
        if (commandFlagCount > MAX_NUMBER_OF_COMMAND_FLAGS) {
            throw new ExcessFlagsException("command");
        }
        return flags;
    }

    static FlagType getRecipeFlag(String[] parsedCommand) {
        FlagType flag = null;
        switch (parsedCommand[RECIPE_FLAG_INDEX]) {
        case INGREDIENT_FLAG:
            flag = FlagType.INGREDIENT;
            break;
        case TITLE_FLAG:
            flag = FlagType.TITLE;
            break;
        default:
            new CommandResult(FindCommand.CORRECT_FORMAT);
            break;
        }
        return flag;
    }

    public static int getLastFlagIndex(String[] parsedCommand) {
        int lastIndex = 0;
        for (int i = 0; i < parsedCommand.length; i++) {
            if (parsedCommand[i].contains("-")) {
                switch (parsedCommand[i]) {
                    case INDEX_FLAG: case ADD_FLAG: case DELETE_FLAG: case SWAP_FLAG: case CHANGE_FLAG:
                    case INGREDIENT_FLAG: case STEP_FLAG: case TITLE_FLAG: case DESCRIPTION_FLAG:
                        lastIndex = i;
                        break;
                    default:
                        break;
                }
            }
        }
        return lastIndex;
    }

}
