package computercomponentchooser;

import java.util.Scanner;

public class ComputerComponentChooser {
    public static void main(String[] args) {
        Ui.startSession();

        String line;
        Scanner in = new Scanner(System.in);

        BuildManager buildManager = new BuildManager();

        do {
            line = in.nextLine();
            if (Parser.checkEdit(line)) {
                while (!EditParser.checkBack(line)) {
                    line = in.nextLine();
                }
            }
        } while (!Parser.checkBye(line));

        Ui.endSession();
    }
}
