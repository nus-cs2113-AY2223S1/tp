package recipeditor;

import recipeditor.parser.Parser;
import recipeditor.ui.Ui;

public class Recipeditor {
    /**
     * Main entry point.
     */


    public static void main(String[] args) {
        Ui.printGreeting();
        while (true) {
            System.out.println("Please write your command below");
            String input = Ui.readInput();
            String output = Parser.parseCommand(input);
            System.out.println(output);
        }
    }
}
