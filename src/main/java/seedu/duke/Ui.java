package seedu.duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke, your Recipe Notetaker!");
        printHorizontalLine();
    }

    public static String readInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    //public static void printAddRecipeText(Recipe recipe) {
    //    System.out.println("Duke has added this task to your list:");
    //    System.out.println(recipe.name);
    //    System.out.println("Now you have " + recipe.getRecipeSize() + " tasks in the list. You can do it!!");
    //    Ui.printHorizontalLine();
    //}
    //
    //public static void printRecipeList() {
    //}

    public static void printCreateParentFolderErrorText() {
        System.out.println("Error creating parent folder(s)");
    }

    public static void printFilePath(File file, String filePath) {
        try {
            if (file.createNewFile()) {
                System.out.printf("Bob has created the file at %s\n", filePath);
            } else {
                System.out.printf("File already exists at %s\n", filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: Could not create file at %s" + filePath);
        }
    }
}
