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

public class FlagParser {

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
                case "-id":
                    flags[1] = INDEX;
                    recipeFlagCount++;
                    break;
                case "-add":
                    flags[0] = ADD;
                    commandFlagCount++;
                    break;
                case "-del":
                    flags[0] = DELETE;
                    commandFlagCount++;
                    break;
                case "-swp":
                    flags[0] = SWAP;
                    commandFlagCount++;
                    break;
                case "-chg":
                    flags[0] = CHANGE;
                    commandFlagCount++;
                    break;
                case "-i":
                    flags[1] = INGREDIENT;
                    recipeFlagCount++;
                    break;
                case "-s":
                    flags[1] = STEP;
                    recipeFlagCount++;
                    break;
                case "-t":
                    flags[1] = TITLE;
                    recipeFlagCount++;
                    break;
                case "-d":
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
}
