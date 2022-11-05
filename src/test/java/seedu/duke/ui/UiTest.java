package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidUniversityException;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Timetable;
import seedu.duke.university.University;
import seedu.duke.user.UserModuleMapping;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                + "Type /help if you need help with getting started for user commands\n"
                + "____________________________________________________________________________\n";
        assertEquals(expectedGreeting, Ui.greetUser());
    }

    @Test
    public void testGoodbye() {
        String expectedGoodbye = "____________________________________________________________________________\n"
                + " ___               _  _             \n"
                + "/  _>  ___  ___  _| || |_  _ _  ___ \n"
                + "| <_/\\/ . \\/ . \\/ . || . \\| | |/ ._>\n"
                + "`____/\\___/\\___/\\___||___/`_. |\\___.\n"
                + "                          <___'     \n"
                + "Hope to see you again soon!\n"
                + "____________________________________________________________________________\n";
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
        String expected = "____________________________________________________________________________\n"
                + "     " + "COMMAND   " + "FORMAT                                                " + "PURPOSE\n"
                + "     " + "--------  " + "--------------------------------------                " + "-------\n"
                + "     " + "add       " + "/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}              "
                + "Adds input Partner University module code to input university list\n"
                + "     " + "add       " + "/add u/{UNIVERSITY_NAME} m/{MODULE_CODE}              "
                + "Adds a lesson for the input module code to the timetable for the input\n"
                + "     " + "          " + "d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}\n"
                + "     " + "add       " + "/add u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/{input} "
                + "Adds input comment to the corresponding module code & university\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "create    " + "/create u/UNIVERSITY_NAME                             "
                + "Creates an empty module list for the input university\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "delete    " + "/delete u/{UNIVERSITY_NAME}                           "
                + "Deletes input university list\n"
                + "     " + "delete    " + "/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}           "
                + "Deletes input module from the university list\n"
                + "     " + "delete    " + "/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE}           "
                + "Deletes input lesson from the university's timetable\n"
                + "     " + "          " + "d/{DAY_OF_THE_WEEK} st/{START_TIME} en/{END_TIME}\n"
                + "     " + "delete    " + "/delete u/{UNIVERSITY_NAME} m/{MODULE_CODE} note/     "
                + "Deletes the comment from input university & module code if any\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "exit      " + "/exit                                                 "
                + "Terminates the program\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "favourite " + "/favourite add/{UNIVERSITY_NAME}                      "
                + "Adds a university list to the user's favourites\n"
                + "     " + "favourite " + "/favourite del/{UNIVERSITY_NAME}                      "
                + "Deletes a university list from the user's favourites\n"
                + "     " + "favourite " + "/favourite VIEW                                       "
                + "Displays the user's favourite university lists\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "help      " + "/help                                                 "
                + "Displays eligible user commands for the program\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "list      " + "/list MODULES                                         "
                + "Lists all existing university modules mappings that are approved in the format:\n"
                + "     " + "                                                                "
                + "[PU Module Code] [PU Module Title] [PU Module Credits] | \n"
                + "     " + "          " + "                                                      "
                + "[NUS Module Code] [NUS Module Title] [NUS Module Credits] \n"
                + "     " + "list      " + "/list UNIVERSITIES                                    "
                + "Lists all universities with module mappings available in database\n"
                + "     " + "list      " + "/list m/{MODULE_CODE}                                 "
                + "Lists all module mappings for input NUS module code in database\n"
                + "     " + "list      " + "/list u/{UNIVERSITY_NAME}                             "
                + "Lists all module mappings offered by input university in database\n"
                + "     " + "          " + "                                                    \n"
                + "     " + "view      " + "/view LISTS                                           "
                + "Displays all existing university lists that have been created by the user\n"
                + "     " + "view      " + "/view u/{UNIVERSITY_NAME}                             "
                + "Displays all modules added to user's input university list in the format:\n"
                + "     " + "                                                                "
                + "[Home University Module Code] [Home University Module Title] | \n"
                + "     " + "          " + "                                                      "
                + "[PU Module Code] [PU Module Title] | [Equivalent NUS Credits]\n"
                + "     " + "view      " + "/view TIMETABLES                                      "
                + "Displays all timetables for list of universities created by user\n"
                + "     " + "view      " + "/view DELETE_HISTORY                                  "
                + "Displays up to 5 most recent modules that the user has deleted\n\n"
                + "     " + "Note: Words in curly brackets are parameters that you should input as a user\n"
                + "     " + "Note: There should not be spaces in parameters, replace with underscores instead\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printCommands());
    }

    @Test
    public void testPrintModule() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
                + " | Partner University: " + "test " + "CPSC123" + " " + "Intro to AI"
                + " | Equivalent NUS Credits: " + "4 MCs";
        assertEquals(expected, Ui.printModule(dummy));
    }

    @Test
    public void testPrintModuleAddedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "____________________________________________________________________________\n"
                + "Success! You added:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "test " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " + "4 MCs"
                + "\n" + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleAddedAcknowledgement(dummy));
    }

    @Test
    public void testPrintModuleUpdatedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        dummy.setComment("A+ or nothing");
        String expected = "____________________________________________________________________________\n"
                + "Success! You updated:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: " + "test " + "CPSC123" + " "
                + "Intro to AI" + " | Equivalent NUS Credits: " + "4 MCs" + "\n"
                + "Note: " + dummy.getComment() + "\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleUpdatedAcknowledgement(dummy));

    }

    @Test
    public void testPrintModuleDeletedAcknowledgement() {
        UserModuleMapping dummy = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        String expected = "____________________________________________________________________________\n"
                + "Success! You deleted:\n" + "NUS: " + "CS3243" + " "
                + "Introduction to Artificial Intelligence" + " | Partner University: "
                + "test " + "CPSC123" + " " + "Intro to AI" + " | Equivalent NUS Credits: " + "4 MCs"
                + "\n" + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModuleDeletedAcknowledgement(dummy));
    }

    @Test
    public void testPrintPuListCreatedAcknowledgement() {
        String expected = "____________________________________________________________________________\n"
                + "Success! You have created a new list for " + "Stanford University" + "\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListCreatedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintPuListDeletedAcknowledgement() {
        String expected = "____________________________________________________________________________\n"
                + "Success! You deleted the list for " + "Stanford University" + "\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printPuListDeletedAcknowledgement("Stanford University"));
    }

    @Test
    public void testPrintModulesInList() {
        ArrayList<UserModuleMapping> modules = new ArrayList<UserModuleMapping>();
        UserModuleMapping dummy1 = new UserModuleMapping("CPSC123", "Intro to AI",
                "CS3243", "Introduction to Artificial Intelligence", "4",
                "test", "test", "test");
        UserModuleMapping dummy2 = new UserModuleMapping("CPSC456", "ML",
                "CS3244", "Machine Learning", "4",
                "test", "test", "test");
        modules.add(dummy1);
        modules.add(dummy2);
        String expected = "____________________________________________________________________________\n" + "1. "
                + "NUS: " + "CS3243" + " " + "Introduction to Artificial Intelligence"
                + " | Partner University: " + "test " + "CPSC123" + " " + "Intro to AI"
                + " | Equivalent NUS Credits: " + "4 MCs"
                + "\n" + "2. " + "NUS: " + "CS3244" + " " + "Machine Learning"
                + " | Partner University: " + "test " + "CPSC456" + " " + "ML"
                + " | Equivalent NUS Credits: " + "4 MCs" + "\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printModulesInUserList(modules));
    }

    @Test
    public void printDeletedModulesHistory_normalOperation() {
        ArrayDeque<UserModuleMapping> deletedModules = new ArrayDeque<>();
        UserModuleMapping testModule1 = new UserModuleMapping("MET CS 248", "Discrete Mathematics", "CS1231",
                "Discrete Structures", "4", "3", "Boston University", "USA");
        UserModuleMapping testModule2 = new UserModuleMapping("CS103",
                "Introduction to Internet Technologies and Web Programming",
                "IT1001", "Introduction to Computing", "4", "3", "Boston University", "USA");
        UserModuleMapping testModule3 = new UserModuleMapping("CS201",
                "INTRODUCTION TO PROGRAMMING WITH PYTHON",
                "CS1010S", "Programming Methodology", "4", "3", "Boston University", "USA");
        deletedModules.add(testModule1);
        deletedModules.add(testModule2);
        deletedModules.add(testModule3);
        String expected = "____________________________________________________________________________\n"
            + "Your most recently deleted modules are:\n"
            + "1. NUS: CS1231 Discrete Structures | Partner University: Boston University MET CS 248 Discrete "
                + "Mathematics | Equivalent NUS Credits: 4 MCs\n"
            + "2. NUS: IT1001 Introduction to Computing | Partner University: Boston University CS103 Introduction"
                + " to Internet Technologies and Web Programming | Equivalent NUS Credits: 4 MCs\n"
            + "3. NUS: CS1010S Programming Methodology | Partner University: Boston University CS201 INTRODUCTION"
                + " TO PROGRAMMING WITH PYTHON | Equivalent NUS Credits: 4 MCs\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printDeletedModulesHistory(deletedModules));
    }

    @Test
    public void printFavouriteListAddedAcknowledgement_normalOperation() {
        String universityName = "Stanford University";
        String expected = "____________________________________________________________________________\n"
                + "Success! You added:\n" + "Stanford University" + " to your favourite lists\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printFavouriteListAddedAcknowledgement(universityName));
    }

    @Test
    public void printFavouriteListDeletedAcknowledgement_normalOperation() {
        String universityName = "Stanford University";
        String expected = "____________________________________________________________________________\n"
                + "Success! You deleted:\n" + "Stanford University" + " from your favourite lists\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printFavouriteListDeletedAcknowledgement(universityName));
    }

    @Test
    public void printTimetableCreatedAcknowledgement_normalOperation() {
        String universityName = "UC Berkeley";
        String expected = "____________________________________________________________________________\n"
                + "Success! You have created a new timetable for UC Berkeley\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printTimetableCreatedAcknowledgement(universityName));
    }

    @Test
    public void printTimetableDeletedAcknowledgement_normalOperation() {
        String universityName = "UC Berkeley";
        String expected = "____________________________________________________________________________\n"
                + "Success! You deleted the timetable for UC Berkeley\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printTimetableDeletedAcknowledgement(universityName));
    }

    @Test
    public void printLessonAddedAcknowledgement_normalOperation() throws InvalidUniversityException,
            InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson lesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        String expected = "____________________________________________________________________________\n"
                + "Success! You have added a new lesson:\n"
                + "Stanford University Tuesday 11:00hrs-13:00hrs: CS229 Machine Learning\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printLessonAddedAcknowledgement(lesson));
    }

    @Test
    public void printLessonDeletedAcknowledgement_normalOperation() throws InvalidUniversityException,
            InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson lesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        String expected = "____________________________________________________________________________\n"
                + "Success! You have deleted the lesson:\n"
                + "Stanford University Tuesday 11:00hrs-13:00hrs: CS229 Machine Learning\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printLessonDeletedAcknowledgement(lesson));
    }

    @Test
    public void printLesson_normalOperation() throws InvalidUniversityException, InvalidModuleException {
        University university = new University("Stanford University", "USA");
        Lesson lesson = new Lesson("CS229", "Machine Learning", "5", university,"Tuesday", "11:00", "13:00");
        String expected = "11:00hrs-13:00hrs: CS229 Machine Learning";
        assertEquals(expected, Ui.printLesson(lesson));
    }

    @Test
    public void printTimetable_normalOperation() throws InvalidUniversityException, InvalidModuleException {
        Timetable timetable = new Timetable();
        University university = new University("Stanford University", "USA");
        Lesson lesson1 = new Lesson("CS229", "Machine Learning", "5", university,"Monday", "09:00", "13:00");
        Lesson lesson2 = new Lesson("CS103", "Mathematical Foundations of Computing", "5", university,
                "Tuesday", "11:00", "13:00");
        Lesson lesson3 = new Lesson("CS106A", "Programming Methodology", "5", university,
                "Wednesday", "11:00", "12:00");
        Lesson lesson4 = new Lesson("CS106B", "Programming Abstractions", "5", university,
                "Thursday", "13:00", "15:00");
        Lesson lesson5 = new Lesson("CS109", "Intro to Probability for Computer Scientists", "5", university,
                "Thursday", "09:00", "10:30");
        Lesson lesson6 = new Lesson("CS148", "Introduction to Computer Graphics and Imaging", "5", university,
                "Friday", "11:00", "13:00");
        Lesson lesson7 = new Lesson("CS161", "Design and Analysis of Algorithms", "5", university,
                "Friday", "08:30", "10:30");
        Lesson lesson8 = new Lesson("CS193C", "Client-Side Internet Technologies", "5", university,
                "Saturday", "10:00", "12:00");
        Lesson lesson9 = new Lesson("CS339", "Independent Project", "5", university,"Sunday", "09:00", "12:00");
        timetable.addLesson(lesson1, false);
        timetable.addLesson(lesson2, false);
        timetable.addLesson(lesson3, false);
        timetable.addLesson(lesson4, false);
        timetable.addLesson(lesson5, false);
        timetable.addLesson(lesson6, false);
        timetable.addLesson(lesson7, false);
        timetable.addLesson(lesson8, false);
        timetable.addLesson(lesson9, false);
        String expected = "____________________________________________________________________________\n"
                + "Monday:\n" + "1. 09:00hrs-13:00hrs: CS229 Machine Learning\n"
                + "Tuesday:\n" + "1. 11:00hrs-13:00hrs: CS103 Mathematical Foundations of Computing\n"
                + "Wednesday:\n" + "1. 11:00hrs-12:00hrs: CS106A Programming Methodology\n"
                + "Thursday:\n" + "1. 09:00hrs-10:30hrs: CS109 Intro to Probability for Computer Scientists\n"
                + "2. 13:00hrs-15:00hrs: CS106B Programming Abstractions\n"
                + "Friday:\n" + "1. 08:30hrs-10:30hrs: CS161 Design and Analysis of Algorithms\n"
                + "2. 11:00hrs-13:00hrs: CS148 Introduction to Computer Graphics and Imaging\n"
                + "Saturday:\n" + "1. 10:00hrs-12:00hrs: CS193C Client-Side Internet Technologies\n"
                + "Sunday:\n" + "1. 09:00hrs-12:00hrs: CS339 Independent Project\n"
                + "____________________________________________________________________________\n";
        assertEquals(expected, Ui.printTimetable(timetable.getTimetable()));
    }
}
