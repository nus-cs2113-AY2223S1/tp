package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class UI {

    public static String separationLine = "----------------------------------------";

    public static void printSeparationLine() {
        System.out.println(separationLine);
    }

    /*
    Function to get input from Command Line Interface
     */
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = "";
        try {
            input = in.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return input;
    }

    /**
     *Message to be printed in the beginning;
     */
    public static void helloMessage() {
        printSeparationLine();
        System.out.println("Welcome to PlanIT!");
        System.out.println("Start planning out your 4 years in NUS with us!");
        printSeparationLine();
    }
    /**
     *Message to be printed in the end
     */
    public static void endMessage() {
        System.out.println("Thank you for using PlanIT!");
        System.out.println("See you again next time!");
    }
    /**
     * Message to be printed after adding a module
     */
    public static void addMessage(String modCode, String semester, String grade) {
        System.out.print(modCode + " has been added to " + semester);
        if (grade.matches("-")) {
            System.out.println(" as incomplete!");
        } else {
            System.out.println(" as completed");
        }
    }

    /**
     * Message to be printed if an already existing module is being added
     * @param modCode the module which is being repeated
     */
    public static void repetitionMessage(String modCode) {
        System.out.println("This " + modCode + " module is already in your plan.");
        System.out.println("Choose another module to add or delete the one in the plan and add it again.");
    }

    /**
     * Function to print message after deleting a mod
     * @param modCode the module which has been deleted
     */
    public static void deleteMessage(String modCode) {
        System.out.println(modCode + " has been deleted from your plan");
    }

    /**
     * Message to be printed if a module which is trying to be deleted is not found in plan
     * @param modCode the module which is to be deleted
     */
    public static void notFoundDeletionMessage(String modCode) {
        System.out.println("The module " + modCode + " is not found in your plan!!");
        System.out.println("Please add the module first before you want to delete.");
    }
    public static void fileLoadingErrorMessage() {
        System.out.println("Could not find any previous usage.");
    }

    /**
     * Function to print a list of modules in a particular semester
     * @param modules Collection of modules to be printed
     * @param semester Semester for which the modules need to be printed
     */
    public static void listMessage(ArrayList<Module> modules, String semester) {
        System.out.println("These are your mods for " + semester);
        int counter = 1;
        for (Module mod : modules) {
            System.out.println(counter + ". " + mod.toString());
            counter++;
        }
    }

    /**
     * Function to print a message if there are no modules in a particular semester
     * @param semester Semester for which the modules need to be printed
     */
    public static void emptyListMessage(String semester) {
        System.out.println("There is no module allocated in " + semester + "!!");
    }

    /**
     * Function to print message when number of mcs needs to be displayed
     * @param semester Semester for which number of mcs have been calculated
     * @param mcs number of mcs calculated
     */
    public static void mcMessage(String semester, int mcs) {
        System.out.println("You have " + mcs + " mcs for " + semester);
    }

    public static void findMessage(ArrayList<Module> matchingModules) {
        System.out.println("These are your matching module(s):");
        int counter = 1;
        for (Module mod: matchingModules) {
            System.out.println(counter + ". " + mod.toString());
            counter++;
        }
    }

    public static void emptyFindMessage() {
        System.out.println("There are no existing modules that match your keyword inputted.");
    }

    public static void NOCEligibleMessage() {
        System.out.println("You are eligible for NOC!");
    }

    public static void NOCIneligibleMessage() {
        System.out.println("You are ineligible for NOC");
    }

    public static void SEPEligibleMessage() {
        System.out.println("You are eligible for SEP!");
    }

    public static void SEPIneligibleMessage() {
        System.out.println("You are ineligible for SEP");
    }

    public static void helpMessage(String message) {
        System.out.println(message);
    }

    /**
     * Message to be displayed when an exception is encountered due to invalid input format
     */
    public static void invalidFormatMessage() {
        System.out.println("INPUT FORMAT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

    /**
     * Message to be displayed when an exception is encountered due to invalid content in input
     */
    public static void invalidContentMessage() {
        System.out.println("INPUT CONTENT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

    /**
     * Message to be displayed when an exception is encountered due to an invalid command word
     */

    public static void invalidCommandWordMessage() {
        System.out.println("NO COMMAND WORD SPECIFIED!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

}
