package seedu.duke.commands;

import seedu.duke.Timetable;

import java.util.Scanner;

public class CommandAddModule {
    public static String addModule(Timetable timetable) {
        Scanner in = new Scanner(System.in);

        System.out.println("What is the module name?");
        String name = in.nextLine();

        System.out.println("What is the module code?");
        String code = in.nextLine();

        timetable.addNewModule(name, code);

        return "Successfully added new module: " + name + " [" + code + "]";
    }
}
