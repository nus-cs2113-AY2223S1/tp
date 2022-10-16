package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;

class DukeTest {

    @Test
    public void testAdd() {
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
        assertEquals(initialCount + 1,finalCount);

        // Deletes the module
        String inputDelete = "delete m/cs2113";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

    }

    @Test
    public void testDelete() {
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
        assertEquals(initialCount,finalCount);
    }

    @Test
    public void testView() {
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
    public void testMcs() {
        System.out.println("Running JUnit Test for mcs");

        // mcs - Test whether we are able to view mcs taken for semester after adding something

        String inputAdd = "add m/cs2030 s/y2s1 mc/4 g/A+";
        String inputMcs = "mcs s/y2s1";
        ModuleList modulelist = new ModuleList();
        int initialMcsCount = modulelist.getMcsCount();

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
