package recipeditor.parser;

import recipeditor.command.CommandResult;
import recipeditor.command.FindCommand;

public interface FlagParser {

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
            if (s.contains("-")) {
                switch (s) {
                case "-add":
                    flags[0] = FlagType.ADD;
                    commandFlagCount += 1;
                    break;
                case "-del":
                    flags[0] = FlagType.DELETE;
                    commandFlagCount += 1;
                    break;
                case "-swp":
                    flags[0] = FlagType.SWAP;
                    commandFlagCount += 1;
                    break;
                case "-chg":
                    flags[0] = FlagType.CHANGE;
                    commandFlagCount += 1;
                    break;
                case "-i":
                    flags[1] = FlagType.INGREDIENT;
                    recipeFlagCount += 1;
                    break;
                case "-s":
                    flags[1] = FlagType.STEP;
                    recipeFlagCount += 1;
                    break;
                case "-t":
                    flags[1] = FlagType.TITLE;
                    recipeFlagCount += 1;
                    break;
                case "-d":
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
        case "-i":
            flag = FlagType.INGREDIENT;
            break;
        case "-t":
            flag = FlagType.TITLE;
            break;
        default:
            new CommandResult(FindCommand.CORRECT_FORMAT);
            break;
        }
        return flag;
    }
}
