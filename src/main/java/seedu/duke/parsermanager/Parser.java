package seedu.duke.parsermanager;

import seedu.duke.command.Command;
import seedu.duke.exception.ClientAlreadyPairedException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.EmptyDetailException;
import seedu.duke.exception.ExistingPairException;
import seedu.duke.exception.ExtraParametersException;
import seedu.duke.exception.IncorrectFlagOrderException;
import seedu.duke.exception.InvalidBudgetFormatException;
import seedu.duke.exception.InvalidContactNumberException;
import seedu.duke.exception.InvalidEmailException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidPriceFormatException;
import seedu.duke.exception.InvalidSingaporeAddressException;
import seedu.duke.exception.MissingFlagException;
import seedu.duke.exception.NoExistingPairException;
import seedu.duke.exception.NotIntegerException;

public abstract class Parser {
    public abstract Command parseCommand() throws EmptyDescriptionException, InvalidSingaporeAddressException,
            MissingFlagException, IncorrectFlagOrderException, InvalidPriceFormatException, EmptyDetailException,
            InvalidContactNumberException, InvalidEmailException, InvalidBudgetFormatException, InvalidIndexException,
            NotIntegerException, ClientAlreadyPairedException, ExistingPairException, NoExistingPairException,
            ExtraParametersException;
}
