package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.item.Category;
import seedu.duke.ui.Ui;

//@@author bdthanh
/**
 * A representation of a command to list all categories.
 */
public class ListCategoriesCommand extends Command {
    /**
     * Executes ListCategoriesCommand.
     *
     * @return false
     */
    public boolean executeCommand() {
        Ui.printResponse(Category.listCategories());
        return false;
    }
}
