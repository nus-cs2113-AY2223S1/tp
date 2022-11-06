package computercomponentchooser;

import computercomponentchooser.exceptions.DuplicateBuildException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static computercomponentchooser.ComputerComponentChooser.buildManager;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void setUp() throws DuplicateBuildException {
        buildManager = new BuildManager();
        parser = new Parser(buildManager);
        Build test = new Build("test");
        buildManager.addBuild(test);
    }

    @Test
    public void testCheckBye() {
        assertTrue(Parser.checkBye("bye"));
        assertTrue(Parser.checkBye("bye "));
        assertTrue(Parser.checkBye(" bye"));
        assertTrue(Parser.checkBye(" bye "));
        assertFalse(Parser.checkBye("byebye"));
    }

    @Test
    public void testCheckEdit() {
        assertTrue(parser.checkEdit("edit/test"));
        assertTrue(parser.checkEdit("edit /test"));
        assertTrue(parser.checkEdit(" edit/test"));
        assertTrue(parser.checkEdit(" edit /test"));
        assertFalse(parser.checkEdit("edit/no"));
    }
}
