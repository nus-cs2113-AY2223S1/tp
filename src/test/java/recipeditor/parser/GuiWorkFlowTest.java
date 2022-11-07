package recipeditor.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class GuiWorkFlowTest {

    @Test
    void enterGuiWorkFlow_FileNotFound_fails() {
        try {
            new GuiWorkFlow("");
            Assertions.fail();
        } catch (FileNotFoundException e) {
            Assertions.assertTrue(true);
        }
    }

}
