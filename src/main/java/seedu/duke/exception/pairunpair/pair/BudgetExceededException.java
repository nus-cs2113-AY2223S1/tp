package seedu.duke.exception.pairunpair.pair;
//@@author ngdeqi

import static seedu.duke.Messages.MESSAGE_BUDGET_EXCEEDED;

/**
 * Represents exception for when the user tries to pair a client with a property whose renting price is greater than
 * the client's budget.
 */
public class BudgetExceededException extends CommandPairException {

    @Override
    public String toString() {
        return MESSAGE_BUDGET_EXCEEDED;
    }
}
