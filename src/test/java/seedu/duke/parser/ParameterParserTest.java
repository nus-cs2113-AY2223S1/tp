package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.command.AddCommand;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.EmptyParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterParserTest {
    @Test
    public void parse_AddCommandEmptyDate_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/ i/thank_you_boss";

        assertThrows(
            EmptyParameterException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    @Test
    public void parse_AddCommandInvalidDateFormat_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss";

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }
}
