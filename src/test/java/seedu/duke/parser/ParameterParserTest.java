package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.exception.AddTransactionInvalidAmountException;
import seedu.duke.exception.InputTransactionInvalidCategoryException;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.EmptyParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterParserTest {

    //@@author chinhan99
    @Test
    public void execute_InvalidCategory_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/expense c/f0od a/20 d/13092022 i/yummy";

        assertThrows(
            InputTransactionInvalidCategoryException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }


    @Test
    public void execute_InvalidNegativeAmount_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/expense c/food a/-20 d/13092022 i/isThisAnIncome";

        assertThrows(
            AddTransactionInvalidAmountException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    @Test
    public void execute_InvalidAmountInput_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/one_dollar d/13092022 i/thank_you_boss";

        assertThrows(
            AddTransactionInvalidAmountException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    //@@author wcwy
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
