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
    private final Mode mode;
    private boolean saveToTemp;
    private String path;
    private boolean isValid = false;
    private Recipe recipe = new Recipe();
    private boolean shouldExitLoop;

    /**
     * A class that handle the GUI call and the intermediate interaction between the GUI and CLI.
     * Ask the user whether they want to make changes when the format of the text in Editor is wrong.
     * @param inputPath load the content of the file
     * @throws FileNotFoundException Handled by the parser
     */
    public GuiWorkFlow(String inputPath) throws FileNotFoundException {
        path = inputPath;
        mode = getMode(path);
        saveToTemp = new Editor().enterEditor(path);
        shouldExitLoop = !saveToTemp;
        while (!shouldExitLoop) {
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
            isValid = true;
            shouldExitLoop = true;
        } catch (ParseFileException | FileNotFoundException | DuplicateRecipeTitleException e) {
            Ui.showMessage(e.getMessage());
            YesNoLoopAnswer ans = yesNoLoop(ABORT_QUESTION);
            if (ans.equals(YesNoLoopAnswer.YES)) {
                saveToTemp = new Editor().enterEditor(Storage.TEMPORARY_FILE_PATH);
                shouldExitLoop = !saveToTemp;
            } else {
                shouldExitLoop = true;
            }
        }
    }

    private void checkDuplicate() throws DuplicateRecipeTitleException {
        if (RecipeList.containsRecipe(recipe) && mode.equals(Mode.ADD)) {
            throw new DuplicateRecipeTitleException(DuplicateRecipeTitleException.DUPLICATE_IN_MODEL);
        }
    }

    public boolean getValid() {
        return isValid;
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