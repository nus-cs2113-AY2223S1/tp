package seedu.duke;

import seedu.duke.commands.Check;
import seedu.duke.design.ASCIIArtGenerator;
import seedu.duke.exceptions.InvalidInputContentException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {

    public static String separationLine = "----------------------------------------";
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void printSeparationLine() {
        System.out.println(separationLine);
    }

    /**
     * Function to get user input.
     * @return user input
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
     * Message to be printed in the beginning;
     */
    public static void helloMessage() throws Exception {
        printSeparationLine();
        ASCIIArtGenerator.printTextArt("PlanIT!", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        printSeparationLine();
        System.out.println("Welcome to PlanIT!");
        System.out.println("Start planning out your 4 years in NUS with us :)");
        printSeparationLine();
        System.out.println("To get started, type 'help' to see the list of available commands.");
        printSeparationLine();
    }

    /**
     * Message to be printed in the end
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

    /**
     * Message to be printed if a semester which is trying to be deleted is not found in plan
     * @param semester : the semester in which all modules are to be deleted
     */
    public static void notFoundClearMessage(String semester) {
        System.out.println("No modules found in " + semester + "!");
        System.out.println("Please add the modules to the semester first before you want to clear.");
    }

    /**
     * Message to be printed when all modules have been successfully cleared from plan
     */
    public static void allClearedMessage() {
        System.out.println("Successfully cleared all modules in your plan!");
    }

    /**
     * Message to be printed when modules from a semester have been successfully cleared from plan
     */
    public static void semesterClearedMessage(String semester) {
        System.out.println("Successfully cleared all modules for " + semester + ".");
    }

    /**
     * Message to be printed when there is no modules found in plan
     */
    public static void noModulesFoundMessage() {
        System.out.println("0 modules found in your plan!");
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
        System.out.println("These are your module(s) for " + semester);
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

    public static void nocEligibleMessage() {
        System.out.println("You are eligible for NOC!");
    }

    public static void nocIneligibleMessage() {
        System.out.println("Sorry, You are ineligible for NOC." + "\n");
        System.out.println("These may be possible reasons for ineligibility:");
        System.out.println(" * You have yet to complete 4 semesters of study");
        System.out.println(" * You are currently in your final academic semester");
        System.out.println(" * You have yet to obtain more than 70MC");

    }

    public static void sepEligibleMessage() {
        System.out.println("You are eligible for SEP!");
    }

    public static void sepIneligibleMessage() {
        System.out.println("Sorry, You are ineligible for SEP." + "\n");
        System.out.println("These may be possible reasons for ineligibility:");
        System.out.println(" * You have yet to complete 2 semesters of study");
        System.out.println(" * You are currently in your final year");
        System.out.println(" * Your CAP is below 3.0");

    }

    public static void overview(ModuleList moduleList) throws InvalidInputContentException {

        System.out.println("Here’s an overview of your Profile:" + "\n");
        System.out.println("* Current Semester: " + new Check().findCurrentSemesterInString() + "\n");
        System.out.println("* Total MCs : " + moduleList.totalMcs());
        System.out.println("* Total Graded MCs : " + moduleList.totalGradedMcs());
        System.out.println("* Total Ungraded (-) MCs : " + moduleList.totalUngradedMcs());
        System.out.println("* Total S/U MCs : " + moduleList.totalSuMcs() + "\n");
        System.out.println("* Cumulative Average Point (CAP) : " + df.format(moduleList.calculateCap()) + "\n");
        System.out.println("* MCs Needed needed for graduation : " + moduleList.mcsForGraduation() + "\n");
        System.out.println("* Eligibility for NOC : " + (new Check("NOC").checkNOC() ? "Yes" : "No"));
        System.out.println("* Eligibility for SEP : " + (new Check("SEP").checkSEP() ? "Yes" : "No"));

    }

    public static void helpMessage(String message) {
        System.out.println(message);
    }

}
