package seedu.moneygowhere.data.category;

import java.util.ArrayList;

//@@author yuu-chennn
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class CategoryManager {
    private ArrayList<Category> categories;

    public CategoryManager() {
        categories = new ArrayList<>();
    }

    public void addCategory(String categoryName) {
        Category category = new Category(categoryName);
        categories.add(category);
    }

    public boolean hasCategory(String categoryName) {
        for (Category category : categories) {
            if (category.categoryName.equals(categoryName)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories);
    }
}
