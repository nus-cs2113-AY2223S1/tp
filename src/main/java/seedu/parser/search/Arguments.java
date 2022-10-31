package seedu.parser.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import seedu.exception.DashedExceptionNotInFrontException;

public class Arguments {
    ArrayList<String> dashedArgs = new ArrayList<>();
    Sentence arguments;
    public Arguments(String argString) throws DashedExceptionNotInFrontException {
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
                    throw new DashedExceptionNotInFrontException();
                }
                dashedArgs.add(arg.substring(1));
            } else {
                dashes = false;
                noDashArgs.add(arg.trim());
            }
            arguments = new Sentence(noDashArgs);
        }
    }

    public ArrayList<String> getDashedArgs() {
        return dashedArgs;
    }

    public Sentence getArguments() {
        return arguments;
    }

    public int getDashedArgsCount() {
        return dashedArgs.size();
    }

    public int getNonDashedArgumentCount() {
        return arguments.getWords().size();
    }
}
