package seedu.moneygowhere.parser;

import org.junit.jupiter.api.Test;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandDeleteExpense;
import seedu.moneygowhere.commands.ConsoleCommandEditExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandDeleteExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandEditExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandViewExpenseInvalidException;
import seedu.moneygowhere.exceptions.MoneyGoWhereException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleParserTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void parseCommand_aeNameAmount_ccaeNameAmountDateTime() throws
            MoneyGoWhereException {
        String input = "Add-Expense -n Exp -a 7.80";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = true;
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_aeNameAmountDateTime_ccaeNameAmountDateTime() throws
            MoneyGoWhereException {
        String input = "Add-Expense -n Exp -a 7.80 -d \"01/01/2022 2359\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandAddExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_aeNameAmountDateTimeDescription_ccaeNameAmountDateTimeDescription() throws
            MoneyGoWhereException {
        String input = "Add-Expense -n Exp -a 7.80 -d \"01/01/2022 2359\" -t \"Test Desc\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandAddExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                .equals("Test Desc");
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                == null;

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_aeNameAmountDateTimeDescriptionCategory_ccaeNameAmountDateTimeDescriptionCategory() throws
            MoneyGoWhereException {
        String input = "Add-Expense -n Exp -a 7.80 -d \"01/01/2022 2359\" -t \"Test Desc\" -c \"Test Cat\"";

        ConsoleCommandAddExpense consoleCommandAddExpense = (ConsoleCommandAddExpense) ConsoleParser.parse(input);

        boolean isNameEqual = consoleCommandAddExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandAddExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandAddExpense
                .getDescription()
                .equals("Test Desc");
        boolean isAmountEqual = consoleCommandAddExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandAddExpense
                .getCategory()
                .equals("Test Cat");

        assertTrue(
                isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_aeName_ccaeInvalidException() {
        String input = "Add-Expense -n Exp";

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_aeNameAmount_ccaeInvalidException() {
        String input = "Add-Expense -n Exp -a \"ThisIsAnInvalidAmount\"";

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_aeNameAmountDateTime_ccaeInvalidException() {
        String input = "Add-Expense -n Exp -a 7.80 -d \"ThisIsAnInvalidDateTime\"";

        assertThrows(ConsoleParserCommandAddExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_ve_ccve() throws
            MoneyGoWhereException {
        String input = "View-Expense";

        ConsoleCommandViewExpense consoleCommandViewExpense = (ConsoleCommandViewExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandViewExpense
                .getExpenseIndex()
                == -1;

        assertTrue(isExpenseIndexEqual);
    }

    @Test
    void parseCommand_veIndex_ccveIndex() throws
            MoneyGoWhereException {
        String input = "View-Expense -e 0";

        ConsoleCommandViewExpense consoleCommandViewExpense = (ConsoleCommandViewExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandViewExpense
                .getExpenseIndex()
                == 0;

        assertTrue(isExpenseIndexEqual);
    }

    @Test
    void parseCommand_veIndex_ccveInvalidException() {
        String input = "View-Expense -e \"ThisIsAnInvalidExpenseIndex\"";

        assertThrows(ConsoleParserCommandViewExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_deIndex_ccdeIndex() throws
            MoneyGoWhereException {
        String input = "Delete-Expense -e 0";

        ConsoleCommandDeleteExpense consoleCommandDeleteExpense =
                (ConsoleCommandDeleteExpense) ConsoleParser.parse(input);

        boolean isExpenseIndexEqual = consoleCommandDeleteExpense
                .getExpenseIndex()
                == 0;

        assertTrue(isExpenseIndexEqual);
    }

    @Test
    void parseCommand_de_ccdeInvalidException() {
        String input = "Delete-Expense";

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_deIndex_ccdeInvalidException() {
        String input = "Delete-Expense -e \"ThisIsAnInvalidExpenseIndex\"";

        assertThrows(ConsoleParserCommandDeleteExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_eeIndexName_cceeIndexName() throws
            MoneyGoWhereException {
        String input = "Edit-Expense -e 0 -n Exp";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals("Exp");
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

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_eeIndexNameAmount_cceeIndexNameAmount() throws
            MoneyGoWhereException {
        String input = "Edit-Expense -e 0 -n Exp -a 7.80";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                == null;
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_eeIndexNameAmountDateTime_cceeIndexNameAmountDateTime() throws
            MoneyGoWhereException {
        String input = "Edit-Expense -e 0 -n Exp -a 7.80 -d \"01/01/2022 2359\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                == null;
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_eeIndexNameAmountDateTimeDescription_cceeIndexNameAmountDateTimeDescription() throws
            MoneyGoWhereException {
        String input = "Edit-Expense -e 0 -n Exp -a 7.80 -d \"01/01/2022 2359\" -t \"Test Desc\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                .equals("Test Desc");
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                == null;

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_eeIndexNameAmountDateTimeDescriptionCategory_cceeIndexNameAmountDateTimeDescriptionCategory()
            throws
            MoneyGoWhereException {
        String input = "Edit-Expense -e 0 -n Exp -a 7.80 -d \"01/01/2022 2359\" -t \"Test Desc\" -c \"Test Cat\"";

        ConsoleCommandEditExpense consoleCommandEditExpense = (ConsoleCommandEditExpense) ConsoleParser.parse(input);

        boolean isIndexEqual = consoleCommandEditExpense
                .getExpenseIndex()
                == 0;
        boolean isNameEqual = consoleCommandEditExpense
                .getName()
                .equals("Exp");
        boolean isDateTimeEqual = consoleCommandEditExpense
                .getDateTime()
                .equals(LocalDateTime.parse(
                        "01/01/2022 2359",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
                ));
        boolean isDescriptionEqual = consoleCommandEditExpense
                .getDescription()
                .equals("Test Desc");
        boolean isAmountEqual = consoleCommandEditExpense
                .getAmount()
                .equals(new BigDecimal("7.80"));
        boolean isCategoryEqual = consoleCommandEditExpense
                .getCategory()
                .equals("Test Cat");

        assertTrue(
                isIndexEqual
                        && isNameEqual
                        && isDateTimeEqual
                        && isDescriptionEqual
                        && isAmountEqual
                        && isCategoryEqual);
    }

    @Test
    void parseCommand_ee_cceeInvalidException() {
        String input = "Edit-Expense";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_eeIndex_cceeInvalidException() {
        String input = "Edit-Expense -e \"ThisIsAnInvalidExpenseIndex\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_eeIndexAmount_cceeInvalidException() {
        String input = "Edit-Expense -e 0 -a \"ThisIsAnInvalidAmount\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }

    @Test
    void parseCommand_eeIndexAmountDateTime_cceeInvalidException() {
        String input = "Edit-Expense -e 0 -a 7.80 -d \"ThisIsAnInvalidDateTime\"";

        assertThrows(ConsoleParserCommandEditExpenseInvalidException.class, () ->
                ConsoleParser.parse(input));
    }
}