package seedu.duke.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.common.InfoMessages.INFO_EXCEEDING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_REMAINING_BUDGET;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_EXCEEDED_TIPS;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_EXCEEDED_TIPS;
import static seedu.duke.common.InfoMessages.INFO_SAVING_TIPS_AND_BUDGET_ADVICE_SEPARATOR;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_HIGH;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_LOW;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_HIGH;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_LOW;
import static seedu.duke.common.InfoMessages.INFO_BUDGET_NOT_SPENT_ADVICE;


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


    String messageHeader = INFO_SAVING_TIPS_AND_BUDGET_ADVICE_SEPARATOR.toString();

    @Test
    public void generateBudgetAdvice_budgetExceededNotMoreThanTwice_expectLowExceededAdvice() {
        // Ensure that the budget is not updated by other test cases
        Budget.setBudget(1000);
        assertEquals(Budget.generateBudgetAdvice(1001, true),
            messageHeader + INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_LOW);
    }

    @Test
    public void generateBudgetAdvice_budgetExceededMoreThanTwice_expectHighExceededAdvice() {
        // Ensure that the budget is not updated by other test cases
        Budget.setBudget(1000);
        assertEquals(Budget.generateBudgetAdvice(2001, true),
            messageHeader + INFO_BUDGET_EXCEEDED_ADVICE_SPENDING_HIGH);
    }

    @Test
    public void generateBudgetAdvice_budgetRemainedLessThanHalf_expectRemainedButHighSpendingAdvice() {
        // Ensure that the budget is not updated by other test cases
        Budget.setBudget(1000);
        assertEquals(Budget.generateBudgetAdvice(499, false),
                messageHeader + INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_HIGH);
    }

    @Test
    public void generateBudgetAdvice_budgetRemainedNotLessThanHalf_expectRemainedButLowSpendingAdvice() {
        // Ensure that the budget is not updated by other test cases
        Budget.setBudget(1000);
        // 500 is exactly half of 1000 but not less than half
        assertEquals(Budget.generateBudgetAdvice(500, false),
                messageHeader + INFO_BUDGET_NOT_EXCEEDED_ADVICE_SPENDING_LOW);
    }

    @Test
    public void generateBudgetAdvice_budgetNotSpent_expectNoSpendingAdvice() {
        // Ensure that the budget is not updated by other test cases
        Budget.setBudget(1000);
        // 500 is exactly half of 1000 but not less than half
        assertEquals(Budget.generateBudgetAdvice(1000, false),
                messageHeader + INFO_BUDGET_NOT_SPENT_ADVICE);
    }
}
