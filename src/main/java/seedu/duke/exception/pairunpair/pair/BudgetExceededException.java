package seedu.duke.exception.pairunpair.pair;
//@@author ngdeqi

import seedu.duke.Client;
import seedu.duke.Property;

import static seedu.duke.Messages.MESSAGE_BUDGET_CLIENT;
import static seedu.duke.Messages.MESSAGE_BUDGET_EXCEEDED;
import static seedu.duke.Messages.MESSAGE_BUDGET_PROPERTY;

/**
 * Represents exception for when the user tries to pair a client with a property whose renting price is greater than
 * the client's budget.
 */
public class BudgetExceededException extends CommandPairException {

    private static final String WHITE_SPACE = " | ";
    private Client client;
    private Property property;

    public BudgetExceededException(Client client, Property property) {
        this.client = client;
        this.property = property;
    }

    @Override
    public String toString() {
        return MESSAGE_BUDGET_EXCEEDED
                + MESSAGE_BUDGET_CLIENT
                + client.getClientName() + WHITE_SPACE + client.getClientBudgetPerMonth() + System.lineSeparator()
                + MESSAGE_BUDGET_PROPERTY
                + property.getPropertyAddress() + WHITE_SPACE + property.getRentingPrice() + System.lineSeparator();
    }
}
