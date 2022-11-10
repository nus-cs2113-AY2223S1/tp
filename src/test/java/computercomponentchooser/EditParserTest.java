package computercomponentchooser;

import org.junit.jupiter.api.Test;

import static computercomponentchooser.ComputerComponentChooser.editParser;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EditParserTest {

    @Test
    public void testGetParameter() {
        String test = "add/cpu/123/456/lga/4";
        assertTrue(editParser.getParameter(test, 0).equals("add"));
        assertTrue(editParser.getParameter(test, 1).equals("cpu"));
        assertTrue(editParser.getParameter(test, 2).equals("123"));
        assertTrue(editParser.getParameter(test, 3).equals("456"));
        assertTrue(editParser.getParameter(test, 4).equals("lga"));
        assertTrue(editParser.getParameter(test, 5).equals("4"));

        assertFalse(editParser.getParameter(test, 0).equals("add "));
        assertFalse(editParser.getParameter(test, 1).equals("cpu "));
        assertFalse(editParser.getParameter(test, 2).equals("123 "));
        assertFalse(editParser.getParameter(test, 3).equals("456 "));
        assertFalse(editParser.getParameter(test, 4).equals("lga "));
        assertFalse(editParser.getParameter(test, 5).equals("4 "));

    }

    @Test
    public void testCheckBack() {
        assertTrue(editParser.checkBack("back"));
        assertTrue(editParser.checkBack(" back"));
        assertTrue(editParser.checkBack(" back "));
        assertTrue(editParser.checkBack(" back "));
        assertFalse(editParser.checkBack("no"));
    }
}
