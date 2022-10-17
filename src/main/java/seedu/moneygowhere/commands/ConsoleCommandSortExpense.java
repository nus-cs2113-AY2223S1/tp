package seedu.moneygowhere.commands;

import seedu.moneygowhere.parser.ConsoleParserConfigurations;

@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandSortExpense extends ConsoleCommand {
    private String type;
    private boolean order;

    /**
     * Reads in type & order of sorting to run sorting command later.
     *
     * @param type defines the type of sorting to be done later, be it by date, amount or alphabetical
     * @param order if true, sort by ascending. If false, sort by descending
     */
    public ConsoleCommandSortExpense(String type, String order) {
        this.type = type;
        if (order.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING)) {
            this.order = true;
        } else if (order.equalsIgnoreCase(ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING)) {
            this.order = false;
        }
    }

    public String getType() {
        return type;
    }

    public boolean getOrderValue() {
        return order;
    }

    public String getOrder() {
        if (order) {
            return ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING;
        }
        return ConsoleParserConfigurations.COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING;
    }
}
