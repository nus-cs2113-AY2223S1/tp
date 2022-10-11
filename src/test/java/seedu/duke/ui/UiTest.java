package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.user.UserModule;
//import seedu.duke.DukeException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {
    @Test
    public void testGreeting() {
        String expectedGreeting = "                        _____ ______ _____  \n"
                + "                       / ____|  ____|  __ \\ \n"
                + "   ___  __ _ ___ _   _| (___ | |__  | |__) |\n"
                + "  / _ \\/ _` / __| | | |\\___ \\|  __| |  ___/ \n"
                + " |  __/ (_| \\__ \\ |_| |____) | |____| |     \n"
                + "  \\___|\\__,_|___/\\__, |_____/|______|_|     \n"
                + "                  __/ |                     \n"
                + "                 |___/                      \n"
                + "Hello! Welcome to easySEP, your personal companion for planning your student exchange :-)\n"
                + "How may I help you today?\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGreeting, Ui.greetUser());
    }

    @Test
    public void testGoodbye() {
        String expectedGoodbye = "_____________________________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expectedGoodbye, Ui.sayByeToUser());
    }

    @Test
    public void testGetUserInputWithWhiteSpaces() {
        String input = "   /create    ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("/create", Ui.getUserInput());
    }

    @Test
    public void testPrintCommands() {
        String expected = "_____________________________________________________________________________\n"
                + "     " + "COMMAND   " + "FORMAT                                  " + "PURPOSE\n"
                + "     " + "--------  " + "--------------------------------------  " + "-------\n"
                + "     " + "create    " + "/create u/UNIVERSITY                    "
                + "Creates an empty module list for the input university\n"
                + "     " + "view      " + "/view MODULES                           "
                + "Displays all existing university modules mappings that are approved in the format\n"
                + "                                                       "
                + "[NUS Module Code] [NUS Module Title] [NUS Module Credits] -> [Partner University Name] "
                + "[Partner University Module Code] [Partner University Title]\n"
                + "     " + "view      " + "/view LISTS                             "
                + "Displays all existing university lists that have been created by the user\n"
                + "     " + "view      " + "/view u/UNIVERSITY                      "
                + "Displays all modules that have been added to the input university’s list in the format\n"
                + "                                                       "
                + "[Home University Module Code] → [Partner University Module Code]\n"
                + "     " + "add       " + "/add u/UNIVERSITY m/MODULECODE          "
                + "Add input Partner University module code to input university list                       \n"
                + "     " + "delete    " + "/delete u/UNIVERSITY m/MODULECODE       "
                + "Remove input Partner University module code from input university list                  \n"
                + "     " + "delete    " + "/delete u/UNIVERSITY                    "
                + "Delete input university list                        \n\n"
                + "     " + "Note: Words in UPPER_CASE are parameters that you should input as a user\n"
                + "     " + "Note: There should not be spaces in parameters, replace with underscore instead\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printCommands());
    }

    @Test
    public void testPrintModule() {
        UserModule dummy = new UserModule("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4");
        String expected = "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
            + " | Partner University: " + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs";
        assertEquals(expected, Ui.printModule(dummy));
    }

    @Test
    public void testPrintModuleAddedAcknowledgement() {
        UserModule dummy = new UserModule("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You added:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleAddedAcknowledgement(dummy));
    }

    @Test
    public void testPrintModuleUpdatedAcknowledgement() {
        UserModule dummy = new UserModule("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4");
        dummy.setComment("A+ or nothing");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You updated:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs" + "\n"
                + "With the following comment: " + dummy.getComment() + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleUpdatedAcknowledgement(dummy));

    }

    @Test
    public void testPrintModuleDeletedAcknowledgement() {
        UserModule dummy = new UserModule("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4");
        String expected = "_____________________________________________________________________________\n"
                + "Success! You deleted:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: "
                + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleDeletedAcknowledgement(dummy));
    }

    @Test
    public void testPrintPuListCreatedAcknowledgement() {
        String expected = "_____________________________________________________________________________\n"
                + "Success! You have created a new list for" + "Stanford University" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListCreatedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintPuListDeletedAcknowledgement() {
        String expected = "_____________________________________________________________________________\n"
                + "Success! You deleted the list for" + "Stanford University" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListDeletedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintModulesInList() {
        ArrayList<UserModule> modules = new ArrayList<UserModule>();
        UserModule dummy1 = new UserModule("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4");
        UserModule dummy2 = new UserModule("CPSC456", "ML",
                "CS3244", "Machine Learning", "4");
        modules.add(dummy1);
        modules.add(dummy2);
        String expected = "_____________________________________________________________________________\n" + "1. "
                + "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
                + " | Partner University: " + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " +  "4 MCs"
                + "\n" + "2. " + "NUS: " + "CS3244" + " " + "Machine Learning"
                + " | Partner University: " + "CPSC456" + " " + "ML" + " | Equivalent NUS Credits: " +  "4 MCs" + "\n"
                + "_____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModulesInList(modules));
    }
}
