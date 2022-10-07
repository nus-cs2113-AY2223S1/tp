package seedu.duke.data.transaction;

public class Category {
    private static final String PREFIX_CATEGORY = "[";
    private static final String POSTFIX_CATEGORY = "]";
    private static final String SYMBOL_DOLLAR = "$";

    private String category;
    private int amount;

    public Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s %s%d", PREFIX_CATEGORY, category, POSTFIX_CATEGORY,
                SYMBOL_DOLLAR, amount);
    }
}
