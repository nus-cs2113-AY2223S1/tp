package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.data.Budget;
import seedu.duke.data.transaction.Expense;
import seedu.duke.data.transaction.Income;
import seedu.duke.exception.GlobalEmptyParameterException;
import seedu.duke.exception.GlobalDuplicateTagException;
import seedu.duke.exception.GlobalMissingTagException;
import seedu.duke.exception.InputTransactionInvalidAmountException;
import seedu.duke.exception.InputTransactionInvalidCategoryException;
import seedu.duke.exception.InputTransactionInvalidDateException;
import seedu.duke.exception.InputTransactionInvalidTypeException;
import seedu.duke.exception.MoolahException;
import seedu.duke.exception.HelpUnknownOptionException;
import seedu.duke.exception.InputBudgetInvalidAmountException;
import seedu.duke.exception.InputBudgetDuplicateException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.common.Constants.MIN_BUDGET_VALUE;
import static seedu.duke.common.Constants.MAX_BUDGET_VALUE;
import static seedu.duke.common.Constants.MAX_TRANSACTIONS_COUNT;
import static seedu.duke.common.Constants.MAX_AMOUNT_VALUE;
import static seedu.duke.parser.ParameterParser.parseBudgetTag;

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
            InputTransactionInvalidAmountException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    @Test
    public void execute_InvalidAmountInput_ExpectedException() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/one_dollar d/13092022 i/thank_you_boss";

        assertThrows(
            InputTransactionInvalidAmountException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    //@@author wcwy
    @Test
    public void parse_addCommandEmptyDate_exceptionThrown() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/ i/thank_you_boss";

        assertThrows(
            GlobalEmptyParameterException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    @Test
    public void parse_addCommandInvalidDateFormat_exceptionThrown() {
        AddCommand addCommand = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss";

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parse(addCommand, parametersInput)
        );
    }

    @Test
    public void checkMandatoryTagsExist_allTagsExists_expectNoError() {
        Command command = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss";
        String[] splits = parametersInput.split(" ");
        assertDoesNotThrow(
            () -> ParameterParser.checkMandatoryTagsExist(command, splits)
        );
    }

    @Test
    public void checkMandatoryTagsExist_allTagsExists_exceptionThrown() {
        Command command = new AddCommand();
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01";
        String[] splits = parametersInput.split(" ");
        assertThrows(
            GlobalMissingTagException.class,
            () -> ParameterParser.checkMandatoryTagsExist(command, splits)
        );
    }

    @Test
    public void checkDuplicateTagsNotExist_noDuplicateTagExists_expectNoError() {
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss";
        String[] splits = parametersInput.split(" ");
        assertDoesNotThrow(
            () -> ParameterParser.checkDuplicateTagsNotExist(splits)
        );
    }


    @Test
    public void checkDuplicateTagsNotExist_duplicateTagsExists_exceptionThrown() {
        String parametersInput = "t/income c/bonus a/1 d/2020-01-01 i/thank_you_boss i/thank_you_boss";
        String[] splits = parametersInput.split(" ");
        assertThrows(
            GlobalDuplicateTagException.class,
            () -> ParameterParser.checkDuplicateTagsNotExist(splits)
        );
    }

    @Test
    public void parseTypeTagForAdding_typeIsAcceptable_expectTypeReturnBack()
            throws InputTransactionInvalidTypeException {
        assertEquals(ParameterParser.parseTypeTagForAdding("income"), Income.TRANSACTION_NAME);
        assertEquals(ParameterParser.parseTypeTagForAdding("expense"), Expense.TRANSACTION_NAME);
    }

    @Test
    public void parseTypeTagForAdding_typeIsInvalid_exceptionThrown() {
        assertThrows(
            InputTransactionInvalidTypeException.class,
            () -> ParameterParser.parseTypeTagForAdding("rAnD0m")
        );
    }

    @Test
    public void parseDateTag_validDateFormat_expectParsedDate() throws InputTransactionInvalidDateException {
        assertEquals(ParameterParser.parseDateTag("02022222"), LocalDate.of(2222, 2, 2));
        assertEquals(ParameterParser.parseDateTag("29101999"), LocalDate.of(1999, 10, 29));
    }

    @Test
    public void parseDateTag_invalidDateFormat_exceptionThrown() {
        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("rAnD0m")
        );

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("10291999")
        );

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("Oct 29 1999")
        );

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("29-10-1999")
        );

        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("29-02-1999")
        );
    }

    @Test
    public void parseDateTag_emptyDateFormat_exceptionThrown() {
        assertThrows(
            InputTransactionInvalidDateException.class,
            () -> ParameterParser.parseDateTag("")
        );
    }

    @Test
    public void parseHelpOptionTag_validHelpOption_expectTrue() throws HelpUnknownOptionException {
        assertTrue(ParameterParser.parseHelpOptionTag("detailed"));
    }

    @Test
    public void parseHelpOptionTag_invalidHelpOption_exceptionThrown() {
        assertThrows(
            HelpUnknownOptionException.class,
            () -> ParameterParser.parseHelpOptionTag("rAnD0m")
        );
    }

    @Test
    public void parseHelpOptionTag_emptyHelpOption_exceptionThrown() {
        assertThrows(
            HelpUnknownOptionException.class,
            () -> ParameterParser.parseHelpOptionTag("")
        );
    }

    @Test void parseBudgetTag_validBudget_expectBudgetParsedToLong() throws MoolahException {
        assertEquals(parseBudgetTag("1000000000"), Long.parseLong("1000000000"));
        assertEquals(parseBudgetTag(Integer.toString(MIN_BUDGET_VALUE)), Long.parseLong("1"));
        assertEquals(parseBudgetTag(Long.toString(MAX_BUDGET_VALUE)), (long) MAX_TRANSACTIONS_COUNT * MAX_AMOUNT_VALUE);
    }

    @Test
    public void parseBudgetTag_budgetContainsAlphabets_exceptionThrown() {
        assertThrows(
            InputBudgetInvalidAmountException.class,
            () -> ParameterParser.parseBudgetTag("0xffffffff")
        );
    }

    @Test
    public void parseBudgetTag_budgetContainsSymbols_exceptionThrown() {
        assertThrows(
            InputBudgetInvalidAmountException.class,
            () -> ParameterParser.parseBudgetTag("1000000000-5")
        );
    }

    @Test
    public void parseBudgetTag_budgetOutOfAcceptedRange_exceptionThrown() {
        assertThrows(
            InputBudgetInvalidAmountException.class,
            () -> ParameterParser.parseBudgetTag(Integer.toString(MIN_BUDGET_VALUE - 1))
        );

        assertThrows(
            InputBudgetInvalidAmountException.class,
            () -> ParameterParser.parseBudgetTag(Long.toString(MAX_BUDGET_VALUE + 1))
        );
    }

    @Test
    public void parseBudgetTag_budgetToSetSameAsCurrentBudget_exceptionThrown() {
        String budgetAmount = Long.toString(Budget.getBudget());
        assertThrows(
            InputBudgetDuplicateException.class,
            () -> ParameterParser.parseBudgetTag(budgetAmount)
        );
    }


}
