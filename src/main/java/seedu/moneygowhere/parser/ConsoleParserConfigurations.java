package seedu.moneygowhere.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

//@@author xzynos

/**
 * Defines configuration parameters used by {@link ConsoleParser}.
 */
public class ConsoleParserConfigurations {
    //@@author xzynos
    //region Defines parameters for console command Bye
    public static final String COMMAND_BYE = "bye";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Add-Expense
    public static final String COMMAND_ADD_EXPENSE = "Add-Expense";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME = "n";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_EXPENSE_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_NAME_IS_MAND = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_EXPENSE_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_AMOUNT_IS_MAND = true;
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_EXPENSE_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY = "c";
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_ADD_EXPENSE_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_CATEGORY_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_CATEGORY_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_REMARKS = "r";
    public static final String COMMAND_ADD_EXPENSE_ARG_REMARKS_LONG = "remarks";
    public static final String COMMAND_ADD_EXPENSE_ARG_REMARKS_DESC = "remarks";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_REMARKS_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_REMARKS_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_CURRENCY = "x";
    public static final String COMMAND_ADD_EXPENSE_ARG_CURRENCY_LONG = "currency";
    public static final String COMMAND_ADD_EXPENSE_ARG_CURRENCY_DESC = "currency";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_CURRENCY_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_CURRENCY_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT = "p";
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_LONG = "mode of payment";
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_DESC = ""
            + "PayLah/PayNow/Cash/Card";
    public static final boolean COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_IS_MAND = false;
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH = "PayLah";
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW = "PayNow";
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH = "Cash";
    public static final String COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD = "Card";
    public static final String COMMAND_ADD_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Add-Expense does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command View-Expense
    public static final String COMMAND_VIEW_EXPENSE = "View-Expense";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND = false;
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY = "c";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG = "expense-category";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_HAS_VAL = true;
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_IS_MAND = false;
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME = "n";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_LONG = "expense-name";
    public static final String COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_DESC = "Name";
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_HAS_VAL = true;
    public static final boolean COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_IS_MAND = false;
    public static final String COMMAND_VIEW_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command View-Expense does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Delete-Expense
    public static final String COMMAND_DELETE_EXPENSE = "Delete-Expense";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final String COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final boolean COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND = true;
    public static final String COMMAND_DELETE_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Delete-Expense does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Edit-Expense
    public static final String COMMAND_EDIT_EXPENSE = "Edit-Expense";
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND = true;
    public static final String COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME = "n";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME_LONG = "name";
    public static final String COMMAND_EDIT_EXPENSE_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_NAME_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT = "a";
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_EDIT_EXPENSE_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_AMOUNT_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME = "d";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION = "t";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY = "c";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_CATEGORY_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_CATEGORY_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_REMARKS = "r";
    public static final String COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG = "remarks";
    public static final String COMMAND_EDIT_EXPENSE_ARG_REMARKS_DESC = "Remarks";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_REMARKS_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_REMARKS_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_CURRENCY = "x";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG = "currency";
    public static final String COMMAND_EDIT_EXPENSE_ARG_CURRENCY_DESC = "currency";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_CURRENCY_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_CURRENCY_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT = "p";
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG = "mode of payment";
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_DESC = ""
            + "PayLah/PayNow/Cash/Card";
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_IS_MAND = false;
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYLAH = "PayLah";
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_PAYNOW = "PayNow";
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CASH = "Cash";
    public static final String COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_VAL_CARD = "Card";
    public static final String COMMAND_EDIT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Edit-Expense does not have all of the required options.";
    //endregion

    //@@author jeyvia
    //region Defines parameters for console command Sort-Expense
    public static final String COMMAND_SORT_EXPENSE = "Sort-Expense";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE = "t";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_LONG = "type";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_DESC = ""
            + "Alphabetical/Amount/Date";
    public static final boolean COMMAND_SORT_EXPENSE_ARG_TYPE_HAS_VAL = true;
    public static final boolean COMMAND_SORT_EXPENSE_ARG_TYPE_IS_MAND = true;
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_ALPHABETICAL = "Alphabetical";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_AMOUNT = "Amount";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_DATE = "Date";
    public static final String COMMAND_SORT_EXPENSE_ARG_TYPE_VAL_CURRENCY = "Currency";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER = "o";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_LONG = "order";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_DESC = ""
            + "Ascending/Descending";
    public static final boolean COMMAND_SORT_EXPENSE_ARG_ORDER_HAS_VAL = true;
    public static final boolean COMMAND_SORT_EXPENSE_ARG_ORDER_IS_MAND = true;
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_ASCENDING = "Ascending";
    public static final String COMMAND_SORT_EXPENSE_ARG_ORDER_VAL_DESCENDING = "Descending";
    public static final String COMMAND_SORT_EXPENSE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Sort-Expense does not have all of the required options.";
    //endregion

    //@@author jeyvia
    //region Defines parameters for console command Convert-Currency
    public static final String COMMAND_CONVERT_CURRENCY = "Convert-Currency";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX = "e";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG = "expense-index";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_DESC = "Index";
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_IS_MAND = true;
    public static final String COMMAND_CONVERT_CURRENCY_ARG_CURRENCY = "x";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG = "currency";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_DESC = "currency";
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_HAS_VAL = true;
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_IS_MAND = true;
    public static final String COMMAND_CONVERT_CURRENCY_ARG_RATE = "r";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_RATE_LONG = "rate";
    public static final String COMMAND_CONVERT_CURRENCY_ARG_RATE_DESC = "rate";
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_RATE_HAS_VAL = true;
    public static final boolean COMMAND_CONVERT_CURRENCY_ARG_RATE_IS_MAND = false;
    public static final String COMMAND_CONVERT_CURRENCY_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Convert-Currency does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Add-Target
    public static final String COMMAND_ADD_TARGET = "Add-Target";
    public static final String COMMAND_ADD_TARGET_ARG_NAME = "n";
    public static final String COMMAND_ADD_TARGET_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_TARGET_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_TARGET_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_TARGET_ARG_NAME_IS_MAND = true;
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_TARGET_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_TARGET_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_TARGET_ARG_AMOUNT_IS_MAND = true;
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT = "c";
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG = "current-amount";
    public static final String COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_DESC = "Current Amount";
    public static final boolean COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_IS_MAND = true;
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_TARGET_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_TARGET_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_TARGET_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_TARGET_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_TARGET_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_ADD_TARGET_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_ADD_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Add-Target does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command View-Target
    public static final String COMMAND_VIEW_TARGET = "View-Target";
    public static final String COMMAND_VIEW_TARGET_ARG_TARGET_INDEX = "e";
    public static final String COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG = "target-index";
    public static final String COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_DESC = "Index";
    public static final boolean COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_IS_MAND = false;
    public static final String COMMAND_VIEW_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command View-Target does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Delete-Target
    public static final String COMMAND_DELETE_TARGET = "Delete-Target";
    public static final String COMMAND_DELETE_TARGET_ARG_TARGET_INDEX = "e";
    public static final String COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_LONG = "target-index";
    public static final String COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_DESC = "Index";
    public static final boolean COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_IS_MAND = true;
    public static final String COMMAND_DELETE_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Delete-Expense does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Edit-Target
    public static final String COMMAND_EDIT_TARGET = "Edit-Target";
    public static final String COMMAND_EDIT_TARGET_ARG_TARGET_INDEX = "e";
    public static final String COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_LONG = "target-index";
    public static final boolean COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_IS_MAND = true;
    public static final String COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_DESC = "Index";
    public static final String COMMAND_EDIT_TARGET_ARG_NAME = "n";
    public static final String COMMAND_EDIT_TARGET_ARG_NAME_LONG = "name";
    public static final String COMMAND_EDIT_TARGET_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_EDIT_TARGET_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_NAME_IS_MAND = false;
    public static final String COMMAND_EDIT_TARGET_ARG_AMOUNT = "a";
    public static final String COMMAND_EDIT_TARGET_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_EDIT_TARGET_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_EDIT_TARGET_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_AMOUNT_IS_MAND = false;
    public static final String COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT = "c";
    public static final String COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_LONG = "current-amount";
    public static final String COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_DESC = "Current Amount";
    public static final boolean COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_IS_MAND = true;
    public static final String COMMAND_EDIT_TARGET_ARG_DATE_TIME = "d";
    public static final String COMMAND_EDIT_TARGET_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_EDIT_TARGET_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_EDIT_TARGET_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_EDIT_TARGET_ARG_DESCRIPTION = "t";
    public static final String COMMAND_EDIT_TARGET_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_EDIT_TARGET_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_EDIT_TARGET_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_TARGET_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_EDIT_TARGET_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Edit-Target does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Add-Income
    public static final String COMMAND_ADD_INCOME = "Add-Income";
    public static final String COMMAND_ADD_INCOME_ARG_NAME = "n";
    public static final String COMMAND_ADD_INCOME_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_INCOME_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_INCOME_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_INCOME_ARG_NAME_IS_MAND = true;
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_INCOME_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_INCOME_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_INCOME_ARG_AMOUNT_IS_MAND = true;
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME = "d";
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_ADD_INCOME_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_ADD_INCOME_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_INCOME_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_INCOME_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_INCOME_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_ADD_INCOME_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_ADD_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Add-Income does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command View-Income
    public static final String COMMAND_VIEW_INCOME = "View-Income";
    public static final String COMMAND_VIEW_INCOME_ARG_INCOME_INDEX = "e";
    public static final String COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_LONG = "income-index";
    public static final String COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_DESC = "Index";
    public static final boolean COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_IS_MAND = false;
    public static final String COMMAND_VIEW_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command View-Income does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Delete-Income
    public static final String COMMAND_DELETE_INCOME = "Delete-Income";
    public static final String COMMAND_DELETE_INCOME_ARG_INCOME_INDEX = "e";
    public static final String COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_LONG = "income-index";
    public static final String COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_DESC = "Index";
    public static final boolean COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_IS_MAND = true;
    public static final String COMMAND_DELETE_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Delete-Income does not have all of the required options.";
    //endregion

    //@@author penguin-s
    //region Defines parameters for console command Edit-Income
    public static final String COMMAND_EDIT_INCOME = "Edit-Income";
    public static final String COMMAND_EDIT_INCOME_ARG_INCOME_INDEX = "e";
    public static final String COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_LONG = "income-index";
    public static final boolean COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_IS_MAND = true;
    public static final String COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_DESC = "Index";
    public static final String COMMAND_EDIT_INCOME_ARG_NAME = "n";
    public static final String COMMAND_EDIT_INCOME_ARG_NAME_LONG = "name";
    public static final String COMMAND_EDIT_INCOME_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_EDIT_INCOME_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_INCOME_ARG_NAME_IS_MAND = false;
    public static final String COMMAND_EDIT_INCOME_ARG_AMOUNT = "a";
    public static final String COMMAND_EDIT_INCOME_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_EDIT_INCOME_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_EDIT_INCOME_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_INCOME_ARG_AMOUNT_IS_MAND = false;
    public static final String COMMAND_EDIT_INCOME_ARG_DATE_TIME = "d";
    public static final String COMMAND_EDIT_INCOME_ARG_DATE_TIME_LONG = "datetime";
    public static final String COMMAND_EDIT_INCOME_ARG_DATE_TIME_DESC = "Date & time";
    public static final boolean COMMAND_EDIT_INCOME_ARG_DATE_TIME_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_INCOME_ARG_DATE_TIME_IS_MAND = false;
    public static final String COMMAND_EDIT_INCOME_ARG_DESCRIPTION = "t";
    public static final String COMMAND_EDIT_INCOME_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_EDIT_INCOME_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_EDIT_INCOME_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_INCOME_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_EDIT_INCOME_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Edit-Income does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Add-RecurringPayment
    public static final String COMMAND_ADD_RECURRING_PAYMENT = "Add-RecurringPayment";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME = "n";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG = "name";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_DESC = "Name";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_IS_MAND = true;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL = "i";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG = "interval";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_DESC = "Interval in days";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_IS_MAND = true;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT = "a";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG = "amount";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_DESC = "Amount";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_IS_MAND = true;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION = "t";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG = "description";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_DESC = "Description";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_IS_MAND = false;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY = "c";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_IS_MAND = false;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY = "x";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_LONG = "currency";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_DESC = "Currency";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_IS_MAND = false;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT = "p";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_LONG = "mode of payment";
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_DESC = ""
            + "PayLah/PayNow/Cash/Card";
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_HAS_VAL = true;
    public static final boolean COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_IS_MAND = false;
    public static final String COMMAND_ADD_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Add-RecurringPayment does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command View-RecurringPayment
    public static final String COMMAND_VIEW_RECURRING_PAYMENT = ""
            + "View-RecurringPayment";
    public static final String COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX = ""
            + "r";
    public static final String COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG = ""
            + "recurringpayment-index";
    public static final String COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC = ""
            + "Index";
    public static final boolean COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL =
            true;
    public static final boolean COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND =
            false;
    public static final String COMMAND_VIEW_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command View-RecurringPayment does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Delete-RecurringPayment
    public static final String COMMAND_DELETE_RECURRING_PAYMENT = ""
            + "Delete-RecurringPayment";
    public static final String COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX = ""
            + "r";
    public static final String COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG = ""
            + "recurringpayment-index";
    public static final String COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC = ""
            + "Index";
    public static final boolean COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL =
            true;
    public static final boolean COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND =
            true;
    public static final String COMMAND_DELETE_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Delete-RecurringPayment does not have all of the required options.";
    //endregion

    //@@author xzynos
    //region Defines parameters for console command Edit-RecurringPayment
    public static final String COMMAND_EDIT_RECURRING_PAYMENT = ""
            + "Edit-RecurringPayment";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX = ""
            + "r";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG = ""
            + "recurringpayment-index";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC = ""
            + "Index";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL =
            true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND =
            true;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME
            = "n";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG = ""
            + "name";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_DESC = ""
            + "Name";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_HAS_VAL =
            true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_IS_MAND =
            false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL = ""
            + "i";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_LONG = ""
            + "interval";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_DESC = ""
            + "Interval in days";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_HAS_VAL =
            true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_IS_MAND =
            false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT = ""
            + "a";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_LONG = ""
            + "amount";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_DESC = ""
            + "Amount";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_HAS_VAL =
            true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_IS_MAND =
            false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION = ""
            + "t";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG = ""
            + "description";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_DESC = ""
            + "Description";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_HAS_VAL =
            true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_IS_MAND =
            false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY = "c";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_LONG = "category";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_DESC = "Category";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_IS_MAND = false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY = "x";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_LONG = "currency";
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_DESC = "Currency";
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_HAS_VAL = true;
    public static final boolean COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_IS_MAND = false;
    public static final String COMMAND_EDIT_RECURRING_PAYMENT_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Edit-RecurringPayment does not have all of the required options.";
    //endregion

    //@@author LokQiJun
    //region Defines parameters for console command Merge-file
    public static final String COMMAND_MERGE_FILE = ""
            + "Merge-File";
    public static final String COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH = ""
            + "p";
    public static final String COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH_LONG = ""
            + "path-string";
    public static final String COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_DESC = ""
            + "File Path";
    public static final boolean COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_HAS_VAL =
            true;
    public static final boolean COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_IS_MAND =
            true;
    public static final String COMMAND_MERGE_EXTERNAL_FILE_ASSERT_FAILURE_MESSAGE_ALL_CLI_OPTIONS = ""
            + "Command Merge-file does not have all of the required options.";
    //endregion

    //@@author xzynos

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
        optionName.setRequired(COMMAND_ADD_EXPENSE_ARG_NAME_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_ADD_EXPENSE_ARG_AMOUNT,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_LONG,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_ADD_EXPENSE_ARG_AMOUNT_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_LONG,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_ADD_EXPENSE_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_ADD_EXPENSE_ARG_DESCRIPTION_IS_MAND);
        Option optionCategory = new Option(
                COMMAND_ADD_EXPENSE_ARG_CATEGORY,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_LONG,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_CATEGORY_DESC
        );
        optionCategory.setRequired(COMMAND_ADD_EXPENSE_ARG_CATEGORY_IS_MAND);
        Option optionRemarks = new Option(
                COMMAND_ADD_EXPENSE_ARG_REMARKS,
                COMMAND_ADD_EXPENSE_ARG_REMARKS_LONG,
                COMMAND_ADD_EXPENSE_ARG_REMARKS_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_REMARKS_DESC
        );
        optionRemarks.setRequired(COMMAND_ADD_EXPENSE_ARG_REMARKS_IS_MAND);
        Option optionCurrency = new Option(
                COMMAND_ADD_EXPENSE_ARG_CURRENCY,
                COMMAND_ADD_EXPENSE_ARG_CURRENCY_LONG,
                COMMAND_ADD_EXPENSE_ARG_CURRENCY_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_CURRENCY_DESC
        );
        optionCurrency.setRequired(COMMAND_ADD_EXPENSE_ARG_CURRENCY_IS_MAND);
        Option optionModeOfPayment = new Option(
                COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT,
                COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_LONG,
                COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_HAS_VAL,
                COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_DESC
        );
        optionModeOfPayment.setRequired(COMMAND_ADD_EXPENSE_ARG_MODE_OF_PAYMENT_IS_MAND);

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionCategory);
        options.addOption(optionRemarks);
        options.addOption(optionCurrency);
        options.addOption(optionModeOfPayment);

        return options;
    }

    //@@author xzynos

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
        optionExpenseIndex.setRequired(COMMAND_VIEW_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND);
        Option optionExpenseCategory = new Option(
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_LONG,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_HAS_VAL,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_DESC
        );
        optionExpenseCategory.setRequired(COMMAND_VIEW_EXPENSE_ARG_EXPENSE_CATEGORY_IS_MAND);
        Option optionExpenseName = new Option(
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_LONG,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_HAS_VAL,
                COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_DESC
        );
        optionExpenseName.setRequired(COMMAND_VIEW_EXPENSE_ARG_EXPENSE_NAME_IS_MAND);

        Options options = new Options();
        options.addOption(optionExpenseIndex);
        options.addOption(optionExpenseCategory);
        options.addOption(optionExpenseName);

        return options;
    }

    //@@author xzynos

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
        optionExpenseIndex.setRequired(COMMAND_DELETE_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionExpenseIndex);

        return options;
    }

    //@@author xzynos

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
        optionExpenseIndex.setRequired(COMMAND_EDIT_EXPENSE_ARG_EXPENSE_INDEX_IS_MAND);
        Option optionName = new Option(
                COMMAND_EDIT_EXPENSE_ARG_NAME,
                COMMAND_EDIT_EXPENSE_ARG_NAME_LONG,
                COMMAND_EDIT_EXPENSE_ARG_NAME_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_NAME_DESC
        );
        optionName.setRequired(COMMAND_EDIT_EXPENSE_ARG_NAME_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_LONG,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_EDIT_EXPENSE_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_LONG,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_EDIT_EXPENSE_ARG_DESCRIPTION_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_LONG,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_EDIT_EXPENSE_ARG_AMOUNT_IS_MAND);
        Option optionCategory = new Option(
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_LONG,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_CATEGORY_DESC
        );
        optionCategory.setRequired(COMMAND_EDIT_EXPENSE_ARG_CATEGORY_IS_MAND);
        Option optionRemarks = new Option(
                COMMAND_EDIT_EXPENSE_ARG_REMARKS,
                COMMAND_EDIT_EXPENSE_ARG_REMARKS_LONG,
                COMMAND_EDIT_EXPENSE_ARG_REMARKS_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_REMARKS_DESC
        );
        optionRemarks.setRequired(COMMAND_EDIT_EXPENSE_ARG_REMARKS_IS_MAND);
        Option optionCurrency = new Option(
                COMMAND_EDIT_EXPENSE_ARG_CURRENCY,
                COMMAND_EDIT_EXPENSE_ARG_CURRENCY_LONG,
                COMMAND_EDIT_EXPENSE_ARG_CURRENCY_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_CURRENCY_DESC
        );
        optionCurrency.setRequired(COMMAND_EDIT_EXPENSE_ARG_CURRENCY_IS_MAND);
        Option optionModeOfPayment = new Option(
                COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT,
                COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_LONG,
                COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_HAS_VAL,
                COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_DESC
        );
        optionModeOfPayment.setRequired(COMMAND_EDIT_EXPENSE_ARG_MODE_OF_PAYMENT_IS_MAND);

        Options options = new Options();
        options.addOption(optionExpenseIndex);
        options.addOption(optionName);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionAmount);
        options.addOption(optionCategory);
        options.addOption(optionRemarks);
        options.addOption(optionCurrency);
        options.addOption(optionModeOfPayment);

        return options;
    }

    //@@author jeyvia

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
        optionType.setRequired(COMMAND_SORT_EXPENSE_ARG_TYPE_IS_MAND);
        Option optionOrder = new Option(
                COMMAND_SORT_EXPENSE_ARG_ORDER,
                COMMAND_SORT_EXPENSE_ARG_ORDER_LONG,
                COMMAND_SORT_EXPENSE_ARG_ORDER_HAS_VAL,
                COMMAND_SORT_EXPENSE_ARG_ORDER_DESC
        );
        optionOrder.setRequired(COMMAND_SORT_EXPENSE_ARG_ORDER_IS_MAND);

        Options options = new Options();
        options.addOption(optionType);
        options.addOption(optionOrder);

        return options;
    }

    //@@author jeyvia

    /**
     * Generates an {@link Options} object with required arguments for command Change-Currency.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandConvertCurrencyOptions() {
        Option optionExpenseIndex = new Option(
                COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX,
                COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_LONG,
                COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_HAS_VAL,
                COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_DESC
        );
        optionExpenseIndex.setRequired(COMMAND_CONVERT_CURRENCY_ARG_EXPENSE_INDEX_IS_MAND);
        Option optionCurrency = new Option(
                COMMAND_CONVERT_CURRENCY_ARG_CURRENCY,
                COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_LONG,
                COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_HAS_VAL,
                COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_DESC
        );
        optionCurrency.setRequired(COMMAND_CONVERT_CURRENCY_ARG_CURRENCY_IS_MAND);
        Option optionRate = new Option(
                COMMAND_CONVERT_CURRENCY_ARG_RATE,
                COMMAND_CONVERT_CURRENCY_ARG_RATE_LONG,
                COMMAND_CONVERT_CURRENCY_ARG_RATE_HAS_VAL,
                COMMAND_CONVERT_CURRENCY_ARG_RATE_DESC
        );
        optionRate.setRequired(COMMAND_CONVERT_CURRENCY_ARG_RATE_IS_MAND);

        Options options = new Options();
        options.addOption(optionExpenseIndex);
        options.addOption(optionCurrency);
        options.addOption(optionRate);

        return options;
    }

    //@@author penguin-s

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
        optionName.setRequired(COMMAND_ADD_TARGET_ARG_NAME_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_ADD_TARGET_ARG_AMOUNT,
                COMMAND_ADD_TARGET_ARG_AMOUNT_LONG,
                COMMAND_ADD_TARGET_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_ADD_TARGET_ARG_AMOUNT_IS_MAND);
        Option optionCurrentAmount = new Option(
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_LONG,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_DESC
        );
        optionCurrentAmount.setRequired(COMMAND_ADD_TARGET_ARG_CURRENT_AMOUNT_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_ADD_TARGET_ARG_DATE_TIME,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_LONG,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_ADD_TARGET_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_ADD_TARGET_ARG_DESCRIPTION,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_TARGET_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_ADD_TARGET_ARG_DESCRIPTION_IS_MAND);

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionCurrentAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command View-Target.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandViewTargetOptions() {
        Option optionTargetIndex = new Option(
                COMMAND_VIEW_TARGET_ARG_TARGET_INDEX,
                COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_LONG,
                COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_HAS_VAL,
                COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_DESC
        );
        optionTargetIndex.setRequired(COMMAND_VIEW_TARGET_ARG_TARGET_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionTargetIndex);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command Delete-Target.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandDeleteTargetOptions() {
        Option optionTargetIndex = new Option(
                COMMAND_DELETE_TARGET_ARG_TARGET_INDEX,
                COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_LONG,
                COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_HAS_VAL,
                COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_DESC
        );
        optionTargetIndex.setRequired(COMMAND_DELETE_TARGET_ARG_TARGET_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionTargetIndex);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command Edit-Target.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandEditTargetOptions() {
        Option optionTargetIndex = new Option(
                COMMAND_EDIT_TARGET_ARG_TARGET_INDEX,
                COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_LONG,
                COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_DESC
        );
        optionTargetIndex.setRequired(COMMAND_EDIT_TARGET_ARG_TARGET_INDEX_IS_MAND);
        Option optionName = new Option(
                COMMAND_EDIT_TARGET_ARG_NAME,
                COMMAND_EDIT_TARGET_ARG_NAME_LONG,
                COMMAND_EDIT_TARGET_ARG_NAME_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_NAME_DESC
        );
        optionName.setRequired(COMMAND_EDIT_TARGET_ARG_NAME_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_EDIT_TARGET_ARG_DATE_TIME,
                COMMAND_EDIT_TARGET_ARG_DATE_TIME_LONG,
                COMMAND_EDIT_TARGET_ARG_DATE_TIME_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_EDIT_TARGET_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_EDIT_TARGET_ARG_DESCRIPTION,
                COMMAND_EDIT_TARGET_ARG_DESCRIPTION_LONG,
                COMMAND_EDIT_TARGET_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_EDIT_TARGET_ARG_DESCRIPTION_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_EDIT_TARGET_ARG_AMOUNT,
                COMMAND_EDIT_TARGET_ARG_AMOUNT_LONG,
                COMMAND_EDIT_TARGET_ARG_AMOUNT_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_EDIT_TARGET_ARG_AMOUNT_IS_MAND);
        Option optionCurrentAmount = new Option(
                COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT,
                COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_LONG,
                COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_HAS_VAL,
                COMMAND_EDIT_TARGET_ARG_CURRENT_AMOUNT_DESC
        );

        Options options = new Options();
        options.addOption(optionTargetIndex);
        options.addOption(optionName);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionAmount);
        options.addOption(optionCurrentAmount);

        return options;
    }

    //@@author penguin-s

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
        optionName.setRequired(COMMAND_ADD_INCOME_ARG_NAME_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_ADD_INCOME_ARG_AMOUNT,
                COMMAND_ADD_INCOME_ARG_AMOUNT_LONG,
                COMMAND_ADD_INCOME_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_ADD_INCOME_ARG_AMOUNT_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_ADD_INCOME_ARG_DATE_TIME,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_LONG,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_ADD_INCOME_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_ADD_INCOME_ARG_DESCRIPTION,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_INCOME_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_ADD_INCOME_ARG_DESCRIPTION_IS_MAND);

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionAmount);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command View-Income.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandViewIncomeOptions() {
        Option optionIncomeIndex = new Option(
                COMMAND_VIEW_INCOME_ARG_INCOME_INDEX,
                COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_LONG,
                COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_HAS_VAL,
                COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_DESC
        );
        optionIncomeIndex.setRequired(COMMAND_VIEW_INCOME_ARG_INCOME_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionIncomeIndex);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command Delete-Income.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandDeleteIncomeOptions() {
        Option optionIncomeIndex = new Option(
                COMMAND_DELETE_INCOME_ARG_INCOME_INDEX,
                COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_LONG,
                COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_HAS_VAL,
                COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_DESC
        );
        optionIncomeIndex.setRequired(COMMAND_DELETE_INCOME_ARG_INCOME_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionIncomeIndex);

        return options;
    }

    //@@author penguin-s

    /**
     * Generates an {@link Options} object with required arguments for command Edit-Income.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandEditIncomeOptions() {
        Option optionIncomeIndex = new Option(
                COMMAND_EDIT_INCOME_ARG_INCOME_INDEX,
                COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_LONG,
                COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_HAS_VAL,
                COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_DESC
        );
        optionIncomeIndex.setRequired(COMMAND_EDIT_INCOME_ARG_INCOME_INDEX_IS_MAND);
        Option optionName = new Option(
                COMMAND_EDIT_INCOME_ARG_NAME,
                COMMAND_EDIT_INCOME_ARG_NAME_LONG,
                COMMAND_EDIT_INCOME_ARG_NAME_HAS_VAL,
                COMMAND_EDIT_INCOME_ARG_NAME_DESC
        );
        optionName.setRequired(COMMAND_EDIT_INCOME_ARG_NAME_IS_MAND);
        Option optionDateTime = new Option(
                COMMAND_EDIT_INCOME_ARG_DATE_TIME,
                COMMAND_EDIT_INCOME_ARG_DATE_TIME_LONG,
                COMMAND_EDIT_INCOME_ARG_DATE_TIME_HAS_VAL,
                COMMAND_EDIT_INCOME_ARG_DATE_TIME_DESC
        );
        optionDateTime.setRequired(COMMAND_EDIT_INCOME_ARG_DATE_TIME_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_EDIT_INCOME_ARG_DESCRIPTION,
                COMMAND_EDIT_INCOME_ARG_DESCRIPTION_LONG,
                COMMAND_EDIT_INCOME_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_EDIT_INCOME_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_EDIT_INCOME_ARG_DESCRIPTION_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_EDIT_INCOME_ARG_AMOUNT,
                COMMAND_EDIT_INCOME_ARG_AMOUNT_LONG,
                COMMAND_EDIT_INCOME_ARG_AMOUNT_HAS_VAL,
                COMMAND_EDIT_INCOME_ARG_AMOUNT_DESC
        );

        Options options = new Options();
        options.addOption(optionIncomeIndex);
        options.addOption(optionName);
        options.addOption(optionDateTime);
        options.addOption(optionDescription);
        options.addOption(optionAmount);

        return options;
    }

    //@@author xzynos

    /**
     * Generates an {@link Options} object with required arguments for command Add-RecurringPayment.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandAddRecurringPaymentOptions() {
        Option optionName = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_DESC
        );
        optionName.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_NAME_IS_MAND);
        Option optionInterval = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_DESC
        );
        optionInterval.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_INTERVAL_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_AMOUNT_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_DESCRIPTION_IS_MAND);
        Option optionCategory = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_DESC
        );
        optionCategory.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_CATEGORY_IS_MAND);
        Option optionCurrency = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_DESC
        );
        optionCurrency.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_CURRENCY_IS_MAND);
        Option optionModeOfPayment = new Option(
                COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_LONG,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_HAS_VAL,
                COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_DESC
        );
        optionModeOfPayment.setRequired(COMMAND_ADD_RECURRING_PAYMENT_ARG_MODE_OF_PAYMENT_IS_MAND);

        Options options = new Options();
        options.addOption(optionName);
        options.addOption(optionInterval);
        options.addOption(optionAmount);
        options.addOption(optionDescription);
        options.addOption(optionCategory);
        options.addOption(optionCurrency);
        options.addOption(optionModeOfPayment);

        return options;
    }

    //@@author xzynos

    /**
     * Generates an {@link Options} object with required arguments for command View-RecurringPayment.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandViewRecurringPaymentOptions() {
        Option optionRecurringPaymentIndex = new Option(
                COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX,
                COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG,
                COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL,
                COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC
        );
        optionRecurringPaymentIndex.setRequired(COMMAND_VIEW_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionRecurringPaymentIndex);

        return options;
    }

    //@@author xzynos

    /**
     * Generates an {@link Options} object with required arguments for command Delete-RecurringPayment.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandDeleteRecurringPaymentOptions() {
        Option optionRecurringPaymentIndex = new Option(
                COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX,
                COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG,
                COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL,
                COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC
        );
        optionRecurringPaymentIndex.setRequired(COMMAND_DELETE_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND);

        Options options = new Options();
        options.addOption(optionRecurringPaymentIndex);

        return options;
    }

    //@@author xzynos

    /**
     * Generates an {@link Options} object with required arguments for command Edit-RecurringPayment.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandEditRecurringPaymentOptions() {
        Option optionRecurringPaymentIndex = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_DESC
        );
        optionRecurringPaymentIndex.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_RECURRING_PAYMENT_INDEX_IS_MAND);
        Option optionName = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_DESC
        );
        optionName.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_NAME_IS_MAND);
        Option optionInterval = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_DESC
        );
        optionInterval.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_INTERVAL_IS_MAND);
        Option optionDescription = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_DESC
        );
        optionDescription.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_DESCRIPTION_IS_MAND);
        Option optionAmount = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_DESC
        );
        optionAmount.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_AMOUNT_IS_MAND);
        Option optionCategory = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_DESC
        );
        optionCategory.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_CATEGORY_IS_MAND);
        Option optionCurrency = new Option(
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_LONG,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_HAS_VAL,
                COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_DESC
        );
        optionCurrency.setRequired(COMMAND_EDIT_RECURRING_PAYMENT_ARG_CURRENCY_IS_MAND);

        Options options = new Options();
        options.addOption(optionRecurringPaymentIndex);
        options.addOption(optionName);
        options.addOption(optionInterval);
        options.addOption(optionDescription);
        options.addOption(optionAmount);
        options.addOption(optionCategory);
        options.addOption(optionCurrency);

        return options;
    }

    //@@author LokQiJun

    /**
     * Generates an {@link Options} object with required arguments for command Merge-File.
     *
     * @return {@link Options} object initialized with the required arguments.
     */
    public static Options getCommandMergeExternalFileOptions() {
        Option optionMergeFilePath = new Option(
                COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH,
                COMMAND_MERGE_FILE_ARG_MERGE_FILE_PATH_LONG,
                COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_HAS_VAL,
                COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_DESC
        );
        optionMergeFilePath.setRequired(COMMAND_MERGE_EXTERNAL_FILE_ARG_MERGE_EXTERNAL_FILE_PATH_IS_MAND);

        Options options = new Options();
        options.addOption(optionMergeFilePath);

        return options;
    }
}
