package seedu.parser.search;

import java.util.ArrayList;
import java.util.List;

import seedu.exception.DashedArgumentsNotInFrontException;

/**
 * A representation of command arguments for use with the {@link seedu.parser.Parser Parser} class.
 */
public class Arguments {
    private final ArrayList<String> dashedArgs = new ArrayList<>();
    private Sentence arguments;

    /**
     * Constructor for the {@link Arguments} class. Parses a string into a collection of arguments.
     *
     * @param argString Argument string to parse.
     * @throws DashedArgumentsNotInFrontException If the order of dashed arguments and regular arguments are wrong.
     */
    public Arguments(String argString) throws DashedArgumentsNotInFrontException {
        List<String> allArgs = new ArrayList<>(List.of(argString.split(" ", 0)));
        boolean dashes = true;
        ArrayList<String> noDashArgs = new ArrayList<>();
        for (String arg : allArgs) {
            if (arg.isEmpty()) {
                arguments = new Sentence();
                break;
            }
            if (arg.startsWith("-")) {
                if (!dashes) {
                    throw new DashedArgumentsNotInFrontException();
                }
                dashedArgs.add(arg.substring(1));
            } else {
                dashes = false;
                noDashArgs.add(arg.trim());
            }
            arguments = new Sentence(noDashArgs);
        }
    }

    /**
     * Getter method to retrieve dashed arguments.
     *
     * @return Dashed arguments.
     */
    public ArrayList<String> getDashedArgs() {
        return dashedArgs;
    }

    /**
     * Getter method to retrieve arguments.
     *
     * @return Arguments.
     */
    public Sentence getArguments() {
        return arguments;
    }

    /**
     * Returns number of dashed arguments.
     *
     * @return Number of dashed arguments.
     */
    public int getDashedArgsCount() {
        return dashedArgs.size();
    }
}
