package seedu.duke.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.common.InfoMessages.INFO_EXCEEDING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_REMAINING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_EXCEEDED_TIPS;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_EXCEEDED_TIPS;

public class BudgetTest {
    @Test
    public void generateBudgetRemainingMessage_budgetExceeded_expectExceededStringInMessage() {
        assertTrue(Budget.generateBudgetRemainingMessage(10000, false, "")
                .startsWith(INFO_EXCEEDING_BUDGET.toString()));
        assertTrue(Budget.generateBudgetRemainingMessage(69696969, true, "")
                .startsWith(INFO_EXCEEDING_BUDGET.toString()));
    }

    @Test
    public void generateBudgetRemainingMessage_budgetNotExceeded_expectRemainedStringInMessage() {
        assertTrue(Budget.generateBudgetRemainingMessage(0, false, "")
                .startsWith(INFO_REMAINING_BUDGET.toString()));
        assertTrue(Budget.generateBudgetRemainingMessage(69, true, "")
                .startsWith(INFO_REMAINING_BUDGET.toString()));
    }

    @Test
    public void generateBudgetRemainingMessage_withTips_expectTipsInMessage() {
        assertTrue(Budget.generateBudgetRemainingMessage(69, true, "")
                .contains(INFO_BUDGET_NOT_EXCEEDED_TIPS.toString()));
        assertTrue(Budget.generateBudgetRemainingMessage(69696969, true, "")
                .contains(INFO_BUDGET_EXCEEDED_TIPS.toString()));
    }

    @Test
    public void generateBudgetRemainingMessage_withoutTips_expectNoTipInMessage() {
        assertFalse(Budget.generateBudgetRemainingMessage(69, false, "")
                .contains(INFO_BUDGET_EXCEEDED_TIPS.toString()));
        assertFalse(Budget.generateBudgetRemainingMessage(69696969, false, "")
                .contains(INFO_BUDGET_NOT_EXCEEDED_TIPS.toString()));
    }

    @Test
    public void generateCurrentMonthBudgetRemainingMessage_usualRun_expectCurrentMonthBudget() {
        TransactionList transactions = new TransactionList();
        long currentMonthTotalExpense = TransactionList.calculateMonthlyTotalExpense(LocalDate.now());
        assertTrue(Budget.generateCurrentMonthBudgetRemainingMessage()
                .contains(Long.toString(currentMonthTotalExpense)));
    }
}
