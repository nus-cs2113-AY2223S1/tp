package recipeditor.parser;

import recipeditor.exception.DuplicateRecipeTitleException;
import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;
import recipeditor.ui.Editor;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;

public class GuiWorkFlow {
    private static final String ABORT_QUESTION = "Do you want to FIX the recipe? (Y/N)";
    boolean saveToTemp;
    private boolean validity = false;
    private Recipe recipe = new Recipe();
    private final Mode mode;
    private boolean exitLoop;

    public GuiWorkFlow(String path) throws FileNotFoundException {
        mode = getMode(path);
        saveToTemp = new Editor().enterEditor(path);
        exitLoop = !saveToTemp;
        while (!exitLoop) {
            guiLoop();
        }
    }

    private static Mode getMode(String path) {
        if (path.equals(Storage.TEMPLATE_FILE_PATH)) {
            return Mode.ADD;
        } else {
            return Mode.EDIT;
        }
    }

    private static YesNoLoopAnswer yesNoLoop(String message) {
        do {
            Ui.showMessage(message);
            String input = Ui.readInput();
            switch (input.toLowerCase()) {
            default:
                break;
            case "n":
                return YesNoLoopAnswer.NO;
            case "y":
                return YesNoLoopAnswer.YES;
            }
        } while (true);
    }

    private void guiLoop() throws FileNotFoundException {
        try {
            String content = Storage.loadFileContent(Storage.TEMPORARY_FILE_PATH);
            recipe = new RecipeFileParser().parseTextToRecipe(content);
            checkDuplicate();
            validity = true;
            exitLoop = true;
        } catch (ParseFileException | FileNotFoundException | DuplicateRecipeTitleException e) {
            Ui.showMessage(e.getMessage());
            YesNoLoopAnswer ans = yesNoLoop(ABORT_QUESTION);
            if (ans.equals(YesNoLoopAnswer.YES)) {
                saveToTemp = new Editor().enterEditor(Storage.TEMPORARY_FILE_PATH);
                exitLoop = !saveToTemp;
            } else {
                exitLoop = true;
            }
        }
    }

    private void checkDuplicate() throws DuplicateRecipeTitleException {
        if (RecipeList.containsRecipe(recipe) && mode.equals(Mode.ADD)) {
            throw new DuplicateRecipeTitleException(DuplicateRecipeTitleException.DUPLICATE_IN_MODEL);
        }
    }

    public boolean getValidity() {
        return validity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private enum YesNoLoopAnswer {
        YES, NO
    }

    private enum Mode {
        ADD, EDIT
    }

}