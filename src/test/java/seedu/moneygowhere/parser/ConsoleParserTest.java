package seedu.moneygowhere.parser;

import org.junit.jupiter.api.Test;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandAddRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandDeleteRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditRecurringPayment;
import seedu.moneygowhere.commands.ConsoleCommandMergeExternalFile;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewRecurringPayment;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.exceptions.MoneyGoWhereException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandAddRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandDeleteRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandEditRecurringPaymentInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandMergeExternalFileInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.exceptions.parser.ConsoleParserCommandViewRecurringPaymentInvalidException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"FieldMayBeFinal", "ConstantConditions"})
class ConsoleParserTest {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT
    );

    private String defaultCurrency = "SGD";

    //region Defines JUnit test cases for command Add-Expense
    //@@author xzynos
    @Test
    void parseCommand_aeNameAmount_ccaeNameAmount() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount;

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountDateTime_ccaeNameAmountDateTime() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        LocalDateTime dateTime = LocalDateTime.parse("01/01/2022 2359", dateTimeFormatter);

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime.format(dateTimeFormatter)
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = consoleCommandAddExpense
                .getDateTime()
                .equals(dateTime);
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountDescription_ccaeNameAmountDescription() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        String description = "Test Desc";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DESCRIPTION
                + " \""
                + description
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountCategory_ccaeNameAmountCategory() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        String category = "Test Cat";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CATEGORY
                + " \""
                + category
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                .equals(category);
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountRemarks_ccaeNameAmountRemarks() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        String remarks = "Test Rem";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_REMARKS
                + " \""
                + remarks
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                .equals(remarks);
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountCurrency_ccaeNameAmountCurrency() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        String currency = "USD";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CURRENCY
                + " \""
                + currency
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountModeOfPayment_ccaeNameAmountModeOfPayment() throws
            MoneyGoWhereException {
        String name = "Exp";
        BigDecimal amount = new BigDecimal("7.80");
        String modeOfPayment = "Card";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeAllArguments_ccaeAllArguments() throws
            MoneyGoWhereException {
        String name = "Exp";
        LocalDateTime dateTime = LocalDateTime.parse("01/01/2022 2359", dateTimeFormatter);
        String description = "Test Desc";
        BigDecimal amount = new BigDecimal("7.80");
        String category = "Test Cat";
        String remarks = "Test Rem";
        String currency = "USD";
        String modeOfPayment = "Card";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime.format(dateTimeFormatter)
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DESCRIPTION
                + " \""
                + description
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CATEGORY
                + " \""
                + category
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_REMARKS
                + " \""
                + remarks
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_CURRENCY
                + " \""
                + currency
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = consoleCommandAddExpense
                .getDateTime()
                .equals(dateTime);
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                .equals(category);
        boolean isRemarkEqual = consoleCommandAddExpense
                .getRemarks()
                .equals(remarks);
        boolean isCurrencyEqual = consoleCommandAddExpense
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandAddExpense
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_aeName_ccaeInvalidException() {
        String name = "Exp";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name;

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_aeAmount_ccaeInvalidException() {
        BigDecimal amount = new BigDecimal("7.80");

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameInvalidAmount1_ccaeInvalidException() {
        String name = "Exp";
        String amount = "InvalidAmount";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameInvalidAmount2_ccaeInvalidException() {
        String name = "Exp";
        String amount = "-7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountInvalidDateTime1_ccaeInvalidException() {
        String name = "Exp";
        String amount = "7.80";
        String dateTime = "InvalidDateTime";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime
                + "\"";

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_aeNameAmountInvalidDateTime2_ccaeInvalidException() {
        String name = "Exp";
        String amount = "7.80";
        String dateTime = "29/02/2022 2359";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime
                + "\"";

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }
    //endregion

    //region Defines JUnit test cases for command View-Expense
    //@@author xzynos
    @Test
    void parseCommand_ve_ccve() throws
            MoneyGoWhereException {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE;

        ConsoleCommandViewExpense consoleCommandViewExpense = (ConsoleCommandViewExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandViewExpense
                .getExpenseIndex()
                == -1;

        assertTrue(isExpenseIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_veIndex_ccveIndex() throws
            MoneyGoWhereException {
        int expenseIndex = 0;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        ConsoleCommandViewExpense consoleCommandViewExpense = (ConsoleCommandViewExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandViewExpense
                .getExpenseIndex()
                == expenseIndex;

        assertTrue(isExpenseIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_veInvalidIndex1_ccveInvalidException() {
        String expenseIndex = "InvalidExpenseIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandViewExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_veInvalidIndex2_ccveInvalidException() {
        String expenseIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandViewExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_veInvalidIndex3_ccveInvalidException() {
        String expenseIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandViewExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }
    //endregion

    //region Defines JUnit test cases for command Delete-Expense
    //@@author xzynos
    @Test
    void parseCommand_deIndex_ccdeIndex() throws
            MoneyGoWhereException {
        int expenseIndex = 0;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        ConsoleCommandDeleteExpense consoleCommandDeleteExpense =
                (ConsoleCommandDeleteExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandDeleteExpense
                .getExpenseIndex()
                == expenseIndex;

        assertTrue(isExpenseIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_de_ccdeInvalidException() {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE;

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_deInvalidIndex1_ccdeInvalidException() {
        String expenseIndex = "InvalidExpenseIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_deInvalidIndex2_ccdeInvalidException() {
        String expenseIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_deInvalidIndex3_ccdeInvalidException() {
        String expenseIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex;

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }
    //endregion

    //region Defines JUnit test cases for command Edit-Expense
    //@@author xzynos
    @Test
    void parseCommand_eeIndexName_cceeIndexName() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String name = "Exp";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME
                + " "
                + name;

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexDateTime_cceeIndexDateTime() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        LocalDateTime dateTime = LocalDateTime.parse("01/01/2022 2359", dateTimeFormatter);

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime.format(dateTimeFormatter)
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                .equals(dateTime);
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexDescription_cceeIndexDescription() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String description = "Test Desc";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION
                + " \""
                + description
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexAmount_cceeIndexAmount() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        BigDecimal amount = new BigDecimal("7.80");

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT
                + " "
                + amount;

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexCategory_cceeIndexCategory() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String category = "Test Cat";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY
                + " \""
                + category
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                .equals(category);
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexRemarks_cceeIndexRemarks() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String remarks = "Test Rem";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS
                + " \""
                + remarks
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                .equals(remarks);
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexCurrency_cceeIndexCurrency() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String currency = "USD";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY
                + " \""
                + currency
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexModeOfPayment_cceeIndexModeOfPayment() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String modeOfPayment = "Card";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                == null;
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                == null;
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_eeAllArguments_cceeAllArguments() throws
            MoneyGoWhereException {
        int expenseIndex = 0;
        String name = "Exp";
        LocalDateTime dateTime = LocalDateTime.parse("01/01/2022 2359", dateTimeFormatter);
        String description = "Test Desc";
        BigDecimal amount = new BigDecimal("7.80");
        String category = "Test Cat";
        String remarks = "Test Rem";
        String currency = "USD";
        String modeOfPayment = "Card";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime.format(dateTimeFormatter)
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION
                + " \""
                + description
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT
                + " \""
                + amount
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CATEGORY
                + " \""
                + category
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_REMARKS
                + " \""
                + remarks
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_CURRENCY
                + " \""
                + currency
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == expenseIndex;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals(name);
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                .equals(dateTime);
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                .equals(category);
        boolean isRemarkEqual = consoleCommandEditExpense
                .getRemarks()
                .equals(remarks);
        boolean isCurrencyEqual = consoleCommandEditExpense
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandEditExpense
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isRemarkEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_ee_cceeInvalidException() {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE;

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeInvalidIndex1_cceeInvalidException() {
        String expenseIndex = "InvalidExpenseIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " \""
                + expenseIndex
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeInvalidIndex2_cceeInvalidException() {
        String expenseIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " \""
                + expenseIndex
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeInvalidIndex3_cceeInvalidException() {
        String expenseIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " \""
                + expenseIndex
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexInvalidAmount1_cceeInvalidException() {
        String expenseIndex = "0";
        String amount = "InvalidAmount";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT
                + " \""
                + amount
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexInvalidAmount2_cceeInvalidException() {
        String expenseIndex = "0";
        String amount = "-7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_AMOUNT
                + " \""
                + amount
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexInvalidDateTime1_cceeInvalidException() {
        String expenseIndex = "0";
        String dateTime = "InvalidDateTime";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    //@@author xzynos
    @Test
    void parseCommand_eeIndexInvalidDateTime2_cceeInvalidException() {
        String expenseIndex = "0";
        String dateTime = "29/02/2022 2359";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX
                + " "
                + expenseIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_EXPENSE_ARG_DATE_TIME
                + " \""
                + dateTime
                + "\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }
    //endregion

    //region Defines JUnit test cases for command Add-RecurringPayment
    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalAmount_ccarpNameIntervalAmount() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalAmountDescription_ccarpNameIntervalAmountDescription() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");
        String description = "Test Desc";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION
                + " \""
                + description
                + "\"";

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalAmountCategory_ccarpNameIntervalAmountCategory() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");
        String category = "Test Cat";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY
                + " \""
                + category
                + "\"";

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                .equals(category);
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalAmountCurrency_ccarpNameIntervalAmountCurrency() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");
        String currency = "USD";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY
                + " \""
                + currency
                + "\"";

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalAmountModeOfPayment_ccarpNameIntervalAmountModeOfPayment() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");
        String modeOfPayment = "Test Desc";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(defaultCurrency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpAllArguments_ccarpAllArguments() throws
            MoneyGoWhereException {
        String name = "RecPay1";
        int interval = 30;
        BigDecimal amount = new BigDecimal("7.80");
        String description = "Test Desc";
        String category = "Test Cat";
        String currency = "USD";
        String modeOfPayment = "Card";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION
                + " \""
                + description
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY
                + " \""
                + category
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY
                + " \""
                + currency
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandAddRecurringPayment consoleCommandAddRecurringPayment =
                (ConsoleCommandAddRecurringPayment) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandAddRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandAddRecurringPayment
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandAddRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandAddRecurringPayment
                .getCategory()
                .equals(category);
        boolean isCurrencyEqual = consoleCommandAddRecurringPayment
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandAddRecurringPayment
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arp_ccarpInvalidException() {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpName_ccarpInvalidException() {
        String name = "RecPay1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpInterval_ccarpInvalidException() {
        String interval = "30";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameInterval_ccarpInvalidException() {
        String name = "RecPay1";
        int interval = 30;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameInvalidInterval1Amount_ccarpInvalidException() {
        String name = "RecPay1";
        String interval = "InvalidInterval";
        String amount = "7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameInvalidInterval2Amount_ccarpInvalidException() {
        String name = "RecPay1";
        String interval = "-1";
        String amount = "7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameInvalidInterval3Amount_ccarpInvalidException() {
        String name = "RecPay1";
        String interval = "999999999999999999999999";
        String amount = "7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalInvalidAmount1_ccarpInvalidException() {
        String name = "RecPay1";
        int interval = 30;
        String amount = "InvalidAmount";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_arpNameIntervalInvalidAmount2_ccarpInvalidException() {
        String name = "RecPay1";
        int interval = 30;
        String amount = "-7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval
                + " -"
                + ConsoleParserConfigurations.COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        assertThrows(
                ConsoleParserCommandAddRecurringPaymentInvalidException.class, () -> ConsoleParser.parse(input)
        );
    }
    //endregion

    //region Defines JUnit test cases for command View-RecurringPayment
    //@@author xzynos
    @Test
    void parseCommand_vrp_ccvrp() throws
            MoneyGoWhereException {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT;

        ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment =
                (ConsoleCommandViewRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandViewRecurringPayment
                .getRecurringPaymentIndex()
                == -1;

        assertTrue(isRecurringPaymentIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_vrpIndex_ccvrpIndex() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        ConsoleCommandViewRecurringPayment consoleCommandViewRecurringPayment =
                (ConsoleCommandViewRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandViewRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;

        assertTrue(isRecurringPaymentIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_vrpInvalidIndex1_ccvrpInvalidException() {
        String recurringPaymentIndex = "InvalidIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandViewRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_vrpInvalidIndex2_ccvrpInvalidException() {
        String recurringPaymentIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandViewRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_vrpInvalidIndex3_ccvrpInvalidException() {
        String recurringPaymentIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandViewRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }
    //endregion

    //region Defines JUnit test cases for command Delete-RecurringPayment
    //@@author xzynos
    @Test
    void parseCommand_drpIndex_ccdrpIndex() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        ConsoleCommandDeleteRecurringPayment consoleCommandDeleteRecurringPayment =
                (ConsoleCommandDeleteRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandDeleteRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;

        assertTrue(isRecurringPaymentIndexEqual);
    }

    //@@author xzynos
    @Test
    void parseCommand_drp_ccdrpInvalidException() {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT;

        assertThrows(ConsoleParserCommandDeleteRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_drpInvalidIndex1_ccdrpInvalidException() {
        String recurringPaymentIndex = "InvalidIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandDeleteRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_drpInvalidIndex2_ccdrpInvalidException() {
        String recurringPaymentIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandDeleteRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_drpInvalidIndex3_ccdrpInvalidException() {
        String recurringPaymentIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandDeleteRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }
    //endregion

    //region Defines JUnit test cases for command Edit-RecurringPayment
    //@@author xzynos
    @Test
    void parseCommand_erpIndexName_ccerpIndexName() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String name = "RecPay1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME
                + " "
                + name;

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInterval_ccerpIndexInterval() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        int interval = 30;

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL
                + " "
                + interval;

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexDescription_ccerpIndexDescription() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String description = "Test Desc";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION
                + " \""
                + description
                + "\"";

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexAmount_ccerpIndexAmount() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        BigDecimal amount = new BigDecimal("7.80");

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT
                + " "
                + amount;

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexCategory_ccerpIndexCategory() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String category = "Test Cat";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY
                + " \""
                + category
                + "\"";

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                .equals(category);
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexCurrency_ccerpIndexCurrency() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String currency = "USD";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY
                + " \""
                + currency
                + "\"";

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                == null;

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexModeOfPayment_ccerpIndexModeOfPayment() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String modeOfPayment = "Cash";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                == null;
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == 0;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                == null;
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                == null;
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                == null;
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexAllArguments_ccerpIndexAllArguments() throws
            MoneyGoWhereException {
        int recurringPaymentIndex = 0;
        String name = "RecPay1";
        int interval = 30;
        String description = "Test Desc";
        BigDecimal amount = new BigDecimal("7.80");
        String category = "Test Cat";
        String currency = "USD";
        String modeOfPayment = "Cash";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME
                + " \""
                + name
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL
                + " \""
                + interval
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION
                + " \""
                + description
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT
                + " \""
                + amount
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY
                + " \""
                + category
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY
                + " \""
                + currency
                + "\" -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT
                + " \""
                + modeOfPayment
                + "\"";

        ConsoleCommandEditRecurringPayment consoleCommandEditRecurringPayment =
                (ConsoleCommandEditRecurringPayment) ConsoleParser.parse(input);

        boolean isRecurringPaymentIndexEqual = consoleCommandEditRecurringPayment
                .getRecurringPaymentIndex()
                == recurringPaymentIndex;
        boolean isNameEqual = consoleCommandEditRecurringPayment
                .getName()
                .equals(name);
        boolean isIntervalEqual = consoleCommandEditRecurringPayment
                .getInterval()
                == interval;
        boolean isDescriptionEqual = consoleCommandEditRecurringPayment
                .getDescription()
                .equals(description);
        boolean isAmountEqual = consoleCommandEditRecurringPayment
                .getAmount()
                .equals(amount.stripTrailingZeros());
        boolean isCategoryEqual = consoleCommandEditRecurringPayment
                .getCategory()
                .equals(category);
        boolean isCurrencyEqual = consoleCommandEditRecurringPayment
                .getCurrency()
                .equals(currency);
        boolean isModeOfPaymentEqual = consoleCommandEditRecurringPayment
                .getModeOfPayment()
                .equals(modeOfPayment);

        assertTrue(
                isRecurringPaymentIndexEqual
                        && isNameEqual
                        && isIntervalEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual
                        && isCurrencyEqual
                        && isModeOfPaymentEqual
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erp_ccerpInvalidException() {
        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT;

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpInvalidIndex1_ccerpInvalidException() {
        String recurringPaymentIndex = "InvalidIndex";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpInvalidIndex2_ccerpInvalidException() {
        String recurringPaymentIndex = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpInvalidIndex3_ccerpInvalidException() {
        String recurringPaymentIndex = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex;

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInvalidInterval1_ccerpInvalidException() {
        String recurringPaymentIndex = "0";
        String interval = "InvalidInterval";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL
                + " \""
                + interval
                + "\"";

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInvalidInterval2_ccerpInvalidException() {
        String recurringPaymentIndex = "0";
        String interval = "-1";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL
                + " \""
                + interval
                + "\"";

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInvalidInterval3_ccerpInvalidException() {
        String recurringPaymentIndex = "0";
        String interval = "999999999999999999999999";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL
                + " \""
                + interval
                + "\"";

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInvalidIAmount1_ccerpInvalidException() {
        String recurringPaymentIndex = "0";
        String amount = "InvalidAmount";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT
                + " \""
                + amount
                + "\"";

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }

    //@@author xzynos
    @Test
    void parseCommand_erpIndexInvalidIAmount2_ccerpInvalidException() {
        String recurringPaymentIndex = "0";
        String amount = "-7.80";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX
                + " "
                + recurringPaymentIndex
                + " -"
                + ConsoleParserConfigurations.COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT
                + " \""
                + amount
                + "\"";

        assertThrows(ConsoleParserCommandEditRecurringPaymentInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }
    //endregion

    //region Defines JUnit test cases for command Merge-File
    //@@author LokQiJun
    @Test
    void parseCommand_mfPath_ccmefPath() throws
            MoneyGoWhereException {
        String filePath = "C:/ValidPath/MoneyGoWhereData.xml";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_MERGE_FILE
                + " -"
                + ConsoleParserConfigurations.COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH
                + " "
                + filePath;
        System.out.print(input);
        ConsoleCommandMergeExternalFile consoleCommandMergeExternalFile =
                (ConsoleCommandMergeExternalFile) ConsoleParser.parse(input);

        boolean isFilePathEqual = consoleCommandMergeExternalFile.getFilePath().equals(filePath);

        assertTrue(isFilePathEqual);
    }

    //@@author LokQiJun
    @Test
    void parseCommand_mfInvalidPath_ccmfInvalidException() {
        String filePath = "This is an invalid path";

        String input = ""
                + ConsoleParserConfigurations.COMMAND_MERGE_FILE
                + " -"
                + ConsoleParserConfigurations.COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH
                + " "
                + filePath;

        assertThrows(ConsoleParserCommandMergeExternalFileInvalidException.class, () ->
                ConsoleParser.parse(input)
        );
    }
    //endregion
}
