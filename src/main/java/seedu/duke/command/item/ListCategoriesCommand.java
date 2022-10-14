package seedu.duke.command.item;

import seedu.duke.command.Command;
import seedu.duke.item.Category;
import seedu.duke.ui.Ui;

public class ListCategoriesCommand extends Command {
    public boolean executeCommand() {
        Ui.printResponse(Category.listCategories());
        return false;
    }
}
