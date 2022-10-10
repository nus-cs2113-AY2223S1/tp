
package seedu.duke;

public class Parser {

    public static Command parse(String input) {
        String[] splitText = input.split(" ");
        switch (splitText[0]) {
            case "add":
                return new Add(input);
            case "delete":
                return new Delete(input);
            case "view":
                return new View(input);
            case "mcs":
                return new Mcs(input);
            case "exit":
                return new Exit(input);
            default:
                return null;

        }
    }

}
