package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;


public class UI {

    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void helloMessage() {
        System.out.println("Welcome to PlanIT!");
        System.out.println("Start planning out your 4 years in NUS with us!");
    }

    public static void endMessage(){
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
    public static void deleteMessage(String modCode){
        System.out.println(modCode + " has been deleted from your plan");
    }
    public static void listMessage(ArrayList<Module> modules, String semester){
        System.out.println("These are your mods for " + semester);
        int counter = 1;
        for (Module mod : modules) {
            System.out.println(counter + ". " + mod.toString());
            counter += 1;
        }
    }
    public static void mcMessage(String semester, int mcs){
        System.out.println("You have " + mcs + " mcs for " + semester);
    }

}
