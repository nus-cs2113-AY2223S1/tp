package seedu.duke;



public class Duke {
    static UI ui = new UI();
    static ModuleList modulelist = new ModuleList();
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        boolean isExit = false;
        UI.helloMessage();
        while (!isExit) {
            String input = UI.getInput();
            Command c = Parser.parse(input);
            if (c != null) {
                c.execute(ui, modulelist);
                isExit = c.checkExit();
            }
        }
    }
}
