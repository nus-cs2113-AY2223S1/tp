package recipeditor.parser;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.storage.Storage;
import recipeditor.ui.Editor;
import recipeditor.ui.Ui;

import java.io.FileNotFoundException;

public class GUIWorkFlow {
    private static final String ABORT_QUESTION = "Do you want to ABORT? (Y/N)";

    private boolean validity;
    private Recipe recipe;

    public GUIWorkFlow(String path) {
        try {
            boolean saveToTemp = new Editor().enterEditor(path);
            boolean exitLoop = !saveToTemp;
            validity = false;
            recipe = new Recipe();
            while (!exitLoop) {
                try {
                    String content = Storage.loadFileContent(Storage.TEMPORARY_FILE_PATH);
                    recipe = new TextFileParser().parseTextToRecipe(content);
                    validity = true;
                    exitLoop = true;
                } catch (ParseFileException | FileNotFoundException e) {
                    Ui.showMessage(e.getMessage());
                    YesNoLoopAnswer ans = yesNoLoop(ABORT_QUESTION);
                    if (ans.equals(YesNoLoopAnswer.NO)) {
                        boolean saveToTempAgain = new Editor().enterEditor(Storage.TEMPORARY_FILE_PATH);
                        exitLoop = !saveToTempAgain;
                    } else {
                        exitLoop = true;
                    }
                }
            }
        } catch (FileNotFoundException e){
            Ui.showMessage(e.getMessage());
            validity = false;
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

    public boolean getValidity() {
        return validity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private enum YesNoLoopAnswer {
        YES, NO, OTHER
    }
}