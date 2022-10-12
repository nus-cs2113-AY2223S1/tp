package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Timetable;

import java.io.IOException;


public class CommandDeleteModule {
    public static String deleteModule(Timetable timetable) {

        System.out.println(timetable.listModules());

        System.out.println("Which module you would like to delete? " + 
                "Please enter the index of that module. ");

        try {
            String index = Duke.sc.nextLine();
            timetable.deleteModule(Integer.parseInt(index));
        } catch (IndexOutOfBoundsException e) {
            return "Please input a valid index!";
        } catch(NumberFormatException e) {
            return "Please input an integer!";
        }

        return "Successfully deleted module!";
    }
    
}
