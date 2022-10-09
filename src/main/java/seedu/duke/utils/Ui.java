package seedu.duke.utils;

import java.util.Scanner;

import seedu.duke.model.ModuleLoader;

public class Ui {

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine();
        return fullCommand;
    }


}
