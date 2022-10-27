//@@author OVReader

package seedu.duke.exception;

import static seedu.duke.Messages.MESSAGE_INVALID_BUDGET_FORMAT;

/**
 * Represents exception when incorrect format for budget is given when adding client.
 */
public class InvalidBudgetFormatException extends ParseAddClientException {
    @Override
    public String toString() {
        return MESSAGE_INVALID_BUDGET_FORMAT;
    }
}
