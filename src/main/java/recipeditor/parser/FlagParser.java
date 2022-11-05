package recipeditor.parser;

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

public class FlagParser {

    private static final String FLAG_INITIAL = "-";
    private static final String ADD_FLAG = FLAG_INITIAL + "add";
    private static final String DELETE_FLAG = FLAG_INITIAL + "del";
    private static final String SWAP_FLAG = FLAG_INITIAL + "swp";
    private static final String CHANGE_FLAG = FLAG_INITIAL + "chg";
    private static final String INGREDIENT_FLAG = FLAG_INITIAL + "i";
    private static final String STEP_FLAG = FLAG_INITIAL + "s";
    private static final String TITLE_FLAG = FLAG_INITIAL + "t";
    private static final String DESCRIPTION_FLAG = FLAG_INITIAL + "d";
    private static final String INDEX_FLAG = FLAG_INITIAL + "id";

    /**
     * Fina all the flags in a given command.
     *
     * @param parsedCommand the parsed command which to find flags from
     * @return the list of flags found in command
     */
    public static FlagType[] getFlags(String[] parsedCommand) throws ExcessFlagsException {
        FlagType[] flags = {NULL, NULL};
        int recipeFlagCount = 0;
        int commandFlagCount = 0;
        for (String s : parsedCommand) {
            if (s.contains("-")) {
                switch (s) {
                case INDEX_FLAG:
                    flags[1] = INDEX;
                    recipeFlagCount++;
                    break;
                case ADD_FLAG:
                    flags[0] = ADD;
                    commandFlagCount++;
                    break;
                case DELETE_FLAG:
                    flags[0] = DELETE;
                    commandFlagCount++;
                    break;
                case SWAP_FLAG:
                    flags[0] = SWAP;
                    commandFlagCount++;
                    break;
                case CHANGE_FLAG:
                    flags[0] = CHANGE;
                    commandFlagCount++;
                    break;
                case INGREDIENT_FLAG:
                    flags[1] = INGREDIENT;
                    recipeFlagCount++;
                    break;
                case STEP_FLAG:
                    flags[1] = STEP;
                    recipeFlagCount++;
                    break;
                case TITLE_FLAG:
                    flags[1] = TITLE;
                    recipeFlagCount++;
                    break;
                case DESCRIPTION_FLAG:
                    flags[1] = DESCRIPTION;
                    recipeFlagCount++;
                    break;
                default:
                    break;
                }
            }
        }
        if (recipeFlagCount > 1) {
            throw new ExcessFlagsException("recipe");
        }
        if (commandFlagCount > 1) {
            throw new ExcessFlagsException("command");
        }
        return flags;
    }

    static FlagType getRecipeFlag(String[] parsedCommand) {
        FlagType flag = null;
        switch (parsedCommand[1]) {
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
}
