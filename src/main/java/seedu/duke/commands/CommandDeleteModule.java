package seedu.duke.commands;

import seedu.duke.Timetable;

import java.util.Scanner;

public class CommandDeleteModule {
    public static String deleteModule(Timetable timetable) {
        Scanner in = new Scanner(System.in);

        System.out.println("Here are a list of modules stored: ");
        timetable.listModules();

        System.out.println("Which module you would like to delete? " + 
                "Please enter the index of that module. ");
        String index = in.nextLine();

        timetable.deleteModule(index);

        return "Successfully deleted module!";
    }
    
}
