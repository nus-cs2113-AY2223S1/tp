package seedu.moneygowhere.parser;

import org.junit.jupiter.api.Test;
import seedu.moneygowhere.commands.ConsoleCommandAddExpense;
import seedu.moneygowhere.commands.ConsoleCommandViewExpense;
import seedu.moneygowhere.exceptions.ConsoleParserCommandAddExpenseInvalidException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandNotFoundException;
import seedu.moneygowhere.exceptions.ConsoleParserCommandSortExpenseInvalidTypeException;
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
    void parseCommand_aeNameAmount_ccaeNameAmountDate() throws
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
    void parseCommand_aeNameAmountDate_ccaeNameAmountDate() throws
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
    void parseCommand_aeNameAmountDateDescription_ccaeNameAmountDateDescription() throws
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
    void parseCommand_aeNameAmountDateDescriptionCategory_ccaeNameAmountDateDescriptionCategory() throws
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
    void parseCommand_aeNameAmountDateDescriptionCategory_ccaeInvalidException() {
        String input = "Add-Expense -n Exp";

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
}