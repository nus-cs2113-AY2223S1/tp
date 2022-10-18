package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidCommandWordException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DukeTest {

    @Test
    void testInvalidCommandWord() {
        System.out.println("Running JUnit tests for exceptions in case of invalid command word");
        String input = "ad m/cs2113 s/y1s1 mc/4 g/A+";
        String[] splitText = input.split(" ");
        int length = splitText[0].length();
        String content = " ";
        if (input.length() > length) {
            content = input.substring(length + 1);
        }
        String finalContent = content;
        assertThrows(InvalidCommandWordException.class, () -> Parser.specificCase(splitText[0], finalContent));
    }

    @Test
    void testInvalidInputFormat() {
        System.out.println("Running JUnit tests for exceptions in case of invalid input format");
        String input = "add o/cs2113 s/y1s1 mc/4 g/A+";
        String[] splitText = input.split(" ");
        int length = splitText[0].length();
        String content = " ";
        if (input.length() > length) {
            content = input.substring(length + 1);
        }
        String finalContent = content;
        assertThrows(InvalidInputFormatException.class, () -> Parser.specificCase(splitText[0], finalContent));
    }

    @Test
    void testEmptyInputContent() {
        System.out.println("Running JUnit tests for exceptions in case of invalid input content of empty field(s)");
        String input = "add m/cs2113 s/ mc/4 g/A+";
        String[] splitText = input.split(" ");
        int length = splitText[0].length();
        String content = " ";
        if (input.length() > length) {
            content = input.substring(length + 1);
        }
        String finalContent = content;
        assertThrows(InvalidInputContentException.class, () -> Parser.specificCase(splitText[0], finalContent));
    }

    @Test
    void testAdd() {
        System.out.println("Running JUnit Test for Add");

        // Add - Checks whether a module is being added

        String input = "add m/cs2113 s/y1s1 mc/4 g/A+";
        ModuleList modulelist = new ModuleList();
        int initialCount = modulelist.getCount();

        // Adds a new module
        Command c = Parser.parse(input);
        c.execute(modulelist);

        // Gets updated count in module list
        int finalCount = modulelist.getCount();

        // finalCount is supposed to be 1 more than initialCount since 1 module is added
        assertEquals(initialCount + 1, finalCount);

        // Deletes the module
        String inputDelete = "delete m/cs2113";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

    }

    @Test
    void testRepetitionAdd() {
        System.out.println("Running JUnit Test for Adding repetitive mods");
        String input1 = "add m/cs2113 s/y1s1 mc/4 g/A+";
        String input2 = "add m/cs2113 s/y2s1 mc/4 g/b";
        ModuleList modulelist = new ModuleList();

        Command c = Parser.parse(input1);
        c.execute(modulelist);
        int initialCount = modulelist.getCount();

        c = Parser.parse(input2);
        c.execute(modulelist);
        int finalCount = modulelist.getCount();

        assertEquals(initialCount, finalCount);

        // Deletes the module
        String inputDelete = "delete m/cs2113";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

    }

    @Test
    void testDelete() {
        System.out.println("Running JUnit Test for Delete");

        // Delete - Checks whether a module is being being deleted after being added

        String inputAdd = "add m/cs2113 s/y1s1 mc/4 g/A+";
        String inputDelete = "delete m/cs2113";
        ModuleList modulelist = new ModuleList();
        int initialCount = modulelist.getCount();

        // Adds a new module
        Command c = Parser.parse(inputAdd);
        c.execute(modulelist);

        // Deletes a new module
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

        // Gets updated count in module list
        int finalCount = modulelist.getCount();

        // finalCount and initialCount supposed to be same count
        assertEquals(initialCount, finalCount);
    }

    @Test
    void testView() {
        System.out.println("Running JUnit Test for view");

        // View - Checks whether we are able to view the modules taken for semester after adding something

        String inputAdd = "add m/cs2113 s/y1s1 mc/4 g/A+";
        String inputView = "view s/y1s1";
        ModuleList modulelist = new ModuleList();

        // Adds a new module
        Command c = Parser.parse(inputAdd);
        c.execute(modulelist);

        // View modules for semester
        c = Parser.parse(inputView);
        c.execute(modulelist);

        // Gets updated count in module list
        int viewCount = modulelist.getViewCount();

        // viewCount should have 1 module
        assertEquals(1, viewCount);

        // Deletes the module
        String inputDelete = "delete m/cs2113";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

    }

    @Test
    void testMcs() {
        System.out.println("Running JUnit Test for mcs");

        // mcs - Test whether we are able to view mcs taken for semester after adding something

        String inputAdd = "add m/cs2030 s/y2s1 mc/4 g/A+";
        String inputMcs = "mcs s/y2s1";
        ModuleList modulelist = new ModuleList();
        int initialMcsCount = modulelist.getMcsCount();
        assertEquals(0, initialMcsCount);

        // Adds a new module
        Command c = Parser.parse(inputAdd);
        c.execute(modulelist);

        // View mcs for the module
        c = Parser.parse(inputMcs);
        c.execute(modulelist);

        // Gets updated MCs count in module list
        int finalMcsCount = modulelist.getMcsCount();

        // mcsCount supposed to be 4
        assertEquals(4, finalMcsCount);

        // Deletes the module
        String inputDelete = "delete m/cs2030";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

    }

}
