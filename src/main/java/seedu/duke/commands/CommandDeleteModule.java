package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.UI;

import java.io.IOException;


public class CommandDeleteModule {
    public static String deleteModule() {

        UI.printResponse(Timetable.listModules());

        UI.printResponse("Which module you would like to delete? "
                + "Please enter the index of that module. ");

        try {
            String index = UI.sc.nextLine();
            Timetable.deleteModule(Integer.parseInt(index));
        } catch (IndexOutOfBoundsException e) {
            return "Please input a valid index!";
        } catch (NumberFormatException e) {
            return "Please input an integer!";
        }

        return "Successfully deleted module!";
    }
    
}
