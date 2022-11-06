package recipeditor.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recipeditor.storage.Storage;

import java.io.FileNotFoundException;


public class EditorTest {
    @Test
    void testFileNotFound() {
        Editor editor = new Editor();
        try {
            boolean isSave = editor.enterEditor("adfasdf");
            Assertions.assertTrue(isSave);
        } catch (FileNotFoundException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void testSaveExit() {
        Editor editor = new Editor();
        try {
            editor.state = Editor.EditorState.SAVE;
            boolean isSave = editor.enterEditor(Storage.TEMPLATE_FILE_PATH);
            Storage.deleteFile(Storage.TEMPORARY_FILE_PATH);
            Assertions.assertTrue(isSave);
        } catch (FileNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    void testExitOnly() {
        Editor editor = new Editor();
        try {
            editor.state = Editor.EditorState.EXIT;
            boolean isSave = editor.enterEditor(Storage.TEMPLATE_FILE_PATH);
            Assertions.assertFalse(isSave);
        } catch (FileNotFoundException e) {
            Assertions.fail();
        }
    }
}
