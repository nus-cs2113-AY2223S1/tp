package seedu.duke.data;

import seedu.duke.data.transaction.Category;

import java.util.ArrayList;

public class CategoryList {
    //@@author chydarren
    private static final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static ArrayList<Category> categories = new ArrayList<>();

    /**
     * Browses the list of transactions for unique categories and adds them into a category list.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public static void addCategories(TransactionList transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            Category category = new Category(transactions.getEntry(i).getCategory());

            // Adds a category in the list only if the category is not in list
            if (!categories.contains(category)) {
                categories.add(category);
            }
        }
    }

    /**
     * Calculates the total amount of transactions belonging to each category in the category list.
     *
     * @param transactions An instance of the TransactionList class.
     */
    public static void calculateTotalAmount(TransactionList transactions) {
        addCategories(transactions);
        for (Category category : categories) {
            int amount = category.getAmount();

            // Gets each transaction and if belongs to category, compute to total amount
            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.getEntry(i).getCategory().equals(category.getCategory())) {
                    amount += transactions.getEntry(i).getAmount();
                }
            }

            category.setAmount(amount);
        }
    }

    /**
     * List the statistics for the total amount of savings in each category.
     *
     * @return A string containing the formatted categories list.
     */
    public String listCategories() {
        String categoriesList = EMPTY_STRING;
        // Loops each category from the categories list
        for (Category category : categories) {
            categoriesList += category.toString() + LINE_SEPARATOR;
        }
        categories.clear();
        return categoriesList;
    }
}
