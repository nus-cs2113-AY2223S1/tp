package seedu.moneygowhere.storage;

import org.junit.jupiter.api.Test;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Configurations;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.income.Income;
import seedu.moneygowhere.data.recurringpayments.RecurringPayment;
import seedu.moneygowhere.data.target.Target;
import seedu.moneygowhere.parser.ConsoleParserConfigurations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.moneygowhere.storage.LocalStorage.loadFromFile;
import static seedu.moneygowhere.storage.LocalStorage.saveToFile;

class LocalStorageTest {
    @Test
    void save_Load_File() {
        String name = "Test";
        BigDecimal amount = new BigDecimal("7.80");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                Configurations.CONSOLE_INTERFACE_DATE_TIME_INPUT_FORMAT
        );
        LocalDateTime dateTime = LocalDateTime.parse("01/01/2022 2359", dateTimeFormatter);
        String currency = "SGD";
        Integer interval = 10;

        Expense testExpense = new Expense(name, dateTime, null, amount,
                null, null, currency, null);
        RecurringPayment testRecurringPayment = new RecurringPayment(name, interval, null, amount,
                null, currency, null);
        Target testTarget = new Target(name, dateTime, null, amount, amount);
        Income testIncome = new Income(name, dateTime, null, amount);


        ArrayList<Expense> testExpenses = new ArrayList<>();
        ArrayList<RecurringPayment> testRecurringPayments = new ArrayList<>();
        ArrayList<Target> testTargets = new ArrayList<>();
        ArrayList<Income> testIncomes = new ArrayList<>();

        final int numExpense = 10;
        final int numRecurringPayment = 3;
        final int numTarget = 6;
        final int numIncome = 3;

        for (int i = 0; i < numExpense; ++i) {
            testExpenses.add(testExpense);
        }
        for (int i = 0; i < numRecurringPayment; ++i) {
            testRecurringPayments.add(testRecurringPayment);
        }
        for (int i = 0; i < numTarget; ++i) {
            testTargets.add(testTarget);
        }
        for (int i = 0; i < numIncome; ++i) {
            testIncomes.add(testIncome);
        }

        ConsoleCommandSortExpense sortCommandSetting = new ConsoleCommandSortExpense(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL,
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING
        );

        saveToFile(testExpenses, sortCommandSetting, testRecurringPayments, testTargets, testIncomes);


        ArrayList<Expense> loadExpenses = new ArrayList<>();
        ConsoleCommandSortExpense loadSortCommandSetting = new ConsoleCommandSortExpense(
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL,
                ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING
        );
        ArrayList<RecurringPayment> loadRecurringPayments = new ArrayList<>();
        ArrayList<Target> loadTargets = new ArrayList<>();
        ArrayList<Income> loadIncomes = new ArrayList<>();
        loadFromFile(loadExpenses, loadSortCommandSetting, loadRecurringPayments, loadTargets, loadIncomes);

        boolean isSameLength = testExpenses.size() == loadExpenses.size()
                && testRecurringPayments.size() == loadRecurringPayments.size()
                && testTargets.size() == loadTargets.size()
                && testIncomes.size() == loadIncomes.size();

        boolean isCorrectLength = loadExpenses.size() == numExpense
                && loadRecurringPayments.size() == numRecurringPayment
                && loadTargets.size() == numTarget
                && loadIncomes.size() == numIncome;

        boolean hasSameSortConfig = loadSortCommandSetting.getOrder().equals(sortCommandSetting.getOrder())
                && loadSortCommandSetting.getType().equals(sortCommandSetting.getType());

        assertTrue(
                isSameLength
                        && hasSameSortConfig);
    }
}