package computerComponentChooser;

import java.util.Scanner;

public class computerComponentChooser {
    public static void main(String[] args) {
        Ui.startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!Parser.checkBye(line)) {
            //Parser.parse(Manager, line); To be added
            line = in.nextLine();
        }

        Ui.endSession();
    }
}
