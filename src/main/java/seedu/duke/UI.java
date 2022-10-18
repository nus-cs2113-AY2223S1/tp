package seedu.duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class UI {

    public static String separationLine = "----------------------------------------";

    public static void printSeparationLine() {
        System.out.println(separationLine);
    }

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

    public static void helloMessage() {
        printSeparationLine();
        System.out.println("Welcome to PlanIT!");
        System.out.println("Start planning out your 4 years in NUS with us!");
        printSeparationLine();
    }

    public static void endMessage() {
        System.out.println("Thank you for using PlanIT!");
        System.out.println("See you again next time!");
    }

    public static void addMessage(String modCode, String semester, String grade) {
        System.out.print(modCode + " has been added to " + semester);
        if (grade.matches("-")) {
            System.out.println(" as incomplete!");
        } else {
            System.out.println(" as completed");
        }
    }

    public static void repetitionMessage(String modCode) {
        System.out.println("This " + modCode + " module is already in your plan.");
        System.out.println("Choose another module to add or delete the one in the plan and add it again.");
    }

    public static void deleteMessage(String modCode) {
        System.out.println(modCode + " has been deleted from your plan");
    }

    public static void notFoundDeletionMessage(String modCode) {
        System.out.println("The module " + modCode + " is not found in your plan!!");
        System.out.println("Please add the module first before you want to delete.");
    }

    public static void listMessage(ArrayList<Module> modules, String semester) {
        System.out.println("These are your mods for " + semester);
        int counter = 1;
        for (Module mod : modules) {
            System.out.println(counter + ". " + mod.toString());
            counter++;
        }
    }

    public static void emptyListMessage(String semester) {
        System.out.println("There is no module allocated in " + semester + "!!");
    }

    public static void mcMessage(String semester, int mcs) {
        System.out.println("You have " + mcs + " mcs for " + semester);
    }

    public static void invalidFormatMessage() {
        System.out.println("INPUT FORMAT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

    public static void invalidContentMessage() {
        System.out.println("INPUT CONTENT IS WRONG!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

    public static void invalidCommandWordMessage() {
        System.out.println("NO COMMAND WORD SPECIFIED!! PLEASE KEY IN THE CORRECT INPUT!!");
    }

}
