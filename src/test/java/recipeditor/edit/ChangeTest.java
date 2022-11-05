package recipeditor.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import recipeditor.exception.InvalidFlagException;
import recipeditor.exception.ParseException;
import recipeditor.parser.FlagType;
import recipeditor.recipe.Recipe;

public class ChangeTest {
    Recipe oldRecipe = new Recipe("title", "description");

    @Test
    void changeTitle_correctArgs_correctParsing() throws InvalidFlagException, ParseException {
        String newTitle = "new title";
        String[] input = { null, null, null, null, newTitle };
        EditModeCommand ed = new Change(FlagType.TITLE, input, oldRecipe);
        Recipe newRecipe = ed.execute();
        assertEquals(newRecipe.getTitle(), newTitle);
    }
}
