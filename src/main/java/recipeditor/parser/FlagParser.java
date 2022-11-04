package recipeditor.parser;

import recipeditor.command.CommandResult;
import recipeditor.command.FindCommand;

public interface FlagParser {
    String FLAG_INITIAL = "-";
    String ADD_FLAG = FLAG_INITIAL + "add";
    String DELETE_FLAG = FLAG_INITIAL + "del";
    String SWAP_FLAG = FLAG_INITIAL + "swp";
    String CHANGE_FLAG = FLAG_INITIAL + "chg";
    String INGREDIENT_FLAG = FLAG_INITIAL + "i";
    String STEP_FLAG = FLAG_INITIAL + "s";
    String TITLE_FLAG = FLAG_INITIAL + "t";
    String DESCRIPTION_FLAG = FLAG_INITIAL + "d";

    /**
     * Fina all the flags in a given command.
     *
     * @param parsedCommand the parsed command which to find flags from
     * @return the list of flags found in command
     */
    static FlagType[] getCommandAndRecipeFlags(String[] parsedCommand) {
        FlagType[] flags = new FlagType[2];
        int commandFlagCount = 0;
        int recipeFlagCount = 0;
        for (String s: parsedCommand) {
            if (s.contains(FLAG_INITIAL)) {
                switch (s) {
                case ADD_FLAG:
                    flags[0] = FlagType.ADD;
                    commandFlagCount += 1;
                    break;
                case DELETE_FLAG:
                    flags[0] = FlagType.DELETE;
                    commandFlagCount += 1;
                    break;
                case SWAP_FLAG:
                    flags[0] = FlagType.SWAP;
                    commandFlagCount += 1;
                    break;
                case CHANGE_FLAG:
                    flags[0] = FlagType.CHANGE;
                    commandFlagCount += 1;
                    break;
                case INGREDIENT_FLAG:
                    flags[1] = FlagType.INGREDIENT;
                    recipeFlagCount += 1;
                    break;
                case STEP_FLAG:
                    flags[1] = FlagType.STEP;
                    recipeFlagCount += 1;
                    break;
                case TITLE_FLAG:
                    flags[1] = FlagType.TITLE;
                    recipeFlagCount += 1;
                    break;
                case DESCRIPTION_FLAG:
                    flags[1] = FlagType.DESCRIPTION;
                    recipeFlagCount += 1;
                    break;
                default:
                    break;
                }
            }
        }
        if (commandFlagCount == 1 && recipeFlagCount == 1) {
            return flags;
        }
        return null;
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
