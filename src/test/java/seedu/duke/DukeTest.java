package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Add;
import seedu.duke.commands.Command;
import seedu.duke.commands.Check;
import seedu.duke.exceptions.InvalidCommandWordException;
import seedu.duke.exceptions.InvalidGradeException;
import seedu.duke.exceptions.InvalidInputContentException;
import seedu.duke.exceptions.InvalidInputFormatException;
import seedu.duke.exceptions.InvalidMcException;
import seedu.duke.exceptions.InvalidSemesterException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


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
    void testInvalidSemester() {
        System.out.println("Running JUnit tests for exceptions in case of invalid semester");
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("S2Y1"));//Semester is given before the Year
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("S1")); //Only semester is given
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("Y3")); //Only year is given
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("Y*SJ")); //Special characters and letters are given for year and semester
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("Y9S2"));//Year greater than 6 is given
        assertThrows(InvalidSemesterException.class, () -> Add.checkYear("Y3S7"));//Semester greater than 2 is given
    }

    @Test
    void testInvalidMc() {
        System.out.println("Running JUnit tests for exceptions in case of invalid mc");
        assertThrows(InvalidMcException.class, () -> Add.checkMc(-6)); //Negative mc is given
        assertThrows(InvalidMcException.class, () -> Add.checkMc(21)); //MC greater than 20 is given
        assertThrows(InvalidMcException.class, () -> Add.checkMcString("999999999999999999999999999999999999999999999999999999999999999999999999")); // Huge numeric mc is given
        assertThrows(InvalidMcException.class, () -> Add.checkMcString("adr*")); // letters and special character for mc is given
    }



    @Test
    void testInvalidGrade() {
        System.out.println("Running JUnit tests for exceptions in case of invalid grade");
        assertThrows(InvalidGradeException.class, () -> Add.checkGrade("9987")); //Numeric grade is given
        assertThrows(InvalidGradeException.class, () -> Add.checkGrade("D-")); // One of the grades that are not possible is given
    }

    @Test
    void testFind() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Find");

        //add the modules
        String input1 = "add m/cs2105 s/y1s1 mc/4 g/A+";
        String input2 = "add m/cs2106 s/y1s2 mc/4 g/F";
        String input3 = "add m/cs2107 s/y2s1 mc/4 g/A";
        ModuleList modulelist = new ModuleList();
        Command c = Parser.parse(input1);
        c.execute(modulelist);
        c = Parser.parse(input2);
        c.execute(modulelist);
        c = Parser.parse(input3);
        c.execute(modulelist);

        assertEquals(1,ModuleList.findMatchingModules("CS2105").size()); // find keyword in module code
        assertEquals(3,ModuleList.findMatchingModules("21").size()); // find partial keyword in module code
        assertEquals(1,ModuleList.findMatchingModules("F").size()); // find keyword in grade
        assertEquals(2,ModuleList.findMatchingModules("Y1").size()); // find keyword in semester
        assertEquals(3,ModuleList.findMatchingModules("4").size()); // find keyword in MC
        assertEquals(0,ModuleList.findMatchingModules("B+").size()); // find keyword that does not exist

        //Delete the modules

        String inputDelete1 = "delete m/CS2105";
        String inputDelete2 = "delete m/CS2106";
        String inputDelete3 = "delete m/CS2107";
        c = Parser.parse(inputDelete1);
        c.execute(modulelist);
        c = Parser.parse(inputDelete2);
        c.execute(modulelist);
        c = Parser.parse(inputDelete3);
        c.execute(modulelist);
    }

    @Test
    void testClearforSemester() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Clear for an individual semester");

        //add the modules
        String input1 = "add m/cs2105 s/y1s1 mc/4 g/A+";
        String input2 = "add m/cs2106 s/y1s2 mc/4 g/F";
        String input3 = "add m/cs2107 s/y1s1 mc/4 g/A";
        ModuleList modulelist = new ModuleList();
        Command c = Parser.parse(input1);
        c.execute(modulelist);
        c = Parser.parse(input2);
        c.execute(modulelist);
        c = Parser.parse(input3);
        c.execute(modulelist);
        int initialCount = modulelist.getCount();

        //give command to clear y1s1
        String inputToClear = "clear s/y1s1";
        c = Parser.parse(inputToClear);
        c.execute(modulelist);
        int finalCount = modulelist.getCount();
        assertEquals(initialCount - 2, finalCount);

        //Delete CS2106 as well

        String inputDelete = "delete m/CS2106";
        c = Parser.parse(inputDelete);
        c.execute(modulelist);
    }

    @Test
    void testClearforAll() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Clear for all");

        //add the modules
        String input1 = "add m/cs2105 s/y1s1 mc/4 g/A+";
        String input2 = "add m/cs2106 s/y1s2 mc/4 g/F";
        String input3 = "add m/cs2107 s/y1s1 mc/4 g/A";
        ModuleList modulelist = new ModuleList();
        Command c = Parser.parse(input1);
        c.execute(modulelist);
        c = Parser.parse(input2);
        c.execute(modulelist);
        c = Parser.parse(input3);
        c.execute(modulelist);
        int initialCount = modulelist.getCount();

        //give command to clear y1s1
        String inputToClear = "clear all";
        c = Parser.parse(inputToClear);
        c.execute(modulelist);
        int finalCount = modulelist.getCount();
        assertEquals(initialCount - 3, finalCount);

    }

    @Test
    void testAdd() throws InvalidInputContentException {
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
    void testRepetitionAdd() throws InvalidInputContentException {
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
    void testDelete() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Delete");

        // Delete - Checks whether a module is being being deleted after being added

        String inputAdd = "add m/cs2113 s/y1s1 mc/4 g/A+";
        String inputDelete = "delete m/cs2113";
        ModuleList modulelist = new ModuleList();
        final int initialCount = modulelist.getCount();

        // Adds a new module
        Command c = Parser.parse(inputAdd);
        c.execute(modulelist);

        // Deletes a new module
        c = Parser.parse(inputDelete);
        c.execute(modulelist);

        // Gets updated count in module list
        final int finalCount = modulelist.getCount();

        // finalCount and initialCount supposed to be same count
        assertEquals(initialCount, finalCount);
    }

    @Test
    void testView() throws InvalidInputContentException {
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
    void testMcs() throws InvalidInputContentException {
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

    @Test
    void testCheckNOC() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Check NOC");

        // check NOC - Test whether the user is eligible for NOC based on the modules the user has added

        ModuleList modulelist = new ModuleList();
        Check check = new Check("NOC");

        // Check initial NOC eligibility
        boolean initialCheckNOCSem = check.checkNOCSem();
        assertFalse(initialCheckNOCSem);
        boolean initialCheckNOCMc = check.checkNOCMc();
        assertFalse(initialCheckNOCMc);
        boolean initialCheckNOC = check.checkNOC();
        assertFalse(initialCheckNOC);

        ArrayList<String> inputArray = new ArrayList<>();
        inputArray.add("add m/cs1011 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1012 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1013 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1014 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1015 s/y1s1 mc/4 g/A+");

        inputArray.add("add m/cs1021 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1022 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1023 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1024 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1025 s/y1s2 mc/4 g/A+");

        inputArray.add("add m/cs2011 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2012 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2013 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2014 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2015 s/y2s1 mc/4 g/A+");

        inputArray.add("add m/cs2021 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2022 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2023 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2024 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2025 s/y2s2 mc/4 g/A+");

        // Adds all the modules in inputArray
        for (String input : inputArray) {
            Command c = Parser.parse(input);
            c.execute(modulelist);
        }

        // check final NOC eligibility
        boolean finalCheckNOCSem = check.checkNOCSem();
        assertTrue(finalCheckNOCSem);
        boolean finalCheckNOCMc = check.checkNOCMc();
        assertTrue(finalCheckNOCMc);
        boolean finalCheckNOC = check.checkNOC();
        assertTrue(finalCheckNOC);

        ModuleList.modules.clear();
    }

    @Test
    void testCheckSEP() throws InvalidInputContentException {
        System.out.println("Running JUnit Test for Check SEP");

        // check SEP - Test whether the user is eligible for SEP based on the modules the user has added

        ModuleList modulelist = new ModuleList();
        Check check = new Check("SEP");

        // Check initial NOC eligibility
        boolean initialCheckSEPSem = check.checkSEPSem();
        assertFalse(initialCheckSEPSem);
        boolean initialCheckSEPCAP = check.checkSEPCAP();
        assertFalse(initialCheckSEPCAP);
        boolean initialCheckSEP = check.checkSEP();
        assertFalse(initialCheckSEP);

        ArrayList<String> inputArray = new ArrayList<>();
        inputArray.add("add m/cs1011 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1012 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1013 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1014 s/y1s1 mc/4 g/A+");
        inputArray.add("add m/cs1015 s/y1s1 mc/4 g/A+");

        inputArray.add("add m/cs1021 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1022 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1023 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1024 s/y1s2 mc/4 g/A+");
        inputArray.add("add m/cs1025 s/y1s2 mc/4 g/A+");

        inputArray.add("add m/cs2011 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2012 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2013 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2014 s/y2s1 mc/4 g/A+");
        inputArray.add("add m/cs2015 s/y2s1 mc/4 g/A+");

        inputArray.add("add m/cs2021 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2022 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2023 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2024 s/y2s2 mc/4 g/A+");
        inputArray.add("add m/cs2025 s/y2s2 mc/4 g/A+");

        // Adds all the modules in inputArray
        for (String input : inputArray) {
            Command c = Parser.parse(input);
            c.execute(modulelist);
        }

        // check final NOC eligibility
        boolean finalCheckSEPSem = check.checkSEPSem();
        assertTrue(finalCheckSEPSem);
        boolean finalCheckSEPCAP = check.checkSEPCAP();
        assertTrue(finalCheckSEPCAP);
        boolean finalCheckSEP = check.checkSEP();
        assertTrue(finalCheckSEP);

        ModuleList.modules.clear();
    }
}
