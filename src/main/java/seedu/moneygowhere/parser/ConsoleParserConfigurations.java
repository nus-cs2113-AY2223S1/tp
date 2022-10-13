package seedu.moneygowhere.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Defines configuration parameters used by {@link ConsoleParser}.
 */
public class ConsoleParserConfigurations {
    /**
     * Defines parameters for console command Bye.
     */
    public static final String COMMAND_BYE = "bye";

    /**
     * Defines parameters for console command Add-Expense.
     */
    public static final String COMMAND_ADD_EXPENSE = "add-expense";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME = "n";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_NAME_HAS_VAL = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_AMOUNT_HAS_VAL = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DATE_TIME_HAS_VAL = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_HAS_VAL = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY = "c";
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_CATEGORY_HAS_VAL = true;
    public static final String COMMAND_ADD_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Add-Expense does not have all of the required options.";

    /**
     * Defines parameters for console command View-Expense.
     */
    public static final String COMMAND_VIEW_EXPENSE = "view-expense";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY = "c";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG = "expense-category";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_HAS_VAL = true;
    public static final String COMMAND_VIEW_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command View-Expense does not have all of the required options.";

    /**
     * Defines parameters for console command Delete-Expense.
     */
    public static final String COMMAND_DELETE_EXPENSE = "delete-expense";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final boolean COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final String COMMAND_DELETE_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Delete-Expense does not have all of the required options.";

    /**
     * Defines parameters for console command Edit-Expense.
     */
    public static final String COMMAND_EDIT_EXPENSE = "edit-expense";
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME = "n";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME_LONG = "name";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_NAME_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT = "a";
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_AMOUNT_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME = "d";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION = "t";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY = "c";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_CATEGORY_HAS_VAL = true;
    public static final String COMMAND_EDIT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Edit-Expense does not have all of the required options.";

    /**
     * Defines parameters for console command Sort-Expense.
     */
    public static final String COMMAND_SORT_EXPENSE = "sort-expense";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE = "t";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_LONG = "type";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_DESC = ""
            + "Alphabetical/Amount/Date";
    public static final boolean COMMAND_SORT_EXPENSE_ARG_TYPE_HAS_VAL = true;
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL = "alphabetical";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT = "amount";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE = "date";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER = "o";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_LONG = "order";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_DESC = ""
            + "Ascending/Descending";
    public static final boolean COMMAND_SORT_EXPENSE_ARG_ORDER_HAS_VAL = true;
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING = "ascending";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING = "descending";

    /**
     * Defines parameters for console command Add-Target.
     */
    public static final String COMMAND_ADD_TARGET = "add-target";
    public static final String COMMAND_ADD_TARGET_ARG_NAME = "n";
    public static final String COMMAND_ADD_TARGET_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_TARGET_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_TARGET_ARG_NAME_HAS_VAL = true;
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_TARGET_ARG_AMOUNT_HAS_VAL = true;
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT = "c";
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG = "current-amount";
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_DESC = "Current Amount";
    public static final boolean COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL = true;
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_TARGET_ARG_DATE_TIME_HAS_VAL = true;
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_TARGET_ARG_DESCRIPTION_HAS_VAL = true;

    /**
     * Defines parameters for console command Add-Income.
     */
    public static final String COMMAND_ADD_INCOME = "add-income";
    public static final String COMMAND_ADD_INCOME_ARG_NAME = "n";
    public static final String COMMAND_ADD_INCOME_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_INCOME_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_INCOME_ARG_NAME_HAS_VAL = true;
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_INCOME_ARG_AMOUNT_HAS_VAL = true;
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_INCOME_ARG_DATE_TIME_HAS_VAL = true;
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_INCOME_ARG_DESCRIPTION_HAS_VAL = true;

    /**
     * Generates an {@link Options} object with required arguments for command Add-Expense.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandAddExpenseOptions() {
        Option optionName = new Option(
                COMMAND_ADD_EXPENSE_ARG_NAME,
                COMMAND_ADD_EXPENSE_ARG_NAME_LONG,
                COMMAND_ADD_EXPENSE_ARG_NAME_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_NAME_DESC
        );
        Option optionAmount = new Option(
                COMMAND_ADD_EXPENSE_ARG_AMOUNT,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_DESC
        );
        Option optionDateTime = new Option(
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_DESC
        );
        Option optionDescription = new Option(
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_DESC
        );

        Option optionCategory = new Option(
                COMMAND_ADD_EXPENSE_ARG_CATEGORY,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_DESC
        );

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionCategory);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command View-Expense.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandViewExpenseOptions() {
        Option optionExpenseIndex = new Option(
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_DESC
        );
        Option optionExpenseCategory = new Option(
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_HAS_VAL,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_DESC
        );

        Options options = new Options();
        options.addOption(optionExpenseIndex);
        options.addOption(optionExpenseCategory);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command Delete-Expense.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandDeleteExpenseOptions() {
        Option optionExpenseIndex = new Option(
                COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX,
                COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG,
                COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL,
                COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_DESC
        );

        Options options = new Options();
        options.addOption(optionExpenseIndex);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command Edit-Expense.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandEditExpenseOptions() {
        Option optionExpenseIndex = new Option(
                COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX,
                COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG,
                COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_DESC
        );
        Option optionName = new Option(
                COMMAND_EDIT_EXPENSE_ARG_NAME,
                COMMAND_EDIT_EXPENSE_ARG_NAME_LONG,
                COMMAND_EDIT_EXPENSE_ARG_NAME_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_NAME_DESC
        );
        Option optionDateTime = new Option(
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_DESC
        );
        Option optionDescription = new Option(
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_DESC
        );
        Option optionAmount = new Option(
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_DESC
        );
        Option optionCategory = new Option(
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_DESC
        );

        Options options = new Options();
        options.addOption(optionExpenseIndex);
        options.addOption(optionName);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionAmount);
        options.addOption(optionCategory);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command Sort-Expense.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandSortExpenseOptions() {
        Option optionType = new Option(
                COMMAND_SORT_EXPENSE_ARG_TYPE,
                COMMAND_SORT_EXPENSE_ARG_TYPE_LONG,
                COMMAND_SORT_EXPENSE_ARG_TYPE_HAS_VAL,
                COMMAND_SORT_EXPENSE_ARG_TYPE_DESC
        );
        Option optionOrder = new Option(
                COMMAND_SORT_EXPENSE_ARG_ORDER,
                COMMAND_SORT_EXPENSE_ARG_ORDER_LONG,
                COMMAND_SORT_EXPENSE_ARG_ORDER_HAS_VAL,
                COMMAND_SORT_EXPENSE_ARG_ORDER_DESC
        );

        Options options = new Options();
        options.addOption(optionType);
        options.addOption(optionOrder);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command Add-Target.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandAddTargetOptions() {
        Option optionName = new Option(
                COMMAND_ADD_TARGET_ARG_NAME,
                COMMAND_ADD_TARGET_ARG_NAME_LONG,
                COMMAND_ADD_TARGET_ARG_NAME_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_NAME_DESC
        );
        Option optionAmount = new Option(
                COMMAND_ADD_TARGET_ARG_AMOUNT,
                COMMAND_ADD_TARGET_ARG_AMOUNT_LONG,
                COMMAND_ADD_TARGET_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_AMOUNT_DESC
        );
        Option optionCurrentAmount = new Option(
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_DESC
        );
        Option optionDateTime = new Option(
                COMMAND_ADD_TARGET_ARG_DATE_TIME,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_DESC
        );
        Option optionDescription = new Option(
                COMMAND_ADD_TARGET_ARG_DESCRIPTION,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_DESC
        );

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionCurrentAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);

        return options;
    }

    /**
     * Generates an {@link Options} object with required arguments for command Add-Income.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandAddIncomeOptions() {
        Option optionName = new Option(
                COMMAND_ADD_INCOME_ARG_NAME,
                COMMAND_ADD_INCOME_ARG_NAME_LONG,
                COMMAND_ADD_INCOME_ARG_NAME_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_NAME_DESC
        );
        Option optionAmount = new Option(
                COMMAND_ADD_INCOME_ARG_AMOUNT,
                COMMAND_ADD_INCOME_ARG_AMOUNT_LONG,
                COMMAND_ADD_INCOME_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_AMOUNT_DESC
        );

        Option optionDateTime = new Option(
                COMMAND_ADD_INCOME_ARG_DATE_TIME,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_DESC
        );
        Option optionDescription = new Option(
                COMMAND_ADD_INCOME_ARG_DESCRIPTION,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_DESC
        );

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);

        return options;
    }
}
